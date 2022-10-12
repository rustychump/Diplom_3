import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Test;
import pageobject.ConstructorSection;

public class TestConstructorSection extends BaseTest {

    @Test
    @DisplayName("Проверка, что работает переход к разделу «Булки»")
    public void checkGoToBunSection() {
        Assert.assertEquals(fillingsText, new ConstructorSection(driver).clickFillingsButton().getTextActiveButton());

        Assert.assertEquals(bunsText, new ConstructorSection(driver).clickBunsButton().getTextActiveButton());
    }

    @Test
    @DisplayName("Проверка, что работает переход к разделу «Соусы»")
    public void checkGoToSaucesSection() {
        Assert.assertEquals(saucesText, new ConstructorSection(driver).clickSaucesButton().getTextActiveButton());
    }

    @Test
    @DisplayName("Проверка, что работает переход к разделу «Начинки»")
    public void checkGoToFillingsSection() {
        Assert.assertEquals(fillingsText, new ConstructorSection(driver).clickFillingsButton().getTextActiveButton());
    }

    @Override
    public void testPreparation() {
        RestAssured.baseURI = URL_STELLAR_BURGERS;

        driver = DriverFactory.getBrowser();
        driver.get(URL_STELLAR_BURGERS);
    }

    @Override
    public void tearDown() {
        driver.quit();
    }
}
