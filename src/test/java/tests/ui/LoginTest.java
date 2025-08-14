package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Авторизация с валидными данными",
            description = "Проверка успешной авторизации с корректными учетными данными")
    @Owner("Андреев Дмитрий")
    @Description("Позитивный тест авторизации: ввод валидного email и пароля, " +
            "проверка сообщения об успешной авторизации")
    public void successfulLoginWithValidCredentials() {
        String alertMessage = loginPage.login(user, password)
                .checkAlert();

        assertEquals(alertMessage, "Successful authorization",
                "Сообщение об успешной авторизации не соответствует ожидаемому");
    }

    @Test(testName = "Авторизация с пустым логином и паролем",
            description = "Проверка авторизации без ввода учетных данных")
    @Owner("Андреев Дмитрий")
    @Description("Негативный тест: попытка авторизации с пустыми полями email и пароля, проверка сообщения об ошибке")
    public void checkLoginWithoutLoginAndPassword() {
        String alertMessage = loginPage.login("", "")
                .checkAlert();
        assertEquals(alertMessage, "Incorrect input data",
                "Сообщение об ошибке не соответствует ожидаемому");
    }

    @Test(testName = "Авторизация с невалидными данными",
            description = "Проверка авторизации с некорректными учетными данными")
    @Owner("Андреев Дмитрий")
    @Description("Негативный тест: ввод случайных невалидных значений в поля email и " +
            "пароля, проверка сообщения об ошибке")
    public void checkLoginWithNegativeValue() {
        String alertMessage = loginPage.login("test", "test")
                .checkAlert();
        assertEquals(alertMessage, "Incorrect input data",
                "Сообщение об ошибке не соответствует ожидаемому");
    }

    @Test(testName = "Авторизация с неверный форматом email (без @)",
            description = "Проверка авторизации с email в неверном формате")
    @Owner("Андреев Дмитрий")
    @Description("Негативный тест: ввод email без символа @, проверка сообщения об ошибке и валидации поля email")
    public void checkLoginWithNegativeValueEmail() {
        String alertMessage = loginPage.login("d.a.ru", password)
                .checkAlert();
        assertEquals(alertMessage, "Incorrect input data",
                "Сообщение об ошибке не соответствует ожидаемому");
        softAssert.assertEquals(loginPage.checkErrorMessage(LoginPage.ErrorType.EMAIL),
                "incorrect Email",
                "Сообщение об ошибке не соответствует ожидаемому");
    }

    @Test(testName = "Авторизация с паролем больше максимально допустимого количества символов (8)",
            description = "Проверка авторизации с паролем длиннее 8 символов")
    @Owner("Андреев Дмитрий")
    @Description("Негативный тест: ввод пароля длиной 9 символов, проверка сообщения об ошибке и валидации поля пароля")
    public void checkLoginWithTooLongPassword() {
        String alertMessage = loginPage.login(user, "123456789")
                .checkAlert();
        assertEquals(alertMessage, "Incorrect input data",
                "Сообщение об ошибке не соответствует ожидаемому");
        softAssert.assertEquals(loginPage.checkErrorMessage(LoginPage.ErrorType.PASSWORD),
                "password length must be more than 3 symbols and less than 8 symbols",
                "Сообщение об ошибке не соответствует ожидаемому");
    }

    @Test(testName = "Авторизация с паролем меньше минимально допустимого количества символов (3)",
            description = "Проверка авторизации с паролем короче 3 символов")
    @Owner("Андреев Дмитрий")
    @Description("Негативный тест: ввод пароля длиной 2 символа, проверка сообщения об ошибке и валидации поля пароля")
    public void checkLoginWithTooShortPassword() {
        String alertMessage = loginPage.login(user, "12")
                .checkAlert();
        softAssert.assertEquals(alertMessage,
                "Incorrect input data",
                "Сообщение об ошибке не соответствует ожидаемому");
        softAssert.assertEquals(loginPage.checkErrorMessage(LoginPage.ErrorType.PASSWORD),
                "password length must be more than 3 symbols and less than 8 symbols",
                "Сообщение об ошибке не соответствует ожидаемому");
    }
}
