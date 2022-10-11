package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage extends GeneralMenu {
    @FindBy(xpath = ".//form[@class='Auth_form__3qKeq mb-20']/fieldset[1]/div/div/input")
    //поле ввода "Имя"
    private WebElement nameField;
    //поле ввода "Email"
    @FindBy(xpath = ".//form[@class='Auth_form__3qKeq mb-20']/fieldset[2]/div/div/input")
    private WebElement emailField;
    //поле ввода "Пароль"
    @FindBy(name = "Пароль")
    private WebElement passwordField;
    //кнопка "Зарегистрироваться"
    @FindBy(className = "button_button__33qZ0")
    private WebElement signUpButton;
    @FindBy(xpath = ".//h2[text()='Регистрация']")
    private WebElement signUpHeader;
    @FindBy(xpath = ".//p[text()='Некорректный пароль']")
    private WebElement mistakeIncorrectPassword;
    @FindBy(className = "Auth_link__1fOlj")
    private WebElement logInLink;


    public SignUpPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //метод заполняет поле ввода "Имя"
    public SignUpPage inputNameField(String name) {
        nameField.sendKeys(name);
        return this;
    }

    //метод заполняет поле ввода "Email"
    public SignUpPage inputEmailField(String email) {
        emailField.sendKeys(email);
        return this;
    }

    //метод заполняет поле ввода "Password"
    public SignUpPage inputPasswordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    //метод кликает на кнопку "Зарегистрироваться"
    public LogInPage clickSignUpButton() {
        signUpButton.click();
        return new LogInPage(driver);
    }

    //метод кликает на ссылку "Войти"
    public LogInPage clickLogInLink() {
        logInLink.click();
        return new LogInPage(driver);
    }

    //метод заполняет поля ввода "Имя", "Email", "Password" и кликает на кнопку "Зарегистрироваться"
    public LogInPage signUpProcedure(String name, String email, String password) {
        waitOpeningSignUpPage();
        return inputNameField(name).inputEmailField(email).inputPasswordField(password).clickSignUpButton();
    }

    //метод ищет ошибку "некорректный пароль"
    public boolean findMistakeIncorrectPassword() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(mistakeIncorrectPassword));
        return mistakeIncorrectPassword.isDisplayed();
    }

    public void waitOpeningSignUpPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(signUpHeader));
    }
}
