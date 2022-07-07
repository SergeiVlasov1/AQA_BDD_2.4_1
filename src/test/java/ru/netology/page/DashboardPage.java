package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final ElementsCollection cards = $$(".list__item div");
    private final SelenideElement firstCard = $$("[data-test-id=action-deposit]").first();
    private final SelenideElement secondCard = $$("[data-test-id=action-deposit]").last();

    public DashboardPage() {
    }

    public int getFirstCardBalance() {
        var text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        var text = cards.last().text();
        return extractBalance(text);
    }

    public TransferPage firstCard() {
        firstCard.click();
        return new TransferPage();
    }

    public TransferPage secondCard() {
        secondCard.click();
        return new TransferPage();
    }

    private int extractBalance(String text) {
        var arr = text.split(" ");
        return Integer.parseInt(arr[5]);
    }
}
