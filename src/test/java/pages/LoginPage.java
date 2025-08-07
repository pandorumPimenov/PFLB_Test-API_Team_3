package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {
    private final SelenideElement loginField = $("input[name='email']");
    private final SelenideElement passwordField = $("input[name='password']");
    private final SelenideElement submitButton = $x("//button[normalize-space(text()) = 'GO']");
    private final SelenideElement logoutButton = $x("//button[normalize-space(text()) = 'LOGOUT']");

    @Step("Вход в систему с именем пользователя {user} и паролем {password}")
    public LoginPage login(String user, String password) {
        openPath("");
        setValue(loginField, user);
        setValue(passwordField, password);
        clickElement(submitButton);
        return this;
    }

    @Step("Проверка всплывающего сообщения после входа")
    public String checkAlert() {
        return confirm();
    }
}
