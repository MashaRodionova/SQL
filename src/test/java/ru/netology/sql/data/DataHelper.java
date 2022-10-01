package ru.netology.sql.data;


import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }
    private static Faker faker = new Faker(Locale.forLanguageTag("ru"));
    public static AuthInfo generateFakerAuthInfo() {
        return new AuthInfo(Integer.toString(faker.number().randomDigit()), faker.name().username(), faker.internet().password(), "ohMyGod");
    }


    public static VerificationInfo generateFakerCode(AuthInfo authInfo) {
        return new VerificationInfo(Integer.toString(faker.number().randomDigit()), Integer.toString(faker.number().randomDigit()));
    }





    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AuthInfo {
        private String id;
        private String login;
        private String password;
        private String status;


    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class VerificationInfo {
        private String code;
        private String userId;
    }

}


