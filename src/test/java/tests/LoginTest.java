package tests;
import org.testng.annotations.Test;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest{

    @Test(testName = "Авторизация с валидными данными")
    public void successfulLoginWithValidCredentials() {
        String alertMessage = loginPage.login(user, password)
                .checkAlert();

        assertEquals(alertMessage, "Successful authorization",
                "Сообщение об успешной авторизации не соответствует ожидаемому");
    }

    @Test(testName = "Авторизация с пустым логином и паролем")
    public void checkLoginWithoutLoginAndPassword() {
        String alertMessage = loginPage.login("", "")
                .checkAlert();
        assertEquals(alertMessage, "Incorrect input data", "Сообщение об ошибке не соответствует ожидаемому");
    }

    @Test(testName = "Авторизация с невалидными данными")
    public void checkLoginWithNegativeValue() {
        String alertMessage = loginPage.login("test", "test")
                .checkAlert();
        assertEquals(alertMessage, "Incorrect input data", "Сообщение об ошибке не соответствует ожидаемому");
    }

    @Test(testName = "Авторизация с неверный форматом email (без @)")
    public void checkLoginWithNegativeValueEmail() {
        String alertMessage = loginPage.login("d.a.ru", password)
                .checkAlert();
        assertEquals(alertMessage, "Incorrect input data", "Сообщение об ошибке не соответствует ожидаемому");
        softAssert.assertEquals(loginPage.checkErrorMessage(LoginPage.ErrorType.EMAIL), "incorrect Email", "Сообщение об ошибке не соответствует ожидаемому");
    }

    @Test(testName = "Авторизация с паролем больше максимально допустимого количества символов (8)")
    public void checkLoginWithTooLongPassword() {
        String alertMessage = loginPage.login(user, "123456789")
                .checkAlert();
        assertEquals(alertMessage, "Incorrect input data", "Сообщение об ошибке не соответствует ожидаемому");
        softAssert.assertEquals(loginPage.checkErrorMessage(LoginPage.ErrorType.PASSWORD), "password length must be more than 3 symbols and less than 8 symbols", "Сообщение об ошибке не соответствует ожидаемому");
    }

    @Test(testName = "Авторизация с паролем больше максимально допустимого количества символов (8)")
    public void checkLoginWithTooShortPassword() {
        String alertMessage = loginPage.login(user, "12")
                .checkAlert();
        softAssert.assertEquals(alertMessage, "Incorrect input data", "Сообщение об ошибке не соответствует ожидаемому");
        softAssert.assertEquals(loginPage.checkErrorMessage(LoginPage.ErrorType.PASSWORD), "password length must be more than 3 symbols and less than 8 symbols", "Сообщение об ошибке не соответствует ожидаемому");
    }
}