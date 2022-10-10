package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogInPage {

    private WebDriver driver;
    //ссылка "Зарегистрироваться"
    private By signUpLink = By.xpath(".//a[text()='Зарегистрироваться']");
    //ссылка "Восстановить пароль"
    private By restorePasswordLink = By.xpath(".//a[text()='Восстановить пароль']");
    //поле ввода "Имя"
    private By emailField = By.name("name");
    //поле ввода "Пароль"
    private By passwordField = By.name("Пароль");
    //кнопка "Войти"
    private By logInButton = By.className("button_button__33qZ0");
    private By logInHeader = By.xpath(".//h2[text()='Вход']");

    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод кликает на ссылку "Зарегистрироваться"
    public void clickSignUpLink() {
        driver.findElement(signUpLink).click();
    }

    //метод кликает на ссылку "Восстановить пароль"
    public void clickRestorePasswordLink() {
        driver.findElement(restorePasswordLink).click();
    }

    //метод заполняет поле ввода "Email"
    public void inputEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    //метод заполняет поле ввода "Пароль"
    public void inputPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    //метод кликает на кнопку "Войти"
    public void clickLogInButton() {
        driver.findElement(logInButton).click();
    }

    //метод заполняет поля ввода "Email", "Пароль" и кликает на кнопку "Войти"
    public void logInProcedure(String email, String password) {
        checkOpeningLogInPage();
        inputEmailField(email);
        inputPasswordField(password);
        clickLogInButton();
    }

    public void checkOpeningLogInPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(logInHeader));
    }
}
