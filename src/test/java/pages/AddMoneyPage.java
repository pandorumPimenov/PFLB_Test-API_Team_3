package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AddMoneyPage extends BasePage {
    private final SelenideElement userIdField = $("#id_send");
    private final SelenideElement moneyField = $("#money_send");
    private final SelenideElement pushButton = $("button.btn.btn-primary.tableButton");
    private final SelenideElement successMessage200 = $x("//button[normalize-space()='Status: Successfully pushed, code: 200']");
    private final SelenideElement incorrectMessage = $x("//button[normalize-space()='Status: Incorrect input data']");

    @Step("Пополнение баланса пользователя с ID {userId} на сумму {money}")
    public AddMoneyPage addMoney(String userId, String money) {
        setValue(userIdField, userId);
        setValue(moneyField, money);
        clickElement(pushButton);
        return this;
    }

    @Step("Получение сообщения об успешном добавлении денег")
    public String getSuccessMessage() {
        return getText(successMessage200);
    }

    @Step("Получение сообщения об ошибке")
    public String getIncorrectMessage() {
        return getText(incorrectMessage);
    }
}
