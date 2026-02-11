package by.megatop.ui.tests;

import by.megatop.BaseUiTest;
import org.junit.jupiter.api.*;


public class HomeTest extends BaseUiTest {

    @Test
    @DisplayName("Проверка заголовка главной страницы")
    @Tag("smoke")
    @Order(2)
    void testHomePageTitle() {
        homePage.testHomePageTitle();
    }

    @Test
    @DisplayName("Проверка текстов категорий на главной странице")
    @Tag("smoke")
    @Order(1)
    void testCategoriesText() {
        Assertions.assertAll(
                () -> homePage.testMenCategoryName(),
                () -> homePage.testWomenCategoryName(),
                () -> homePage.testChildrenCategoryName()
        );
    }
}
