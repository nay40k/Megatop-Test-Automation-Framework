package by.megatop.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final Logger logger = LogManager.getLogger(WaitUtils.class);
    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 секунд по умолчанию
    }

    public WebElement waitForElementToBeClickable(By locator) {
        logger.debug("Ожидаем, что элемент по локатору '{}' станет кликабельным.", locator);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        logger.debug("Элемент по локатору '{}' стал кликабельным.", locator);
        return element;
    }

    public WebElement waitForVisibilityOfElementLocated(By locator) {
        logger.debug("Ожидаем, что элемент по локатору '{}' станет видимым.", locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.debug("Элемент по локатору '{}' стал видимым.", locator);
        return element;
    }

    public void clickWhenReady(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        logger.debug("Кликаем по элементу с локатором '{}'.", locator);
        element.click();
    }

    public String getTextWhenVisible(By locator) {
        WebElement element = waitForVisibilityOfElementLocated(locator);
        String text = element.getText();
        logger.debug("Получен текст '{}' из элемента по локатору '{}'.", text, locator);
        return text.trim();
    }

    public boolean waitForInvisibilityOfElementLocated(By locator) {
        logger.debug("Ожидаем, что элемент по локатору '{}' станет невидимым или исчезнет.", locator);
        boolean result = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        logger.debug("Элемент по локатору '{}' стал невидимым или исчез.", locator);
        return result;
    }

    public boolean waitForTextToBePresentInElementLocated(By locator, String text) {
        logger.debug("Ожидаем, что текст '{}' появится в элементе по локатору '{}'.", text, locator);
        boolean result = wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        logger.debug("Текст '{}' появился в элементе по локатору '{}'.", text, locator);
        return result;
    }
}
