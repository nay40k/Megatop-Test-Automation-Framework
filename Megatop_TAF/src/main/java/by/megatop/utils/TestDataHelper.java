package by.megatop.utils;

import com.github.javafaker.Faker;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestDataHelper {
    private static final Faker faker = new Faker();
    private static final List<String> VALID_CODES = Arrays.asList("25", "29", "33", "44");

    public static String generateFullPhoneNumber() {
        String code = VALID_CODES.get(new Random().nextInt(VALID_CODES.size()));
        String suffix = String.format("%07d", faker.number().numberBetween(1000000, 9999999));
        return "375" + code + suffix;
    }

    public static String getPhoneWithoutCountryCode(String fullPhone) {
        return fullPhone.substring(3);
    }

    public static String generatePassword() {
        return faker.internet().password(8, 16);
    }
}