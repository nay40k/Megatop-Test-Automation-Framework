package by.megatop.ui.tests;

import by.megatop.BaseUiTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class HomeTest extends BaseUiTest {
    private static final Logger logger = LogManager.getLogger(HomeTest.class);

    @Test
    @DisplayName("Проверка заголовка главной страницы")
    @Tag("smoke")
    void testHomePageTitle() {
        logger.info("=== Тест: Проверка заголовка главной страницы ===");

        String actualTitle = homePage.getPageTitle();
        String expectedTitle = homePage.getExpectedTitle();

        Assertions.assertEquals(
                expectedTitle,
                actualTitle,
                "Заголовок главной страницы не соответствует ожидаемому"
        );

        logger.info("Тест пройден: Заголовок сайта соответствует '{}'", expectedTitle);
    }

    @Test
    @DisplayName("Проверка текстов категорий на главной странице")
    @Tag("smoke")
    void testCategoriesText() {
        logger.info("=== Тест: Проверка текстов категорий на главной странице ===");

        // Act & Assert
        Assertions.assertAll(
                "Проверка текстов категорий",
                () -> {
                    String actual = homePage.getMenCategoryText();
                    String expected = homePage.getExpectedMenCategoryText();
                    Assertions.assertEquals(expected, actual, "Текст категории 'Мужчины' не совпадает");
                    logger.debug("Категория 'Мужчины': '{}'", actual);
                },
                () -> {
                    String actual = homePage.getWomenCategoryText();
                    String expected = homePage.getExpectedWomenCategoryText();
                    Assertions.assertEquals(expected, actual, "Текст категории 'Женщины' не совпадает");
                    logger.debug("Категория 'Женщины': '{}'", actual);
                },
                () -> {
                    String actual = homePage.getChildrenCategoryText();
                    String expected = homePage.getExpectedChildrenCategoryText();
                    Assertions.assertEquals(expected, actual, "Текст категории 'Дети' не совпадает");
                    logger.info("Категория 'Дети': '{}'", actual);
                }
        );

        logger.info("Тест пройден: Все тексты категорий корректны");
    }
}
