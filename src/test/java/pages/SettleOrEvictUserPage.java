package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class SettleOrEvictUserPage extends BasePage {

    private final SelenideElement USER_ID_FIELD = $x("//input[@id='id_send']");
    private final SelenideElement HOUSE_ID_FIELD = $("input[id='house_send']");
    private final SelenideElement SETTLE_RADIO = $("input[value='settle']");
    private final SelenideElement EVICT_RADIO = $("input[value='evict']");
    private final SelenideElement PUSH_BUTTON = $("button.btn.btn-primary.tableButton");
    private final SelenideElement SUCCESS_MESSAGE = $x("//button[normalize-space()='Status: Successfully pushed, code: 200']");

    @Step("Открытие страницы заселения/выселения пользователя")
    public SettleOrEvictUserPage openSettleOrEvictPage() {
        open(BASE_URL + "#/update/houseAndUser");
        return this;
    }

    @Step("Заселение пользователя в дом")
    public SettleOrEvictUserPage settleUser(String send_id, String house_id) {
        USER_ID_FIELD.setValue(send_id);
        HOUSE_ID_FIELD.setValue(house_id);
        SETTLE_RADIO.click();
        PUSH_BUTTON.click();
        return this;
    }

    @Step("Выселение пользователя из дома")
    public SettleOrEvictUserPage evictUser(String send_id, String house_id) {
        USER_ID_FIELD.setValue(send_id);
        HOUSE_ID_FIELD.setValue(house_id);
        EVICT_RADIO.click();
        PUSH_BUTTON.click();
        return this;
    }

    @Step("Получение сообщения об успешном заселении пользователя")
    public SettleOrEvictUserPage getSuccessMessage() {
        getText(SUCCESS_MESSAGE);
        return this;
    }
}