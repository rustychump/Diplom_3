package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestorePasswordPage {
    private WebDriver driver;

    private By logInLink = By.className("Auth_link__1fOlj");

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogInLink() {
        driver.findElement(logInLink).click();
    }
}
