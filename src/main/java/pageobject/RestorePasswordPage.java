package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RestorePasswordPage extends GeneralMenu {
    @FindBy(className = "Auth_link__1fOlj")
    private WebElement logInLink;

    public RestorePasswordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LogInPage clickLogInLink() {
        logInLink.click();
        return new LogInPage(driver);
    }
}
