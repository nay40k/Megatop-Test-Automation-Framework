package by.megatop.pages.tests;

import by.megatop.driver.WebDriverSingleton;
import by.megatop.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeTest {

    private HomePage homePage;

    @BeforeEach
    void setUp() {
        homePage = new HomePage();
        homePage.open();
        homePage.confirmRegion();
        homePage.acceptCookies();
    }

    @Test
    void verifyMenCategoryText() {
        String actualText = homePage.getMenCategoryText();
        assertEquals("Мужчины", actualText, "Текст категории 'Мужчины' не совпадает!");
    }

    @AfterEach
    void tearDown() {
        WebDriverSingleton.close();
    }
}
