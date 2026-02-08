package by.megatop.ui.tests;

import by.megatop.BaseUiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


public class HomeTest extends BaseUiTest {

    @Test
    @DisplayName("Проверка заголовка главной страницы")
    @Tag("smoke")
    void testHomePageTitle() {
        homePage.testHomePageTitle();
    }

    @Test
    @DisplayName("Проверка текстов категорий на главной странице")
    @Tag("smoke")
    void testCategoriesText() {
        Assertions.assertAll(
                () -> homePage.testMenCategoryName(),
                () -> homePage.testWomenCategoryName(),
                () -> homePage.testChildrenCategoryName()
        );
    }
}
