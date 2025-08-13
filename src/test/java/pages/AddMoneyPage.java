package pages;

import com.codeborne.selenide.SelenideElement;
import dto.ui.Money;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import wrappers.Input;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class AddMoneyPage extends BasePage {
    private final SelenideElement userIdField = $("#id_send");
    private final SelenideElement moneyField = $("#money_send");
    private final SelenideElement pushButton = $("button.btn.btn-primary.tableButton");
    private final SelenideElement successMessage200 = $x("//button[normalize-space()='Status: " +
            "Successfully pushed, code: 200']");
    private final SelenideElement incorrectMessage = $x("//button[normalize-space()='Status: " +
            "Incorrect input data']");

    @Step("Пополнение баланса пользователя с ID {userId} на сумму {money}")
    public AddMoneyPage addMoney(Money money) {
        log.info("Starting money addition process for user ID: {}", money.getUserId());

        new Input("id_send").write(money.getUserId());
        log.info("Entered user ID: {}", money.getUserId());

        new Input("money_send").write(money.getMoney());
        log.info("Entered money amount: {}", money.getMoney());

        clickElement(pushButton);
        log.info("Clicked submit button");
        log.info("Money addition process completed");
        return this;
    }

    @Step("Получение сообщения об успешном добавлении денег")
    public String getSuccessMessage() {
        log.info("Checking for success message");
        return getText(successMessage200);
    }

    @Step("Получение сообщения об ошибке")
    public String getIncorrectMessage() {
        log.info("Checking for error message");
        return getText(incorrectMessage);
    }
}
