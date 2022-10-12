import cards.CreateUserCard;
import cards.ResponseAuthUserCard;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import static io.restassured.RestAssured.given;

public abstract class BaseTest {
    String name = "name";
    String email = "test@email.ru";
    String password = "password";
    CreateUserCard createUserCard = new CreateUserCard(email, password, name);
    String incorrectPassword = "123";
    CreateUserCard createUserCardWithIncorrectPassword = new CreateUserCard(email, incorrectPassword, name);
    protected static final String URL_STELLAR_BURGERS = "https://stellarburgers.nomoreparties.site/";
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



    @Before
    public void testPreparation() {
        RestAssured.baseURI = URL_STELLAR_BURGERS;

        given()
                .header("Content-type", "application/json")
                .body(createUserCard)
                .when()
                .post("/api/auth/register");

        driver = DriverFactory.getBrowser();
        driver.get(URL_STELLAR_BURGERS);
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
