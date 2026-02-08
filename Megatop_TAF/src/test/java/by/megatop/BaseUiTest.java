// src/main/java/by/megatop/BaseUiTest.java
package by.megatop; // Убедись, что пакет правильный

import by.megatop.driver.WebDriver;
import by.megatop.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseUiTest {

    protected HomePage homePage;
    public final String BASE_URL = "https://megatop.by/";

    @BeforeEach
    void setUp() {
        this.homePage = new HomePage();
        WebDriver.getInstance().get(BASE_URL);
        homePage.clickRegionConfirmButton();
        homePage.clickCookieAcceptButton();
    }

    @AfterEach
    void tearDown() {
        WebDriver.close();
    }
}