package by.megatop.pages;

import by.megatop.driver.Driver;
import by.megatop.enums.ClientCategory;
import by.megatop.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver = Driver.getInstance();
    private final WaitUtils waitUtils = new WaitUtils(driver);

    private final By COOKIE_ACCEPT_BUTTON = By.xpath("//button[normalize-space() = 'Согласен']");
    private final By REGION_CONFIRM_BUTTON = By.xpath("//div[@class='modal__content']//button[not(contains(@class, 'btn--outlined'))]");
    private final By MEN_CATEGORY_LINK = By.xpath("//a[@href='/muzhchiny']");
    private final By WOMEN_CATEGORY_LINK = By.xpath("//a[@href='/zhenshchiny']");
    private final By CHILDREN_CATEGORY_LINK = By.xpath("//a[@href='/deti']");

    public void acceptCookies() {
        waitUtils.clickWhenReady(COOKIE_ACCEPT_BUTTON);
    }

    public void confirmRegion() {
        waitUtils.clickWhenReady(REGION_CONFIRM_BUTTON);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCategoryText(ClientCategory category) {
        return switch (category) {
            case MEN -> waitUtils.getTextWhenVisible(MEN_CATEGORY_LINK);
            case WOMEN -> waitUtils.getTextWhenVisible(WOMEN_CATEGORY_LINK);
            case CHILDREN -> waitUtils.getTextWhenVisible(CHILDREN_CATEGORY_LINK);
        };
    }
}