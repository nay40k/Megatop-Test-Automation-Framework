package by.megatop.utils;

import com.github.javafaker.Faker;

import java.util.Arrays;
import java.util.List;

public class TestDataGenerator {
    private static final Faker faker = new Faker();
    private static final List<String> VALID_CODES = Arrays.asList("25", "29", "33", "44");

    public static String generateFullPhoneNumber() {
        String code = VALID_CODES.get(faker.random().nextInt(VALID_CODES.size()));
        String suffix = String.format("%07d", faker.number().numberBetween(1000000, 9999999));
        return "375" + code + suffix;
    }

    public static String generatePhoneNumberWithoutCountryCode() {
        String code = VALID_CODES.get(faker.random().nextInt(VALID_CODES.size()));
        String suffix = String.format("%07d", faker.number().numberBetween(1000000, 9999999));
        return code + suffix;
    }

    public static String generatePassword() {
        return faker.internet().password(8, 16);
    }

    public static String removeCountryCode(String fullPhone) {
        if (fullPhone.startsWith("375")) {
            return fullPhone.substring(3);
        }
        return fullPhone;
    }
}
