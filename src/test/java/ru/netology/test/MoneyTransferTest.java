package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Data;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void auth() {
        open("http://localhost:9999", LoginPage.class);
        val loginPage = new LoginPage();
        val authInfo = Data.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = Data.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyFromFirstCardToSecond() {
        int amount = 4000;
        int firstCardBalance = DashboardPage.getFirstCardBalance();
        int secondCardBalance = DashboardPage.getSecondCardBalance();
        var transferPage = DashboardPage.secondCard();
        var cardInfo = Data.getFirstCardInfo();
        transferPage.transferCard(cardInfo, amount);
        int firstCardAfterTransferBalance = Data.decreaseBalance(firstCardBalance, amount);
        int secondCardAfterTransferBalance = Data.increaseBalance(secondCardBalance, amount);
        int firstCardBalanceAfter = DashboardPage.getFirstCardBalance();
        int secondCardBalanceAfter = DashboardPage.getSecondCardBalance();
        assertEquals(firstCardAfterTransferBalance, firstCardBalanceAfter);
        assertEquals(secondCardAfterTransferBalance, secondCardBalanceAfter);
    }

    @Test
    void shouldTransferMoneyFromSecondCardToFirst() {
        int amount = 4000;
        int firstCardBalance = DashboardPage.getFirstCardBalance();
        int secondCardBalance = DashboardPage.getSecondCardBalance();
        var transferPage = DashboardPage.firstCard();
        var cardInfo = Data.getSecondCardInfo();
        transferPage.transferCard(cardInfo, amount);
        int firstCardAfterTransferBalance = Data.increaseBalance(firstCardBalance, amount);
        int secondCardAfterTransferBalance = Data.decreaseBalance(secondCardBalance, amount);
        int firstCardBalanceAfter = DashboardPage.getFirstCardBalance();
        int secondCardBalanceAfter = DashboardPage.getSecondCardBalance();
        assertEquals(firstCardAfterTransferBalance, firstCardBalanceAfter);
        assertEquals(secondCardAfterTransferBalance, secondCardBalanceAfter);
    }

    @Test
    void shouldNotTransferMoneyAndShowErrorWhenAmountIsMoreThanAvailableBalance() {
        int amount = 11000;
        DashboardPage.getFirstCardBalance();
        DashboardPage.getSecondCardBalance();
        var transferPage = DashboardPage.firstCard();
        var cardInfo = Data.getSecondCardInfo();
        transferPage.transferCard(cardInfo, amount);
        transferPage.transferMoneyError();
    }
}
