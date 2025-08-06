package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends  BasePage{
    private final By LOGIN_FIELD = By.xpath("//input[@name='email']");
    private final By PASSWORD_FIELD = By.xpath("//input[@name='password']");
    private final By SIGN_UP_BUTTON = By.xpath("//button[normalize-space(text()) = 'GO']");

    @Step("Вход в систему с именем пользователя {user} и паролем {password}")
    public void login(String user, String password) {
        open(BASE_URL);
        $(LOGIN_FIELD).setValue(user);
        $(PASSWORD_FIELD).setValue(password);
        $(SIGN_UP_BUTTON).click();
    }
    public String checkAlert() {
        return confirm();
    }
}
