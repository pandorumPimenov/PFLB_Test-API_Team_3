package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import wrappers.Input;
import wrappers.RadioButton;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class SettleOrEvictUserPage extends BasePage {

    // Кнопки действий
    private final SelenideElement
            PUSH_BUTTON = $("button.btn.btn-primary.tableButton");

    // Сообщения о статусе операций
    private final SelenideElement
            SUCCESS_MESSAGE = $x("//button[normalize-space()='Status: " +
            "Successfully pushed, code: 200']");

    private final SelenideElement
            ID_SEND_SOE_INPUT = $("#id_send");

    @Step("Открытие страницы заселения/выселения пользователя")
    public SettleOrEvictUserPage openSettleOrEvictPage() {
        log.info("Opening Settle/Evict user page");
        open(BASE_URL + "#/update/houseAndUser");
        ID_SEND_SOE_INPUT.shouldBe(visible, Duration.ofSeconds(60));
        return this;
    }

    @Step("Заселение пользователя {userId} в дом {houseId}")
    public SettleOrEvictUserPage settleUser(String userId, String houseId) {
        log.info("Settling user {} into house {}", userId, houseId);

        // Используем существующую обертку Input
        new Input("id_send").write(userId);
        new Input("house_send").write(houseId);

        // Используем существующую обертку RadioButton
        new RadioButton("settle").checkUser();

        log.info("Clicking submit button");
        clickElement(PUSH_BUTTON);
        return this;
    }

    @Step("Выселение пользователя {userId} из дома {houseId}")
    public SettleOrEvictUserPage evictUser(String userId, String houseId) {
        log.info("Evicting user {} from house {}", userId, houseId);

        new Input("id_send").write(userId);
        new Input("house_send").write(houseId);
        new RadioButton("evict").checkUser();

        log.info("Clicking submit button");
        clickElement(PUSH_BUTTON);
        return this;
    }

    @Step("Получение сообщения об успешном выполнении операции")
    public String getSuccessMessage() {
        log.info("Getting success message");
        String message = getText(SUCCESS_MESSAGE);
        log.info("Success message: {}", message);
        return message;
    }
}
