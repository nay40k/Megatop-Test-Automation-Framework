package by.megatop.api.tests;

import by.megatop.api.UserAuthService;
import by.megatop.utils.TestDataHelper;
import by.megatop.utils.UnicodeUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

public class LoginTest {
    private final UserAuthService authService = new UserAuthService();

    @Test
    @DisplayName("API 1: Неверный телефон и пароль")
    void testWrongCredentials() {
        Response response = authService.login(TestDataHelper.generateFullPhoneNumber(), TestDataHelper.generatePassword());
        verifyError(response, 422, "Вы ввели неверный номер");
    }

    @Test
    @DisplayName("API 2: Телефон без пароля")
    void testPhoneWithoutPassword() {
        Response response = authService.login(TestDataHelper.generateFullPhoneNumber(), "");
        verifyError(response, 422, "Вы ввели неверный номер");
    }

    @Test
    @DisplayName("API 3: Пустое тело запроса")
    void testEmptyBody() {
        Response response = authService.loginWithEmptyBody();
        verifyError(response, 500, "Server Error");
    }

    @Test
    @DisplayName("API 4: Без поля email")
    void testNoEmailField() {
        Response response = authService.login(null, "123456");
        verifyError(response, 500, "Server Error");
    }

    @Test
    @DisplayName("API 5: Без поля password")
    void testNoPasswordField() {
        Response response = authService.login("375251112233", null);
        verifyError(response, 500, "Server Error");
    }

    private void verifyError(Response response, int expectedCode, String expectedMessage) {
        String body = UnicodeUtils.decodeUnicodeEscapes(response.asString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedCode, response.getStatusCode()),
                () -> Assertions.assertTrue(body.contains(expectedMessage))
        );
    }
}