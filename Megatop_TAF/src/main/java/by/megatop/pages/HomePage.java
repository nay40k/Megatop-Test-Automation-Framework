package by.megatop.pages;

import by.megatop.driver.WebDriver;
import by.megatop.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static by.megatop.enums.ClientCategory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePage {
    private static final Logger logger = LogManager.getLogger();

    private final org.openqa.selenium.WebDriver driver;
    private final WaitUtils waitUtils;

    private final String HOME_URL = "https://megatop.by/"; //Либо в BaseClass, либо в файл property

    private final By COOKIE_ACCEPT_BUTTON = By.xpath("//button[normalize-space() = 'Согласен']");
    private final By MEN_CATEGORY_LINK = By.xpath("//a[@href='/muzhchiny']");
    private final By WOMEN_CATEGORY_LINK = By.xpath("//a[@href='/zhenshchiny']");
    private final By CHILDREN_CATEGORY_LINK = By.xpath("//a[@href='/deti']");
    private final By REGION_CONFIRM_BUTTON = By.xpath("//div[@class='modal__content']//button[not(contains(@class, 'btn--outlined'))]");

    public HomePage() {

        this.driver = WebDriver.getInstance();
        this.waitUtils = new WaitUtils(driver);
    }

    public HomePage open() { //вынести в BaseTest
        driver.get(HOME_URL);
        logger.info("Home page opened");
        return this;
    }

    public void clickRegionConfirmButton() {
        try {
            waitUtils.clickWhenReady(REGION_CONFIRM_BUTTON);
        } catch (Exception e) {
            logger.warn("Region confirmation modal not found or not clickable.", e);
        }
    }

    public void clickCookieAcceptButton() {
        try {
            waitUtils.clickWhenReady(COOKIE_ACCEPT_BUTTON); // Используем утилиту
        } catch (Exception e) {
            logger.warn("Cookie banner not found or not clickable.", e);
        }
    }

    public String getMenCategoryText() {
        return waitUtils.getTextWhenVisible(MEN_CATEGORY_LINK); // Используем утилиту
    }

    public void testMenCategoryName() {
        assertEquals(MEN.getCategoryName(), getMenCategoryText(), "Текст категории \"" + MEN.getCategoryName() + "\" не совпадает!");
    }

    public String getWomenCategoryText() {
        return waitUtils.getTextWhenVisible(WOMEN_CATEGORY_LINK); // Используем утилиту
    }

    public void testWomenCategoryName() {
        assertEquals(WOMEN.getCategoryName(), getWomenCategoryText(), "Текст категории \"" + WOMEN.getCategoryName() + "\" не совпадает!");
    }

    public String getChildrenCategoryText() {
        return waitUtils.getTextWhenVisible(CHILDREN_CATEGORY_LINK); // Используем утилиту
    }

    public void testChildrenCategoryName() {
        String expected = CHILDREN.getCategoryName();
        String actual = getChildrenCategoryText();

        try {
            assertEquals(expected, actual, "Текст категории \"" + CHILDREN.getCategoryName() + "\" не совпадает!");
        } catch (AssertionError e) {
            logger.error("Тест провален: Текст категории \"" + CHILDREN.getCategoryName() + "\" не совпадает!", e);
            throw e;
        }
        logger.info("Тест пройден: Текст категории \"" + CHILDREN.getCategoryName() + "\" совпадает.");
    }
}
