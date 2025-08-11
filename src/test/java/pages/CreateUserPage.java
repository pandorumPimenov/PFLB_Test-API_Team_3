package pages;

import com.codeborne.selenide.SelenideElement;
import dto.UserBuild;
import io.qameta.allure.Step;
import wrappers.Checkbox;
import wrappers.Input;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CreateUserPage extends BasePage {
    private final SelenideElement pushButton = $("button.btn.btn-primary.tableButton");
    private final SelenideElement successMessage201 = $x("//button[normalize-space()='Status: Successfully pushed, code: 201']");
    private final SelenideElement invalidMessage = $x("//button[normalize-space()='Status: Invalid request data']");

    @Step("Создание пользователя с данными: {firstName}, {lastName}, {age}, {sex}, {money}")
    public CreateUserPage createUser(UserBuild user, String sex) {
        new Input("first_name_send").write(user.getFirstName());
        new Input("last_name_send").write(user.getLastName());
        new Input("age_send").write(user.getAge());
        new Input("age_send").write(user.getAge());
        new Checkbox(sex).checkUser();
        new Input("money_send").write(user.getMoney());
        clickElement(pushButton);
        return this;
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
