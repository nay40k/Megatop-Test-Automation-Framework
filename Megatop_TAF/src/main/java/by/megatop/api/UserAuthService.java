package by.megatop.api;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class UserAuthService {
    private static final Logger logger = LogManager.getLogger(UserAuthService.class);
    private final String baseUrl = "https://api.megatop.by/prod/api/v1/user/login";

    public Response login(String email, String password) {
        Map<String, String> body = new HashMap<>();
        if (email != null) body.put("email", email);
        if (password != null) body.put("password", password);

        logger.info("API POST -> {}. Body: {}", baseUrl, body);

        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(baseUrl);
    }

    public Response loginWithEmptyBody() {
        return given()
                .header("Content-Type", "application/json")
                .body("{}")
                .when()
                .post(baseUrl);
    }
}