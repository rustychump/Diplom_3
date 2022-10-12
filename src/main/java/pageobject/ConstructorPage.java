package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage extends GeneralMenu {
    //кнопка "Войти в аккаунт"
    @FindBy(className = "button_button__33qZ0")
    private WebElement logInButton;
    //заголовок "Соберите бургер"
    @FindBy(xpath = ".//h1[text()='Соберите бургер']")
    private WebElement mainHeader;

    public ConstructorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //метод кликает на кнопку "Войти в аккаунт"
    public LogInPage clickLogInButton() {
        logInButton.click();
        return new LogInPage(driver);
    }

    //метод регистрации в приложении
    public LogInPage signUp(String name, String email, String password) {
        clickLogInButton().clickSignUpLink().signUpProcedure(name, email, password);
        return new LogInPage(driver);

    }

    //метод входа в аккаунт
    public ConstructorPage logIn(String email, String password) {
        clickLogInButton().logInProcedure(email, password);
        waitOpeningConstructorPage();
        return this;
    }

    public void waitOpeningConstructorPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(mainHeader));
    }

    public boolean checkOpeningConstructorPage() {
        waitOpeningConstructorPage();
        return mainHeader.isDisplayed();
    }
}
