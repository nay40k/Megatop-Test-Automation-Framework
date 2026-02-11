package by.megatop.api.tests;

import by.megatop.api.UserAuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class LoginTest {
    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    public UserAuthService userAuthService;

    @BeforeEach
    void setUp() {
        userAuthService = new UserAuthService();
    }

    @Test
    @Disabled("Используется для отладки")
    @DisplayName("Проверка распечатки ответа")
    public void testResponsePrint() {
        userAuthService.testPrintResponse();
    }

    @Test
    @DisplayName("Проверка логина с неправильными телефоном и паролем")
    public void test1() {
        userAuthService.testLoginWithPhoneAndPassword();
    }

    @Test
    @DisplayName("Проверка логина с телефоном и пустым паролем")
    public void test2() {
        userAuthService.testLoginWithPhoneAndWithoutPassword();
        logger.info("Проверка логина с телефоном и пустым паролем: ✅");
    }

    @Test
    @DisplayName("Проверка логина без телефона и пароля")
    public void test3() {
        userAuthService.testLoginWithoutPhoneAndPassword();
    }

    //TODO         Assertions.assertAll (для всех тестов)

}
