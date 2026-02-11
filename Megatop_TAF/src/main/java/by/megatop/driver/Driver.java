package by.megatop.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
    private static org.openqa.selenium.WebDriver instance;

    private Driver() {
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
