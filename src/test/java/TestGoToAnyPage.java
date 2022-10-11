import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import pageobject.ConstructorPage;

public class TestGoToAnyPage extends BaseTest {
    public TestGoToAnyPage(String browserChoice) {
        super(browserChoice);
    }

    @Test
    @DisplayName("Проверка перехода в личный кабинет по клику на кнопку «Личный кабинет»")
    public void checkGoToProfilePage() {
        Assert.assertTrue(new ConstructorPage(driver)
                .logIn(email, password)
                .clickProfilePageButton()
                .checkOpeningProfilePage());
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор по клику на кноку «Конструктор»")
    public void checkGoToConstructorAfterClickConstructorButton() {
        Assert.assertTrue(new ConstructorPage(driver)
                .logIn(email, password)
                .clickProfilePageButton()
                .clickConstructorButton()
                .checkOpeningConstructorPage());
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void checkGoToConstructorAfterClickLogoStellarBurgers() {
        Assert.assertTrue(new ConstructorPage(driver)
                .logIn(email, password)
                .clickProfilePageButton()
                .clickLogoStellarBurgers()
                .checkOpeningConstructorPage());
    }
}
