package by.megatop.pages;

import by.megatop.driver.Driver;
import by.megatop.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

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

    private final String SITE_TITLE = "Магазины обуви в Минске | Сеть обувных магазинов MEGATOP - обувь большого города. \uD83C\uDFE2\uD83C\uDFE6\uD83C\uDFEA";

    public void confirmRegion() {
        logger.info("Подтверждение региона...");
        waitUtils.clickWhenReady(REGION_CONFIRM_BUTTON);
    }

    public void acceptCookies() {
        logger.info("Принятие куки...");
        waitUtils.clickWhenReady(COOKIE_ACCEPT_BUTTON);
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
