package by.megatop.pages;

import by.megatop.driver.Driver;
import by.megatop.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static by.megatop.enums.ClientCategory.*;

public class HomePage {
    private static final Logger logger = LogManager.getLogger(HomePage.class);
    private final org.openqa.selenium.WebDriver driver = Driver.getInstance();
    private final WaitUtils waitUtils = new WaitUtils(driver);

    private final By COOKIE_ACCEPT_BUTTON = By.xpath("//div[contains(@class, 'cookie')]//div[contains(@class, 'justify-end')]//button[last()]");
    private final By MEN_CATEGORY_LINK = By.xpath("//a[@href='/muzhchiny']");
    private final By WOMEN_CATEGORY_LINK = By.xpath("//a[@href='/zhenshchiny']");
    private final By CHILDREN_CATEGORY_LINK = By.xpath("//a[@href='/deti']");
    private final By REGION_CONFIRM_BUTTON = By.xpath("//div[@class='modal__content']//button[not(contains(@class, 'btn--outlined'))]");


    private final By SEARCH_BUTTON = By.xpath("//div[contains(@class, 'search')]//div[normalize-space()='Поиск товара']");
    private final By SEARCH_INPUT = By.id("searchInput");
    private final By NO_PRODUCTS_MESSAGE = By.xpath("//div[contains(@class, 'text-color--main') and contains(@class, 'text-size--3') and normalize-space()='Отсутствуют товары']");

    private final String SITE_TITLE = "Магазины обуви в Минске | Сеть обувных магазинов MEGATOP - обувь большого города. \uD83C\uDFE2\uD83C\uDFE6\uD83C\uDFEA";

    public void confirmRegion() {
        logger.info("Подтверждение региона...");
        waitUtils.clickWhenReady(REGION_CONFIRM_BUTTON);
    }

    public void acceptCookies() {
        logger.info("Принятие куки...");
        waitUtils.clickWhenReady(COOKIE_ACCEPT_BUTTON);
    }

    public void openSearchModal() {
        logger.info("Открытие модального окна поиска...");
        waitUtils.clickWhenReady(SEARCH_BUTTON);
    }

    public void performSearch(String searchTerm) {
        logger.info("Выполнение поиска с запросом: {}", searchTerm);
        var searchElement = waitUtils.waitForVisibilityOfElementLocated(SEARCH_INPUT);
        searchElement.clear();
        searchElement.sendKeys(searchTerm);
        searchElement.sendKeys(Keys.ENTER);
    }

    public String getNoProductsMessageText() {
        return waitUtils.getTextWhenVisible(NO_PRODUCTS_MESSAGE);
    }

    public String getExpectedNoProductsMessage() {
        return "Отсутствуют товары";
    }

    public String getPageTitle() {
        String title = driver.getTitle();
        logger.debug("Заголовок страницы: {}", title);
        return title;
    }

    public String getExpectedTitle() {
        return SITE_TITLE;
    }

    public String getMenCategoryText() {
        return waitUtils.getTextWhenVisible(MEN_CATEGORY_LINK);
    }

    public String getExpectedMenCategoryText() {
        return MEN.getCategoryName();
    }

    public String getWomenCategoryText() {
        return waitUtils.getTextWhenVisible(WOMEN_CATEGORY_LINK);
    }

    public String getExpectedWomenCategoryText() {
        return WOMEN.getCategoryName();
    }

    public String getChildrenCategoryText() {
        return waitUtils.getTextWhenVisible(CHILDREN_CATEGORY_LINK);
    }

    public String getExpectedChildrenCategoryText() {
        return CHILDREN.getCategoryName();
    }
}
