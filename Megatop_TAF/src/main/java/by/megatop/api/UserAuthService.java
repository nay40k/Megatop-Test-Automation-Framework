package by.megatop.api;

import by.megatop.utils.UnicodeUtils;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserAuthService {
    private static final Logger logger = LogManager.getLogger(UserAuthService.class);

    private String baseUrl = "https://api.megatop.by/";
    private String loginEndpoint = "prod/api/v1/user/login";

    private final Faker faker = new Faker();
    private final List<String> VALID_CODES = Arrays.asList("25", "29", "33", "44");

    private Response response;

    public void doRequest(String email, String password) {
        response = given()
                .headers(getHeaders())
                .body(getBody(email, password))
                .when()
                .post(baseUrl + loginEndpoint);

        logger.info("Выполнен POST-запрос к: {}{}", baseUrl, loginEndpoint);
        logger.info("Сгенерированный телефон: {}", email);
        logger.info("Сгенерированный пароль: [HIDDEN]");
        logger.info("Запрос (body): {}", getBody(email, "[HIDDEN]"));
        logger.info("Ответ (status): {}", response.getStatusCode());
        logger.info("Ответ (headers):\n{}", response.getHeaders().toString());
        logger.info("Ответ (body):\n{}", response.getBody().asString());
    }

    private String getBody(String email, String password) {
        return String.format("""
                {"email":"%s","password":"%s"}
                """, email, password);
    }

    public Map<String, Object> getHeaders() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("Accept", "application/json, text/plain, */*");
        headers.put("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.put("Content-Type", "application/json");
        headers.put("Cookie", "PHPSESSID=kibv4dmbtr3jug40uqstihnk2b");
        return headers;
    }

    public void printResponse() {
        response.then().log().all();
    }

    public String generatePhoneNumber() {
        String code = VALID_CODES.get(faker.random().nextInt(VALID_CODES.size()));
        String suffix = String.format("%07d", faker.number().numberBetween(1000000, 9999999));
        return "375" + code + suffix;
    }

    public String generatePassword() {
        return faker.internet().password(8, 16);
    }

    public void testPrintResponse() {
        String phone = generatePhoneNumber();
        String password = generatePassword();
        doRequest(phone, password);
        printResponse();
    }

    public void testLoginWithPhoneAndPassword() {
        String phone = generatePhoneNumber();
        String password = generatePassword();
        doRequest(phone, password);
        assertErrorResponse422("Вы ввели неверный номер телефона и/или пароль");
    }

    public void testLoginWithPhoneAndWithoutPassword() {
        String phone = generatePhoneNumber();
        doRequest(phone, "");
        assertErrorResponse422("Вы ввели неверный номер телефона и/или пароль");
    }

    public void testLoginWithoutPhoneAndPassword() {
        doRequest("", "");
        assertErrorResponse500("Server Error");
    }

    private void assertErrorResponse422(String expectedMessage) {
        String responseBody = response.getBody().asString();
        String decodedResponseBody = UnicodeUtils.decodeUnicodeEscapes(responseBody).replace("\\/", "/");

        Assertions.assertAll("Проверка ответа 422",
                () -> Assertions.assertEquals(422, response.getStatusCode(), "Ожидался статус 422"),
                () -> Assertions.assertTrue(responseBody.contains("\"status\":\"error\""),
                        "Тело ответа не содержит \"status\":\"error\""),
                () -> Assertions.assertTrue(decodedResponseBody.contains(expectedMessage),
                        "Сообщение не содержит ожидаемый текст: '" + expectedMessage + "'\n" +
                                "Декодированный ответ: " + decodedResponseBody)
        );
    }

    private void assertErrorResponse500(String expectedMessage) {
        String responseBody = response.getBody().asString();
        String decodedResponseBody = UnicodeUtils.decodeUnicodeEscapes(responseBody).replace("\\/", "/");

        Assertions.assertAll("Проверка ответа 500",
                () -> Assertions.assertEquals(500, response.getStatusCode(), "Ожидался статус 500"),
                () -> Assertions.assertTrue(responseBody.contains("\"message\": \"Server Error\""),
                        "Тело ответа не содержит \"message\":\"Server Error\""),
                () -> Assertions.assertTrue(decodedResponseBody.contains(expectedMessage),
                        "Сообщение не содержит ожидаемый текст: '" + expectedMessage + "'\n" +
                                "Декодированный ответ: " + decodedResponseBody)
        );
    }
}