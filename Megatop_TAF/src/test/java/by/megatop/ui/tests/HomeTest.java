package by.megatop.ui.tests;

import by.megatop.BaseUiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class HomeTest extends BaseUiTest {

    @Test
    void testCategoriesText() {

        Assertions.assertAll(
                () -> homePage.testMenCategoryName(),
                () -> homePage.testWomenCategoryName(),
                () -> homePage.testChildrenCategoryName()
        );
    }
}
