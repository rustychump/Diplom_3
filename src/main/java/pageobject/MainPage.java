package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    //кнопка "Войти в аккаунт"
    private By logInButton = By.className("button_button__33qZ0");
    //заголовок "Соберите бургер"
    private By mainHeader = By.xpath(".//h1[text()='Соберите бургер']");
    //кнопка "Личный Кабинет"
    private By profileButton = By.xpath(".//p[text()='Личный Кабинет']");
    //кнопка "Конструктор"
    private By constructorButton = By.xpath(".//p[text()='Конструктор']");
    //логотип Stellar Burgers
    private By logoStellarBurgers = By.className("AppHeader_header__logo__2D0X2");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод кликает на кнопку "Войти в аккаунт"
    public void clickLogInButton() {
        driver.findElement(logInButton).click();
    }

    //метод кликает на кнопку "Личный кабинет"
    public void clickProfileButton() {
        driver.findElement(profileButton).click();
    }

    //метод кликает на кнопку "Конструктор"
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    //метод кликает на логотип Stellar Burgers
    public void clickLogoStellarBurgers() {
        driver.findElement(logoStellarBurgers).click();
    }

    //метод регистрации в приложении
    public void signUp(String name, String email, String password) {
        clickLogInButton();
        LogInPage objLogInPage = new LogInPage(driver);
        objLogInPage.clickSignUpLink();
        SignUpPage objSignUpPage = new SignUpPage(driver);
        objSignUpPage.signUpProcedure(name, email, password);
    }

    //метод входа в аккаунт
    public void logIn(String email, String password) {
        clickLogInButton();
        LogInPage objLogInPage = new LogInPage(driver);
        objLogInPage.logInProcedure(email, password);
        checkOpeningMainPage();
    }

    public void checkOpeningMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(mainHeader));
        driver.findElement(mainHeader);
    }
}
