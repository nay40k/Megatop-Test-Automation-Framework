package by.megatop;

import by.megatop.driver.Driver;
import by.megatop.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseUiTest {
    protected HomePage homePage;

    @BeforeEach
    void setUp() {
        Driver.getInstance().get("https://megatop.by/");
        homePage = new HomePage();
        homePage.confirmRegion();
        homePage.acceptCookies();
    }

    @AfterEach
    void tearDown() {
        Driver.close();
    }
}