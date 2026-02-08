package by.megatop.driver;

import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriver {
    private static org.openqa.selenium.WebDriver instance;

    private WebDriver() {
    }

    public static synchronized org.openqa.selenium.WebDriver getInstance() {
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
