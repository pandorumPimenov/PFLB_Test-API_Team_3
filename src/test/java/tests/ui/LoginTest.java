package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;

@Owner("Андреев Дмитрий")
public class LoginTest extends BaseTest {

    @Test(testName = "Авторизация с валидными данными",
            description = "Проверка успешной авторизации с корректными учетными данными")
    @Description("Позитивный тест авторизации: ввод валидного email и пароля, " +
            "проверка сообщения об успешной авторизации")
    public void successfulLoginWithValidCredentials() {
        String alertMessage = loginPage.login(user, password)
                .checkAlert();

        assertEquals(alertMessage, "Successful authorization",
                "Сообщение об успешной авторизации не соответствует ожидаемому");
    }

    @DataProvider(name = "LoginTestCases")
    public Object[][] loginTestCases() {
        return new Object[][]{
                {"", "", "Incorrect input data", null, null},
                {"test", "test", "Incorrect input data", null, null},
                {"d.a.ru", "password123", "Incorrect input data", LoginPage.ErrorType.EMAIL, "incorrect Email"},
                {"valid@email.com", "123456789", "Incorrect input data", LoginPage.ErrorType.PASSWORD,
                        "password length must be more than 3 symbols and less than 8 symbols"},
                {"valid@email.com", "12", "Incorrect input data", LoginPage.ErrorType.PASSWORD,
                        "password length must be more than 3 symbols and less than 8 symbols"}
        };
    }

    @Test(dataProvider = "LoginTestCases", testName = "Негативные тесты авторизации")
    @Description("Тест #[{index}]: user={0}, password={1}, ожидаемый alert={2}")
    public void checkLoginNegativeCases(
            String user,
            String password,
            String expectedAlert,
            LoginPage.ErrorType errorType,
            String expectedErrorMessage) {

        SoftAssert softAssert = new SoftAssert();

        String alertMessage = loginPage.login(user, password).checkAlert();
        softAssert.assertEquals(alertMessage, expectedAlert, "Неверное сообщение в alert");

        if (errorType != null && expectedErrorMessage != null) {
            softAssert.assertEquals(
                    loginPage.checkErrorMessage(errorType),
                    expectedErrorMessage,
                    "Неверное сообщение об ошибке"
            );
            softAssert.assertAll();
        }
    }
}
