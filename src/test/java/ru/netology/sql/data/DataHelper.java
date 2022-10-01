package ru.netology.sql.data;


import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }
    private static Faker faker = new Faker(Locale.forLanguageTag("ru"));
    public static AuthInfo generateFakerAuthInfo() {
        return new AuthInfo(faker.number().randomDigit(), faker.name().username(), faker.internet().password(), "ohMyGod");
    }


    public static VerificationInfo generateFakerCode(AuthInfo authInfo) {
        return new VerificationInfo(Integer.toString(faker.number().randomDigit()), faker.number().randomDigit());
    }





    @Value
    public static class AuthInfo {
        private int id;
        private String login;
        private String password;
        private String status;


    }

    @Value
    public static class VerificationInfo {
        private String code;
        private int userId;
    }

}


