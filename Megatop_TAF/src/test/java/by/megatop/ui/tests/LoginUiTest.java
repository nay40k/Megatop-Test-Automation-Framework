package by.megatop.ui.tests;

import by.megatop.BaseUiTest;
import by.megatop.pages.MensHomePage;
import by.megatop.utils.TestDataHelper;
import org.junit.jupiter.api.*;

public class LoginUiTest extends BaseUiTest {

    @Test
    @DisplayName("Проверка ошибки при входе с неверными данными на странице Мужчины")
    void testLoginFailureOnMensPage() {
        MensHomePage mensPage = new MensHomePage();
        mensPage.open();
        mensPage.openLoginModal();

        String phone = TestDataHelper.getPhoneWithoutCountryCode(TestDataHelper.generateFullPhoneNumber());
        mensPage.fillLoginData(phone, TestDataHelper.generatePassword());
        mensPage.clickSubmit();

        String actualError = mensPage.getErrorMessageText();
        Assertions.assertTrue(actualError.contains("Вы ввели неверный номер телефона и/или пароль"));
    }
}