package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage {
    private WebDriver driver;
    //поле ввода "Имя"
    private By nameField = By.xpath(".//form[@class='Auth_form__3qKeq mb-20']/fieldset[1]/div/div/input");
    //поле ввода "Email"
    private By emailField = By.xpath(".//form[@class='Auth_form__3qKeq mb-20']/fieldset[2]/div/div/input");
    //поле ввода "Пароль"
    private By passwordField = By.name("Пароль");
    //кнопка "Зарегистрироваться"
    private By signUpButton = By.className("button_button__33qZ0");
    private By signUpHeader = By.xpath(".//h2[text()='Регистрация']");
    private By mistakeIncorrectPassword = By.xpath(".//p[text()='Некорректный пароль']");
    private By logInLink = By.className("Auth_link__1fOlj");


    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод заполняет поле ввода "Имя"
    public void inputNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    //метод заполняет поле ввода "Email"
    public void inputEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    //метод заполняет поле ввода "Password"
    public void inputPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    //метод кликает на кнопку "Зарегистрироваться"
    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    //метод кликает на ссылку "Войти"
    public void clickLogInLink() {
        driver.findElement(logInLink).click();
    }

    //метод заполняет поля ввода "Имя", "Email", "Password" и кликает на кнопку "Зарегистрироваться"
    public void signUpProcedure(String name, String email, String password) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(signUpHeader));
        inputNameField(name);
        inputEmailField(email);
        inputPasswordField(password);
        clickSignUpButton();
    }

    //метод ищет ошибку "некорректный пароль"
    public void findMistakeIncorrectPassword() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(mistakeIncorrectPassword));
    }
}
