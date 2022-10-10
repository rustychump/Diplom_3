package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private WebDriver driver;
    private By profileButton = By.className("Account_link_active__2opc9");

    private By logOutButton = By.className("Account_button__14Yp3");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkOpeningProfilePage() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(profileButton));
        driver.findElement(profileButton);
    }

    public void clickLogOutButton() {
        driver.findElement(logOutButton).click();
    }
}
