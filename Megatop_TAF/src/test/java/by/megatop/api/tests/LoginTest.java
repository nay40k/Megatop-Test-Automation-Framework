package by.megatop.api.tests;

import by.megatop.api.UserAuthService;
import by.megatop.utils.TestDataGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private static final Logger logger = LogManager.getLogger(LoginTest.class);
    private UserAuthService userAuthService;

    @BeforeEach
    void setUp() {
        userAuthService = new UserAuthService();
    }

    @Test
    @Disabled("Используется для отладки")
    @DisplayName("Проверка распечатки ответа")
    public void testResponsePrint() {
        logger.info("=== Тест: Проверка распечатки ответа (отладка) ===");

        String phone = TestDataGenerator.generateFullPhoneNumber();
        String password = TestDataGenerator.generatePassword();

        userAuthService.doLoginRequest(phone, password);

        logger.info("Ответ:\n{}", userAuthService.getResponseBody());
    }

    @Test
    @DisplayName("Проверка логина с неправильными телефоном и паролем")
    public void testLoginWithInvalidCredentials() {
        logger.info("=== Тест: Логин с неверными телефоном и паролем ===");

        String phone = TestDataGenerator.generateFullPhoneNumber();
        String password = TestDataGenerator.generatePassword();

        userAuthService.doLoginRequest(phone, password);
        int statusCode = userAuthService.getStatusCode();
        String responseBody = userAuthService.getResponseBody();
        String decodedResponse = userAuthService.getDecodedResponseBody();

        assertAll(
                "Проверка ответа 422 при неверных данных",
                () -> assertEquals(422, statusCode, "Ожидался статус 422"),
                () -> assertTrue(responseBody.contains("\"status\":\"error\""),
                        "Тело ответа должно содержать \"status\":\"error\""),
                () -> assertTrue(decodedResponse.contains("Вы ввели неверный номер телефона и/или пароль"),
                        "Сообщение должно содержать текст об ошибке")
        );

        logger.info("Тест пройден: Ответ 422 с корректным сообщением об ошибке");
    }

    @Test
    @DisplayName("Проверка логина с телефоном и пустым паролем")
    public void testLoginWithPhoneAndEmptyPassword() {
        logger.info("=== Тест: Логин с телефоном и пустым паролем ===");

        String phone = TestDataGenerator.generateFullPhoneNumber();
        String emptyPassword = "";

        userAuthService.doLoginRequest(phone, emptyPassword);
        int statusCode = userAuthService.getStatusCode();
        String responseBody = userAuthService.getResponseBody();
        String decodedResponse = userAuthService.getDecodedResponseBody();

        assertAll(
                "Проверка ответа 422 при пустом пароле",
                () -> assertEquals(422, statusCode, "Ожидался статус 422"),
                () -> assertTrue(responseBody.contains("\"status\":\"error\""),
                        "Тело ответа должно содержать \"status\":\"error\""),
                () -> assertTrue(decodedResponse.contains("Вы ввели неверный номер телефона и/или пароль"),
                        "Сообщение должно содержать текст об ошибке")
        );

        logger.info("Тест пройден: Ответ 422 при пустом пароле");
    }

    @Test
    @DisplayName("Проверка логина без телефона и пароля")
    public void testLoginWithoutPhoneAndPassword() {
        logger.info("=== Тест: Логин без телефона и пароля ===");

        String emptyPhone = "";
        String emptyPassword = "";

        userAuthService.doLoginRequest(emptyPhone, emptyPassword);
        int statusCode = userAuthService.getStatusCode();
        String responseBody = userAuthService.getResponseBody();
        String decodedResponse = userAuthService.getDecodedResponseBody();

        assertAll(
                "Проверка ответа 500 при пустых данных",
                () -> assertEquals(500, statusCode, "Ожидался статус 500"),
                () -> assertTrue(responseBody.contains("\"message\": \"Server Error\""),
                        "Тело ответа должно содержать \"message\": \"Server Error\""),
                () -> assertTrue(decodedResponse.contains("Server Error"),
                        "Сообщение должно содержать текст 'Server Error'")
        );

        logger.info("Тест пройден: Ответ 500 при пустых данных");
    }
}
