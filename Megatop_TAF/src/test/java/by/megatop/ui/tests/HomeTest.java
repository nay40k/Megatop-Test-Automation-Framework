package by.megatop.ui.tests;

import by.megatop.BaseUiTest;
import by.megatop.enums.ClientCategory;
import org.junit.jupiter.api.*;

public class HomeTest extends BaseUiTest {

    @Test
    @DisplayName("Проверка заголовка главной страницы")
    void testHomePageTitle() {
        String expectedTitle = "Магазины обуви в Минске | Сеть обувных магазинов MEGATOP";
        Assertions.assertTrue(homePage.getPageTitle().contains(expectedTitle));
    }

    @Test
    @DisplayName("Проверка текстов категорий")
    void testCategoriesText() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(ClientCategory.MEN.getCategoryName(), homePage.getCategoryText(ClientCategory.MEN)),
                () -> Assertions.assertEquals(ClientCategory.WOMEN.getCategoryName(), homePage.getCategoryText(ClientCategory.WOMEN)),
                () -> Assertions.assertEquals(ClientCategory.CHILDREN.getCategoryName(), homePage.getCategoryText(ClientCategory.CHILDREN))
        );
    }
}