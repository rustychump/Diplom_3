package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class GeneralMenu {
    protected WebDriver driver;
    //кнопка "Конструктор"
    @FindBy(xpath = ".//p[text()='Конструктор']")
    protected WebElement constructorButton;
    //кнопка "Личный Кабинет"
    @FindBy(xpath = ".//p[text()='Личный Кабинет']")
    protected WebElement profilePageButton;
    //логотип Stellar Burgers
    @FindBy(className = "AppHeader_header__logo__2D0X2")
    protected WebElement logoStellarBurgers;

    public GeneralMenu(WebDriver driver) {
        this.driver = driver;
    }

    //метод кликает на кнопку "Личный кабинет"
    public ProfilePage clickProfilePageButton() {
        profilePageButton.click();
        return new ProfilePage(driver);
    }

    //метод кликает на кнопку "Конструктор"
    public ConstructorPage clickConstructorButton() {
        constructorButton.click();
        return new ConstructorPage(driver);
    }

    //метод кликает на логотип Stellar Burgers
    public ConstructorPage clickLogoStellarBurgers() {
        logoStellarBurgers.click();
        return new ConstructorPage(driver);
    }
}
