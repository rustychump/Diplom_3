import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import pageobject.LogInPage;
import pageobject.ConstructorPage;

public class TestLogIn extends BaseTest {

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void checkLogInWithLogInButtonOnMainPage() {
        Assert.assertTrue(new ConstructorPage(driver)
                .logIn(email, password)
                .checkOpeningConstructorPage());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void checkLogInWithProfileButtonOnMainPage() {
        new ConstructorPage(driver).clickProfilePageButton();

        Assert.assertTrue(new LogInPage(driver)
                .logInProcedure(email, password)
                .checkOpeningConstructorPage());
    }

    @Test
    @DisplayName("Вход через ссылку в форме регистрации")
    public void checkLogInWithLogInLinkOnSignUpPage() {
        Assert.assertTrue(new ConstructorPage(driver)
                .clickLogInButton()
                .clickSignUpLink()
                .clickLogInLink()
                .logInProcedure(email, password)
                .checkOpeningConstructorPage());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void checkLogInWithLogInLinkOnRestorePasswordPage() {
        Assert.assertTrue(new ConstructorPage(driver)
                .clickLogInButton()
                .clickRestorePasswordLink()
                .clickLogInLink()
                .logInProcedure(email, password)
                .checkOpeningConstructorPage());
    }
}
