import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Test;
import pageobject.ConstructorPage;
import pageobject.SignUpPage;

import static io.restassured.RestAssured.given;

public class TestSignUp extends BaseTest {

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void checkSuccessfulSignUp() {
        Assert.assertTrue(new ConstructorPage(driver)
                .signUp(name, email, password)
                .logInProcedure(email, password)
                .checkOpeningConstructorPage());
    }

    @Test
    @DisplayName("Проверка ошибки для некорректного пароля")
    public void checkSignUpWithIncorrectPassword() {
        new ConstructorPage(driver)
                .signUp(name, email, incorrectPassword);

        Assert.assertTrue(new SignUpPage(driver).findMistakeIncorrectPassword());
    }

    @Override
    public void testPreparation() {
        RestAssured.baseURI = URL_STELLAR_BURGERS;

        driver = DriverFactory.getBrowser();
        driver.get(URL_STELLAR_BURGERS);
    }

    @Override
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
