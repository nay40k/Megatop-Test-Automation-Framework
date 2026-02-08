// src/main/java/by/megatop/pages/HomePage.java
package by.megatop.pages;

import by.megatop.driver.WebDriverSingleton;
import by.megatop.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static by.megatop.enums.ClientCategory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePage {
    private static final Logger logger = LogManager.getLogger(HomePage.class); // Уточни имя класса

    private final org.openqa.selenium.WebDriver driver = WebDriverSingleton.getInstance();
    private final WaitUtils waitUtils = new WaitUtils(driver);

    private final By COOKIE_ACCEPT_BUTTON = By.xpath("//button[normalize-space() = 'Согласен']");
    private final By MEN_CATEGORY_LINK = By.xpath("//a[@href='/muzhchiny']");
    private final By WOMEN_CATEGORY_LINK = By.xpath("//a[@href='/zhenshchiny']");
    private final By CHILDREN_CATEGORY_LINK = By.xpath("//a[@href='/deti']");
    private final By REGION_CONFIRM_BUTTON = By.xpath("//div[@class='modal__content']//button[not(contains(@class, 'btn--outlined'))]");

    private final String SITE_TITLE = "Магазины обуви в Минске | Сеть обувных магазинов MEGATOP - обувь большого города. \uD83C\uDFE2\uD83C\uDFE6\uD83C\uDFEA";

    public HomePage() {}

    public void clickRegionConfirmButton() {
        try {
            waitUtils.clickWhenReady(REGION_CONFIRM_BUTTON);
        } catch (Exception e) {
            logger.warn("Region confirmation modal not found or not clickable.", e);
        }
    }

    public void clickCookieAcceptButton() {
        try {
            waitUtils.clickWhenReady(COOKIE_ACCEPT_BUTTON);
        } catch (Exception e) {
            logger.warn("Cookie banner not found or not clickable.", e);
        }
    }

    public void testHomePageTitle() {
        assertEquals(SITE_TITLE, driver.getTitle(), "Заголовок домашней страницы не соответствует");
        logger.info("Тест пройден: Заголовок сайта соответствует " + SITE_TITLE);
    }
    public String getMenCategoryText() {
        return waitUtils.getTextWhenVisible(MEN_CATEGORY_LINK);
    }

    public void testMenCategoryName() {
        assertEquals(MEN.getCategoryName(), getMenCategoryText(), "Текст категории \"" + MEN.getCategoryName() + "\" не совпадает!");
    }

    public String getWomenCategoryText() {
        return waitUtils.getTextWhenVisible(WOMEN_CATEGORY_LINK);
    }

    public void testWomenCategoryName() {
        assertEquals(WOMEN.getCategoryName(), getWomenCategoryText(), "Текст категории \"" + WOMEN.getCategoryName() + "\" не совпадает!");
    }

    public String getChildrenCategoryText() {
        return waitUtils.getTextWhenVisible(CHILDREN_CATEGORY_LINK);
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
        logger.info("i: Логирование добавлено только для категории \"Дети\" исключительно для демонстрации логирования");
        logger.info("Тест пройден: Текст категории \"" + CHILDREN.getCategoryName() + "\" совпадает.");
    }
}