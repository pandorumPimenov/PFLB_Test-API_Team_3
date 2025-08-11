package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import wrappers.Input;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {
    private final SelenideElement submitButton = $x("//button[normalize-space(text()) = 'GO']");
    private final SelenideElement logoutButton = $x("//button[normalize-space(text()) = 'LOGOUT']");
    private final SelenideElement emailErrorMessage = $(By.xpath("//div[@style='color: red;'][1]"));
    private final SelenideElement passwordErrorMessage = $(By.xpath("//div[@style='color: red;'][2]"));

    @Step("Вход в систему с именем пользователя {user} и паролем {password}")
    public LoginPage login(String user, String password) {
        openPath("");
        new Input("email").writeLogin(user);
        new Input("password").writeLogin(password);
        clickElement(submitButton);
        return this;
    }

    @Step("Проверка всплывающего сообщения после входа")
    public String checkAlert() {
        return confirm();
    }

    public enum ErrorType {
        EMAIL, PASSWORD
    }

    public String checkErrorMessage(ErrorType errorType) {
        return switch (errorType) {
            case EMAIL -> emailErrorMessage.shouldBe(visible).getText();
            case PASSWORD -> passwordErrorMessage.shouldBe(visible).getText();
        };
    }
}
