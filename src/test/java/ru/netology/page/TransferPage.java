package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.Data;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement messageError = $("[data-test-id= 'error-notification']");

    public void transferCard(Data.CardInfo CardInfo, int amountToTransfer) {
        amountField.setValue(String.valueOf(amountToTransfer));
        fromField.setValue(CardInfo.getCardNumber());
        transferButton.click();
    }

    public void transferMoneyError() {
        messageError.shouldBe(Condition.visible);
    }
}
