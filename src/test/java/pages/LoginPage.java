package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import wrappers.Input;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class LoginPage extends BasePage {

    // Кнопки действий
    private final SelenideElement
            SUBMIT_BUTTON = $x("//button[normalize-space(text()) = 'GO']"),
            LOG_OUT_BUTTON = $x("//button[normalize-space(text()) = 'LOGOUT']");

    // Сообщения об ошибках валидации
    private final SelenideElement
            EMAIL_ERROR_MESSAGE = $(By.xpath("//div[@style='color: red;'][1]")),
            PASSWORD_ERROR_MESSAGE = $(By.xpath("//div[@style='color: red;'][2]"));

    @Step("Вход в систему с именем пользователя {user} и паролем {password}")
    public LoginPage login(String user, String password) {
        log.info("Starting login procedure");
        openPath("");
        log.info("Email entered");
        new Input("email").writeLogin(user);
        log.info("Password entered");
        new Input("password").writeLogin(password);
        log.info("Submit button clicked");
        clickElement(SUBMIT_BUTTON);
        return this;
    }

    @Step("Проверка всплывающего сообщения после входа")
    public String checkAlert() {
        log.info("Checking alert message");
        return confirm();
    }

    public String checkErrorMessage(ErrorType errorType) {
        log.info("Checking error message for: {}", errorType);
        String errorMessage = switch (errorType) {
            case EMAIL -> EMAIL_ERROR_MESSAGE.shouldBe(visible).getText();
            case PASSWORD -> PASSWORD_ERROR_MESSAGE.shouldBe(visible).getText();
        };
        log.info("Error message content: {}", errorMessage);
        return errorMessage;
    }

    public enum ErrorType {
        EMAIL, PASSWORD
    }
}
