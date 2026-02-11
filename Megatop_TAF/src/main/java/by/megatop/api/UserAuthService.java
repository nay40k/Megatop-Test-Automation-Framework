package by.megatop.api;

import by.megatop.utils.UnicodeUtils;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserAuthService {
    private static final Logger logger = LogManager.getLogger(UserAuthService.class);

    private final String baseUrl = "https://api.megatop.by/";
    private final String loginEndpoint = "prod/api/v1/user/login";

    private final Faker faker = new Faker();
    private Response response;

    public void doLoginRequest(String phone, String password) {
        response = given()
                .headers(getHeaders())
                .body(getBody(phone, password))
                .when()
                .post(baseUrl + loginEndpoint);

        logger.info("Выполнен POST-запрос к: {}{}", baseUrl, loginEndpoint);
        logger.info("Сгенерированный телефон: {}", phone);
        logger.info("Запрос (body): {}", getBody(phone, "[HIDDEN]"));
        logger.info("Ответ (status): {}", response.getStatusCode());
        logger.info("Ответ (headers):\n{}", response.getHeaders().toString());
        logger.info("Ответ (body):\n{}", response.getBody().asString());
    }

    public String getResponseBody() {
        return response.getBody().asString();
    }

    public String getDecodedResponseBody() {
        String responseBody = response.getBody().asString();
        return UnicodeUtils.decodeUnicodeEscapes(responseBody).replace("\\/", "/");
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    private String getBody(String phone, String password) {
        return String.format("{\"email\":\"%s\",\"password\":\"%s\"}", phone, password);
    }

    private Map<String, Object> getHeaders() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("Accept", "application/json, text/plain, */*");
        headers.put("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.put("Content-Type", "application/json");
        headers.put("Cookie", "PHPSESSID=kibv4dmbtr3jug40uqstihnk2b");
        return headers;
    }
}
