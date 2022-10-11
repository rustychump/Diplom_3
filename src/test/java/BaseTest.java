import cards.CreateUserCard;
import cards.ResponseAuthUserCard;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
public abstract class BaseTest {
    String name = "name";
    String email = "test@email.ru";
    String password = "password";
    CreateUserCard createUserCard = new CreateUserCard(email, password, name);
    String incorrectPassword = "123";
    CreateUserCard createUserCardWithIncorrectPassword = new CreateUserCard(email, incorrectPassword, name);
    protected String browserChoice;
    protected final String urlStellarBurgers = "https://stellarburgers.nomoreparties.site/";
    protected String bunsText = "Булки";
    protected String saucesText = "Соусы";
    protected String fillingsText = "Начинки";
    protected WebDriver driver;

    protected ResponseAuthUserCard getResponseAuthUserCard(CreateUserCard userCard) {

        return given()
                .header("Content-type", "application/json")
                .body(userCard)
                .when()
                .post("/api/auth/login")
                .body().as(ResponseAuthUserCard.class);
    }

    public BaseTest(String browserChoice) {
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
