package by.megatop;

import by.megatop.driver.WebDriver;
import by.megatop.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class BaseUiTest {

    public HomePage homePage = new HomePage();
    protected final List<AssertionError> softErrors = new ArrayList<>();

    @BeforeEach
    void clickConfirmRegionAndCookieAcceptButtons() {
        homePage.open();
        homePage.clickRegionConfirmButton();
        homePage.clickCookieAcceptButton();
    }

    @AfterEach
    void closeBrowser() {
        WebDriver.close();
    }

}
