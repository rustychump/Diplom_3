import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.ConstructorSection;

public class TestConstructorSection extends BaseTest {
    public TestConstructorSection(String browserChoice) {
        super(browserChoice);
    }

    @Test
    @DisplayName("Проверка, что работает переход к разделу «Булки»")
    public void checkGoToBunSection() {
        Assert.assertEquals(fillingsText, new ConstructorSection(driver).clickFillingsButton().getTextActiveButton());

        Assert.assertEquals(bunsText, new ConstructorSection(driver).clickBunsButton().getTextActiveButton());
    }

    @Test
    @DisplayName("Проверка, что работает переход к разделу «Соусы»")
    public void checkGoToSaucesSection() {
        Assert.assertEquals(saucesText, new ConstructorSection(driver).clickSaucesButton().getTextActiveButton());
    }

    @Test
    @DisplayName("Проверка, что работает переход к разделу «Начинки»")
    public void checkGoToFillingsSection() {
        Assert.assertEquals(fillingsText, new ConstructorSection(driver).clickFillingsButton().getTextActiveButton());
    }

    @Override
    public void testPreparation() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";

        if(browserChoice.equals("Yandex")){
            System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/chromedriver_yandex.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:/Program Files (x86)/Yandex/YandexBrowser/Application/browser.exe");
            driver = new ChromeDriver(options);
            driver.get(urlStellarBurgers);
        } else if(browserChoice.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/chromedriver.exe");
            driver = new ChromeDriver();
            driver.get(urlStellarBurgers);
        }
    }

    @Override
    public void tearDown() {
        driver.quit();
    }
}
