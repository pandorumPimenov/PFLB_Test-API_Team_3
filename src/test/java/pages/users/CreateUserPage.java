package pages.users;

import com.codeborne.selenide.SelenideElement;
import dto.ui.UserBuild;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.BasePage;
import wrappers.Input;
import wrappers.RadioButton;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class CreateUserPage extends BasePage {

    // Кнопки действий
    private final SelenideElement
            PUSH_BUTTON = $("button.btn.btn-primary.tableButton");

    // Сообщения о статусе операций
    private final SelenideElement
            SUCCESS_MESSAGE_201 = $x("//button[normalize-space()='Status: " +
                    "Successfully pushed, code: 201']"),
            INVALID_MESSAGE = $x("//button[normalize-space()='Status: " +
                    "Invalid request data']");

    @Step("Создание пользователя с данными: {firstName}, {lastName}, {age}, {sex}, {money}")
    public CreateUserPage createUser(UserBuild user, String sex) {
        log.info("Starting user creation process");
        log.info("User data - FirstName: {}, LastName: {}, Age: {}, Sex: {}, Money: {}",
                user.getFirstName(), user.getLastName(), user.getAge(), sex, user.getMoney());

        new Input("first_name_send").write(user.getFirstName());
        log.info("Entered first name");

        new Input("last_name_send").write(user.getLastName());
        log.info("Entered last name");

        new Input("age_send").write(user.getAge());
        log.info("Entered age");

        if (sex != null && !sex.trim().isEmpty()) {
            log.info("Selecting sex: {}", sex);
            new RadioButton(sex).checkUser();
        }

        new Input("money_send").write(user.getMoney());
        log.info("Entered money amount");
        clickElement(PUSH_BUTTON);

        log.info("Clicked submit button");
        return this;
    }

    @Step("Получение сообщения об успешном создании пользователя")
    public String getSuccessMessage() {
        log.info("Checking for success message");
        return getText(SUCCESS_MESSAGE_201);
    }

    @Step("Получение сообщения об ошибке")
    public String getErrorMessage() {
        log.info("Checking for error message");
        return getText(INVALID_MESSAGE);
    }
}
