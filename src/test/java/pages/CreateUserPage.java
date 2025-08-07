package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CreateUserPage extends BasePage {
    private final SelenideElement firstNameField = $("#first_name_send");
    private final SelenideElement lastNameField = $("#last_name_send");
    private final SelenideElement ageField = $("#age_send");
    private final SelenideElement maleRadio = $("input[value='MALE']");
    private final SelenideElement femaleRadio = $("input[value='FEMALE']");
    private final SelenideElement moneyField = $("#money_send");
    private final SelenideElement pushButton = $("button.btn.btn-primary.tableButton");
    private final SelenideElement successMessage201 = $x("//button[normalize-space()='Status: Successfully pushed, code: 201']");
    private final SelenideElement invalidMessage = $x("//button[normalize-space()='Status: Invalid request data']");

    @Step("Создание пользователя с данными: {firstName}, {lastName}, {age}, {sex}, {money}")
    public CreateUserPage createUser(String firstName, String lastName, String age, String sex, String money) {
        setValue(firstNameField, firstName);
        setValue(lastNameField, lastName);
        setValue(ageField, age);
        selectSex(sex);
        setValue(moneyField, money);
        clickElement(pushButton);
        return this;
    }

    @Step("Выбор пола: {sex}")
    private void selectSex(String sex) {
        if ("MALE".equalsIgnoreCase(sex)) {
            maleRadio.shouldBe(visible, enabled).click();
        } else if ("FEMALE".equalsIgnoreCase(sex)) {
            femaleRadio.shouldBe(visible, enabled).click();
        } else {
            throw new IllegalArgumentException("Неизвестный пол: " + sex);
        }
    }

    @Step("Получение сообщения об успешном создании пользователя")
    public String getSuccessMessage() {
        return getText(successMessage201);
    }

    @Step("Получение сообщения об ошибке")
    public String getErrorMessage() {
        return getText(invalidMessage);
    }
}
