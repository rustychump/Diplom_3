package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogInPage extends GeneralMenu {
    //ссылка "Зарегистрироваться"
    @FindBy(xpath = ".//a[text()='Зарегистрироваться']")
    private WebElement signUpLink;
    //ссылка "Восстановить пароль"
    @FindBy(xpath = ".//a[text()='Восстановить пароль']")
    private WebElement restorePasswordLink;
    //поле ввода "Имя"
    @FindBy(name = "name")
    private WebElement emailField;
    //поле ввода "Пароль"
    @FindBy(name = "Пароль")
    private WebElement passwordField;
    //кнопка "Войти"
    @FindBy(className = "button_button__33qZ0")
    private WebElement logInButton;
    @FindBy(xpath = ".//h2[text()='Вход']")
    private WebElement logInHeader;

    public LogInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    //метод кликает на ссылку "Зарегистрироваться"
    public SignUpPage clickSignUpLink() {
        signUpLink.click();
        return new SignUpPage(driver);
    }

    //метод кликает на ссылку "Восстановить пароль"
    public RestorePasswordPage clickRestorePasswordLink() {
        restorePasswordLink.click();
        return new RestorePasswordPage(driver);
    }

    //метод заполняет поле ввода "Email"
    public LogInPage inputEmailField(String email) {
        emailField.sendKeys(email);
        return this;
    }

    //метод заполняет поле ввода "Пароль"
    public LogInPage inputPasswordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    //метод кликает на кнопку "Войти"
    public ConstructorPage clickLogInButton() {
        logInButton.click();
        return new ConstructorPage(driver);
    }

    //метод заполняет поля ввода "Email", "Пароль" и кликает на кнопку "Войти"
    public ConstructorPage logInProcedure(String email, String password) {
        waitOpeningLogInPage();
        return inputEmailField(email).inputPasswordField(password).clickLogInButton();
    }

    public boolean checkOpeningLogInPage() {
        waitOpeningLogInPage();
        return logInHeader.isDisplayed();
    }

    public void waitOpeningLogInPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(logInHeader));
    }
}
