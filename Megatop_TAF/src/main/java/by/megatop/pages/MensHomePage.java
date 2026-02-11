package by.megatop.pages;

import by.megatop.driver.Driver;
import by.megatop.utils.WaitUtils;
import org.openqa.selenium.By;

public class MensHomePage {
    private final WaitUtils waitUtils = new WaitUtils(Driver.getInstance());
    public static final String MEN_URL = "https://megatop.by/muzhchiny";

    private final By HEADER_LOGIN_BUTTON = By.xpath("//div[contains(@class, 'login') and contains(text(), 'Вход')]");
    private final By PHONE_INPUT = By.xpath("//input[@type='tel']");
    private final By PASSWORD_INPUT = By.xpath("//input[@type='password']");
    private final By SUBMIT_LOGIN_BUTTON = By.xpath("//button[contains(@class, 'btn') and contains(., 'Вход')]");
    private final By ERROR_MESSAGE = By.xpath("//div[contains(text(), 'неверный номер')]");

    public void open() {
        Driver.getInstance().get(MEN_URL);
    }

    public void openLoginModal() {
        waitUtils.clickWhenReady(HEADER_LOGIN_BUTTON);
    }

    public void fillLoginData(String phone, String password) {
        var phoneEl = waitUtils.waitForVisibilityOfElementLocated(PHONE_INPUT);
        phoneEl.clear();
        phoneEl.sendKeys(phone);
        var passEl = waitUtils.waitForVisibilityOfElementLocated(PASSWORD_INPUT);
        passEl.clear();
        passEl.sendKeys(password);
    }

    public void clickSubmit() {
        waitUtils.clickWhenReady(SUBMIT_LOGIN_BUTTON);
    }

    public String getErrorMessageText() {
        return waitUtils.getTextWhenVisible(ERROR_MESSAGE);
    }
}