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
import pageobject.LogInPage;
import pageobject.MainPage;
import pageobject.SignUpPage;

import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
public class TestSignIn {
    private final String urlStellarBurgers = "https://stellarburgers.nomoreparties.site/";
    private WebDriver driver;
    String name = "name";
    String email = "test@email.ru";
    String password = "password";
    String incorrectPassword = "123";
    CreateUserCard createUserCard = new CreateUserCard(email, password, name);
    CreateUserCard createUserCardWithIncorrectPassword = new CreateUserCard(email, incorrectPassword, name);
    private String browserChoice;

    public TestSignIn(String browserChoice) {
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
    @DisplayName("Проверка успешной регистрации")
    public void checkSuccessfulSignIn() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.signUp(name, email, password);

        LogInPage objLogInPage = new LogInPage(driver);
        objLogInPage.logInProcedure(email, password);

        objMainPage.checkOpeningMainPage();
    }

    @Test
    @DisplayName("Проверка ошибки для некорректного пароля")
    public void checkSignInWithIncorrectPassword() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.signUp(name, email, incorrectPassword);

        SignUpPage objSignUpPage = new SignUpPage(driver);
        objSignUpPage.findMistakeIncorrectPassword();
    }

    @After
    public void tearDown() {
        try {
            given()
                    .auth().oauth2(getResponseAuthUserCard(createUserCard).getAccessToken().substring(7))
                    .delete("/api/auth/user");
        } catch (NullPointerException exception) { }

        try {
            given()
                    .auth().oauth2(getResponseAuthUserCard(createUserCardWithIncorrectPassword).getAccessToken().substring(7))
                    .delete("/api/auth/user");
        } catch (NullPointerException exception) { }

        driver.quit();
    }
}
