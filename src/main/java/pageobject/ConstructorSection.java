package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorSection {
    private WebDriver driver;
    private By activeButton = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span");
    private By bunsButton = By.xpath(".//span[text()='Булки']");
    private By saucesButton = By.xpath(".//span[text()='Соусы']");
    private By fillingsButton = By.xpath(".//span[text()='Начинки']");

    public ConstructorSection(WebDriver driver) {
        this.driver = driver;
    }

    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    public void clickSaucesButton() {
        driver.findElement(saucesButton).click();
    }

    public void clickFillingsButton() {
        driver.findElement(fillingsButton).click();
    }

    public String getTextActiveButton() {
        return driver.findElement(activeButton).getText();
    }

}
