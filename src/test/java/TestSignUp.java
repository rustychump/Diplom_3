import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.ConstructorPage;
import pageobject.SignUpPage;

import static io.restassured.RestAssured.given;

public class TestSignUp extends BaseTest {
    public TestSignUp(String browserChoice) {
        super(browserChoice);
    }

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
