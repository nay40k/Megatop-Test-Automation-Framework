package by.megatop.ui.tests;

import by.megatop.BaseUiTest;
import by.megatop.pages.MensHomePage;
import by.megatop.utils.TestDataGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginUiTest extends BaseUiTest {
    private static final Logger logger = LogManager.getLogger(LoginUiTest.class);

    @Test
    @DisplayName("Проверка ошибки при входе с неверными данными на странице Мужчины")
    void testLoginFailureOnMensPage() {
        logger.info("=== Тест: Проверка ошибки при входе с неверными данными ===");

        // Arrange
        MensHomePage mensPage = new MensHomePage();
        String phone = TestDataGenerator.generatePhoneNumberWithoutCountryCode();
        String password = TestDataGenerator.generatePassword();
        String expectedError = mensPage.getExpectedLoginErrorMessage();

        logger.info("Сгенерированные данные: телефон={}, пароль=[HIDDEN]", phone);

        // Act
        mensPage.open();
        mensPage.openLoginModal();
        mensPage.fillLoginData(phone, password);
        mensPage.clickSubmitButton();
        String actualError = mensPage.getErrorMessageText();

        // Assert
        Assertions.assertTrue(
                actualError.contains(expectedError),
                String.format("Текст ошибки не совпадает.\nОжидалось: %s\nФактически: %s", expectedError, actualError)
        );

        logger.info("Тест пройден: Сообщение об ошибке корректно - '{}'", actualError);
    }
}
