import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.ConstructorSection;

@RunWith(Parameterized.class)
public class TestConstructorSection {
    private final String urlStellarBurgers = "https://stellarburgers.nomoreparties.site/";
    private WebDriver driver;
    private String bunsText = "Булки";
    private String saucesText = "Соусы";
    private String fillingsText = "Начинки";
    private String browserChoice;

    public TestConstructorSection(String browserChoice) {
        this.browserChoice = browserChoice;
    }

    @Parameterized.Parameters(name = "Прогонка тестов через {0}")
    public static Object[][] getBrowser() {
        return new Object[][] {
                {"Chrome"},
                {"Yandex"},
        };
    }

    @Before
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

    @Test
    @DisplayName("Проверка, что работает переход к разделу «Булки»")
    public void checkGoToBunSection() {
        ConstructorSection objConstructorSection = new ConstructorSection(driver);
        objConstructorSection.clickFillingsButton();
        Assert.assertEquals(fillingsText, objConstructorSection.getTextActiveButton());

        objConstructorSection.clickBunsButton();
        Assert.assertEquals(bunsText, objConstructorSection.getTextActiveButton());
    }

    @Test
    @DisplayName("Проверка, что работает переход к разделу «Соусы»")
    public void checkGoToSaucesSection() {
        ConstructorSection objConstructorSection = new ConstructorSection(driver);
        objConstructorSection.clickSaucesButton();
        Assert.assertEquals(saucesText, objConstructorSection.getTextActiveButton());
    }

    @Test
    @DisplayName("Проверка, что работает переход к разделу «Начинки»")
    public void checkGoToFillingsSection() {
        ConstructorSection objConstructorSection = new ConstructorSection(driver);
        objConstructorSection.clickFillingsButton();
        Assert.assertEquals(fillingsText, objConstructorSection.getTextActiveButton());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
