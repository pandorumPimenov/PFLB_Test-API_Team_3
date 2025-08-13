package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class SettleOrEvictUserPage extends BasePage {
    private final SelenideElement userIdField = $x("//input[@id='id_send']");
    private final SelenideElement houseIdField = $("input[id='house_send']");
    private final SelenideElement settleRadio = $("input[value='settle']");
    private final SelenideElement evictRadio = $("input[value='evict']");
    private final SelenideElement pushButton = $("button.btn.btn-primary.tableButton");
    private final SelenideElement successMessage = $x("//button[normalize-space()='Status: Successfully pushed, code: 200']");

    @Step("Открытие страницы заселения/выселения пользователя")
        public SettleOrEvictUserPage openSettleOrEvictPage() {
        open(BASE_URL + "#/update/houseAndUser");
        return this;
    }

    @Step("Заселение пользователя в дом")
    public SettleOrEvictUserPage settleUser (String send_id, String house_id) {
    setValue(userIdField, send_id);
    setValue(houseIdField, house_id);
    settleRadio.click();
    pushButton.click();
    return this;
    }

    @Step("Выселение пользователя из дома")
    public SettleOrEvictUserPage evictUser (String send_id, String house_id) {
        setValue(userIdField, send_id);
        setValue(houseIdField, house_id);
        evictRadio.click();
        pushButton.click();
        return this;
    }

    @Step("Получение сообщения об успешном заселении пользователя")
    public SettleOrEvictUserPage getSuccessMessage() {
        getText(successMessage);
        return this;
    }
}
