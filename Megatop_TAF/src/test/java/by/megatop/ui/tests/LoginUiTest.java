package by.megatop.ui.tests;

import by.megatop.BaseUiTest;
import by.megatop.pages.MensHomePage;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LoginUiTest extends BaseUiTest {

    private final Faker faker = new Faker();
    private final List<String> VALID_CODES = Arrays.asList("25", "29", "33", "44");

    @Test
    @DisplayName("Проверка ошибки при входе с неверными данными на странице Мужчины")
    void testLoginFailureOnMensPage() {
        MensHomePage mensPage = new MensHomePage();
        mensPage.open();
        mensPage.openLoginModal();

        String fullPhone = generateFullPhoneNumber();
        String phoneForInput = getPhoneWithoutCountryCode(fullPhone);
        String password = faker.internet().password(8, 16);
        mensPage.fillLoginData(phoneForInput, password);

        mensPage.clickSubmitButton();
        String actualError = mensPage.getErrorMessageText();
        String expectedError = "Вы ввели неверный номер телефона и/или пароль.";

        Assertions.assertTrue(actualError.contains(expectedError),
                String.format("Текст ошибки не совпадает.\nОжидалось: %s\nФактически: %s", expectedError, actualError));
    }

    private String generateFullPhoneNumber() {
        String code = VALID_CODES.get(faker.random().nextInt(VALID_CODES.size()));
        String suffix = String.format("%07d", faker.number().numberBetween(1000000, 9999999));
        return "375" + code + suffix;
    }

    private String getPhoneWithoutCountryCode(String fullPhone) {
        if (fullPhone.startsWith("375")) {
            return fullPhone.substring(3);
        }
        return fullPhone;
    }
}