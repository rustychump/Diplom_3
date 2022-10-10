import cards.CreateUserCard;
import cards.ResponseAuthUserCard;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.MainPage;
import pageobject.ProfilePage;

import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
public class TestGoToAnyPage {
    private final String urlStellarBurgers = "https://stellarburgers.nomoreparties.site/";
    private WebDriver driver;
    String name = "name";
    String email = "test@email.ru";
    String password = "password";
    CreateUserCard createUserCard = new CreateUserCard(email, password, name);
    private String browserChoice;

    public TestGoToAnyPage(String browserChoice) {
        this.browserChoice = browserChoice;
    }

    @Parameterized.Parameters(name = "Прогонка тестов через {0}")
    public static Object[][] getBrowser() {
        return new Object[][] {
                {"Chrome"},
                {"Yandex"},
        };
    }

    private ResponseAuthUserCard getResponseAuthUserCard(CreateUserCard userCard) {

        return given()
                .header("Content-type", "application/json")
                .body(userCard)
                .when()
                .post("/api/auth/login")
                .body().as(ResponseAuthUserCard.class);
    }

    @Before
    public void testPreparation() {

        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";

        given()
                .header("Content-type", "application/json")
                .body(createUserCard)
                .when()
                .post("/api/auth/register");

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
    @DisplayName("Проверка перехода в личный кабинет по клику на кнопку «Личный кабинет»")
    public void checkGoToProfilePage() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.logIn(email, password);
        objMainPage.clickProfileButton();

        ProfilePage objProfilePage = new ProfilePage(driver);
        objProfilePage.checkOpeningProfilePage();
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор по клику на кноку «Конструктор»")
    public void checkGoToConstructorAfterClickConstructorButton() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.logIn(email, password);
        objMainPage.clickProfileButton();

        ProfilePage objProfilePage = new ProfilePage(driver);
        objProfilePage.checkOpeningProfilePage();

        objMainPage.clickConstructorButton();
        objMainPage.checkOpeningMainPage();
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void checkGoToConstructorAfterClickLogoStellarBurgers() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.logIn(email, password);
        objMainPage.clickProfileButton();

        ProfilePage objProfilePage = new ProfilePage(driver);
        objProfilePage.checkOpeningProfilePage();

        objMainPage.clickLogoStellarBurgers();
        objMainPage.checkOpeningMainPage();
    }

    @After
    public void tearDown() {
        try {
            given()
                    .auth().oauth2(getResponseAuthUserCard(createUserCard).getAccessToken().substring(7))
                    .delete("/api/auth/user");
        } catch (NullPointerException exception) { }

        driver.quit();
    }
}
