package tests;

import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Авторизация с валидными данными")
    public void successfulLoginWithValidCredentials() {
        String alertMessage = loginPage.login(user, password)
                .checkAlert();

        assertEquals(alertMessage, "Successful authorization",
                "Сообщение об успешной авторизации не соответствует ожидаемому");
    }

    @DataProvider(name = "LoginTestCases")
    public Object[][] loginTestCases() {
        return new Object[][]{
                // user, password, expectedAlert, errorType, expectedErrorMessage
                {"", "", "Incorrect input data", null, null},  // Простая проверка alert
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

        // Проверка основного сообщения
        String alertMessage = loginPage.login(user, password).checkAlert();
        assertEquals(alertMessage, expectedAlert, "Неверное сообщение в alert");

        // Проверка дополнительных ошибок (если указаны)
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
