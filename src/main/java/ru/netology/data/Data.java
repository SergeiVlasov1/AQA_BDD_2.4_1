package ru.netology.data;

import lombok.Value;

public class Data {

    private Data() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
    }

    public static CardInfo getFirstCardInfo() {
        return new CardInfo("5559000000000001");
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5559000000000002");
    }

    public static int decreaseBalance(int balance, int amount) {
        int newBalance = balance - amount;
        return newBalance;
    }

    public static int increaseBalance(int balance, int amount) {
        int newBalance = balance + amount;
        return newBalance;
    }
}
