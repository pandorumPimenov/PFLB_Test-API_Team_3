package tests;

import dto.UserBuild;
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
        UserBuild userBuild = UserBuild.builder()
                .firstName("Joe")
                .lastName("Peach")
                .age("30")
                .money("500")
                .build();
        loginPage.login(user, password)
                .checkAlert();

        createUserPage = menuPage.openCreateUserForm()
                .createUser(userBuild, "MALE");

        assertEquals(createUserPage.getSuccessMessage(),
                "Status: Successfully pushed, code: 201",
                "Пользователь не был создан");
    }

    @Test(testName = "Минимальный возраст")
    public void checkMinimumAge() {
        UserBuild userBuild = UserBuild.builder()
                .firstName("Min")
                .lastName("Age")
                .age("1")
                .money("100")
                .build();
        loginPage.login(user, password)
                .checkAlert();

        createUserPage = menuPage.openCreateUserForm()
                .createUser(userBuild, "MALE");

        assertEquals(createUserPage.getSuccessMessage(),
                "Status: Successfully pushed, code: 201",
                "Пользователь с минимальным возрастом не был создан");
    }

    @Test(testName = "Пустой First Name")
    public void checkEmptyFirstName() {
        UserBuild userBuild = UserBuild.builder()
                .firstName("")
                .lastName("Peach")
                .age("25")
                .money("1000")
                .build();
        loginPage.login(user, password)
                .checkAlert();

        createUserPage = menuPage.openCreateUserForm()
                .createUser(userBuild, "MALE");

        assertEquals(createUserPage.getErrorMessage(),
                "Status: Invalid request data",
                "Проверка пустого First Name не прошла");
    }
}
