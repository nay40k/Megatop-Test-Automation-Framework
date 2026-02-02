package by.megatop.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSingleton {
    private static WebDriver instance;

    private WebDriverSingleton() {
    }

    public static synchronized WebDriver getInstance() {
        if (instance == null) {
            instance = new ChromeDriver();
            instance.manage().window().maximize();
        }
        return instance;
    }

    public static void close() {
        if (instance != null) {
            instance.quit();
            instance = null;
        }
    }
}
