import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver getBrowser() {
        String browserName;
        try {
            browserName = System.getProperty("browser");
        } catch (RuntimeException e) {
            browserName = "chrome";
        }

        switch (browserName) {
            case "yandex": {
                System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/chromedriver_yandex.exe");
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:/Program Files (x86)/Yandex/YandexBrowser/Application/browser.exe");
                return new ChromeDriver(options);
            }
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/chromedriver.exe");
                return new ChromeDriver();
            }
            default: throw new RuntimeException("Только Хром и Яндекс!");
        }
    }
}
