package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private static ElementsCollection cards = $$(".list__item div");
    private static SelenideElement firstCard = $$("[data-test-id=action-deposit]").first();
    private static SelenideElement secondCard = $$("[data-test-id=action-deposit]").last();

    public DashboardPage() {
    }

    public static int getFirstCardBalance() {
        var text = cards.first().text();
        return extractBalance(text);
    }

    public static int getSecondCardBalance() {
        var text = cards.last().text();
        return extractBalance(text);
    }

    public static TransferPage firstCard() {
        firstCard.click();
        return new TransferPage();
    }

    public static TransferPage secondCard() {
        secondCard.click();
        return new TransferPage();
    }

    private static int extractBalance(String text) {
        var arr = text.split(" ");
        return Integer.parseInt(arr[5]);
    }
}
