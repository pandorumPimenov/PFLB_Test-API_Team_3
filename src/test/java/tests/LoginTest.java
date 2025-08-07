package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Авторизация с валидными данными")
    public void successfulLoginWithValidCredentials() {
        String alertMessage = loginPage.login(user, password)
                .checkAlert();

        assertEquals(alertMessage, "Successful authorization",
                "Сообщение об успешной авторизации не соответствует ожидаемому");
    }
}
