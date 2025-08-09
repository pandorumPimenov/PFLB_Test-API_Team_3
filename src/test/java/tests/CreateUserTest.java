package tests;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static org.testng.Assert.assertEquals;

public class CreateUserTest extends BaseTest {

    @Test(testName = "Проверка открытия страницы Create new")
    public void openedCreateNewPage() {
        loginPage.login(user, password)
                .checkAlert();

        createUserPage = menuPage.openCreateUserForm();

        webdriver().shouldHave(urlContaining("/#/create/user"));
    }

    @Test(testName = "Создание пользователя с валидными данными")
    public void checkValidUserCreation() {
        loginPage.login(user, password)
                .checkAlert();

        createUserPage = menuPage.openCreateUserForm()
                .createUser("Joe", "Peach", "30", "MALE", "500");

        assertEquals(createUserPage.getSuccessMessage(),
                "Status: Successfully pushed, code: 201",
                "Пользователь не был создан");
    }

    @Test(testName = "Минимальный возраст")
    public void checkMinimumAge() {
        loginPage.login(user, password)
                .checkAlert();

        createUserPage = menuPage.openCreateUserForm()
                .createUser("Min", "Age", "1", "MALE", "100");

        assertEquals(createUserPage.getSuccessMessage(),
                "Status: Successfully pushed, code: 201",
                "Пользователь с минимальным возрастом не был создан");
    }

    @Test(testName = "Пустой First Name")
    public void checkEmptyFirstName() {
        loginPage.login(user, password)
                .checkAlert();

        createUserPage = menuPage.openCreateUserForm()
                .createUser("", "Peach", "25", "MALE", "1000");

        assertEquals(createUserPage.getErrorMessage(),
                "Status: Invalid request data",
                "Проверка пустого First Name не прошла");
    }
}
