package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConstructorSection extends ConstructorPage {
    @FindBy(xpath = ".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span")
    private WebElement activeButton;
    @FindBy(xpath = ".//span[text()='Булки']")
    private WebElement bunsButton;
    @FindBy(xpath = ".//span[text()='Соусы']")
    private WebElement saucesButton;
    @FindBy(xpath = ".//span[text()='Начинки']")
    private WebElement fillingsButton;

    public ConstructorSection(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public ConstructorSection clickBunsButton() {
        bunsButton.click();
        return this;
    }

    public ConstructorSection clickSaucesButton() {
        saucesButton.click();
        return this;
    }

    public ConstructorSection clickFillingsButton() {
        fillingsButton.click();
        return this;
    }

    public String getTextActiveButton() {
        return activeButton.getText();
    }

}
