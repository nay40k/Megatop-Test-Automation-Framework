package by.megatop.pages;

import by.megatop.driver.WebDriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private static final Logger logger = LogManager.getLogger();

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final String HOME_URL = "https://megatop.by/";

    private final By COOKIE_ACCEPT_BUTTON = By.xpath("/html/body/div[1]/div/div[4]/div[1]/div/div[3]/div[3]/button[3]");
    private final By MEN_CATEGORY_LINK = By.xpath("//a[@href='/muzhchiny']");
    private final By REGION_CONFIRM_BUTTON = By.xpath("/html/body/div[3]/div[2]/div[1]/div/div/div[2]/button[1]");

    public HomePage() {

        this.driver = WebDriverSingleton.getInstance();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public HomePage open() {
        driver.get(HOME_URL);
        logger.info("Home page opened");
        return this;
    }

    public void confirmRegion() {
        try {
            WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(REGION_CONFIRM_BUTTON));
            confirmBtn.click();
        } catch (Exception e) {
            System.err.println("Region confirmation modal not found or not clickable.");
        }
    }

    public void acceptCookies() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(COOKIE_ACCEPT_BUTTON));
            button.click();
        } catch (Exception e) {
            System.err.println("Cookie banner not found or not clickable.");
        }
    }

    public String getMenCategoryText() {
        WebElement menLink = wait.until(ExpectedConditions.visibilityOfElementLocated(MEN_CATEGORY_LINK));
        return menLink.getText().trim();
    }
}
