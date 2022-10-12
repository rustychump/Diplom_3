import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import pageobject.ConstructorPage;

public class TestSignOut extends BaseTest {

    @Test
    @DisplayName("Проверка выхода по кнопке «Выйти» в личном кабинете")
    public void checkLogOutWithSignOutButtonFromProfilePage() {
        Assert.assertTrue(
                new ConstructorPage(driver)
                .logIn(email, password)
                .clickProfilePageButton()
                .clickLogOutButton()
                .checkOpeningLogInPage());
    }
}
