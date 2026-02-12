package by.megatop;

import by.megatop.driver.Driver;
import by.megatop.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseUiTest {
    protected HomePage homePage;
    public final String BASE_URL = "https://megatop.by/";

    @BeforeEach
    void clickRegionConfirmAndCookiesButton() {
        this.homePage = new HomePage();
        Driver.getInstance().get(BASE_URL);
        homePage.confirmRegion();
        homePage.acceptCookies();
    }

    @AfterEach
    void closeDriver() {
        Driver.close();
    }
}
