package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage extends GeneralMenu {
    @FindBy(className = "Account_link_active__2opc9")
    private WebElement profileButton;
    @FindBy(className = "Account_button__14Yp3")
    private WebElement logOutButton;
    @FindBy(xpath = ".//a[text()='История заказов']")
    private WebElement orderHistory;

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean checkOpeningProfilePage() {
        waitOpeningProfilePage();
        return profileButton.isDisplayed();
    }

    public LogInPage clickLogOutButton() {
        waitOpeningProfilePage();
        logOutButton.click();
        return new LogInPage(driver);
    }

    public void waitOpeningProfilePage() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(profileButton));
    }
}
