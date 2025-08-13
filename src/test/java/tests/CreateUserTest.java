package tests;

import dto.ui.UserBuild;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static dto.UserBuildFactory.*;
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
        UserBuild userBuild = getRandomUserBuild();
        loginPage.login(user, password)
                .checkAlert();

        createUserPage = menuPage.openCreateUserForm()
                .createUser(userBuild, "MALE");

        assertEquals(
                createUserPage.getSuccessMessage(),
                "Status: Successfully pushed, code: 201",
                "Пользователь не был создан");
    }

    @Test(testName = "Создание пользователей с минимальным и максимальным возрастом")
    public void checkMinimumAge() {
        UserBuild minAgeUser = getUserWithMinAge();
        UserBuild maxAgeUser = getUserWithMaxAge();

        loginPage.login(user, password)
                .checkAlert();

        createUserPage = menuPage.openCreateUserForm()
                .createUser(minAgeUser, "MALE");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(
                createUserPage.getSuccessMessage(),
                "Status: Susfully pushed, code: 201",
                "Пользователь с минимальным возрастом не был создан");

        createUserPage = menuPage.openCreateUserForm()
                .createUser(maxAgeUser, "FEMALE");

        softAssert.assertEquals(
                createUserPage.getSuccessMessage(),
                "Status: Successfully pushed, code: 201",
                "Пользователь с максимальным возрастом (123 года) не был создан");
    }

    @Test(testName = "Пустой First Name")
    public void checkEmptyFirstName() {
        UserBuild emptyFirstNameBuild = getUserWithEmptyFirstName();
        loginPage.login(user, password)
                .checkAlert();

        createUserPage = menuPage.openCreateUserForm()
                .createUser(emptyFirstNameBuild, "MALE");

        assertEquals(createUserPage.getErrorMessage(),
                "Status: Invalid request data",
                "Проверка пустого First Name не прошла");
    }

    @Test(testName = "Создание пользователя с Last Name, содержащей цифры и спецсимволы")
    public void createUserWithNonStandardLastName() {
        UserBuild nonStandartLastNameBuild = getUserWithSpecialCharsInLastName();
        loginPage.login(user, password)
                .checkAlert();

        createUserPage = menuPage.openCreateUserForm()
                .createUser(nonStandartLastNameBuild, "MALE");

        assertEquals(
                createUserPage.getSuccessMessage(),
                "Status: Successfully pushed, code: 201",
                "Пользователь не был создан");
    }

    @Test(testName = "Создание пользователей нулевым возрастом")
    public void checkZeroAgeValidation() {
        UserBuild zeroAgeUser = getUserWithZeroAge();
        loginPage.login(user, password).checkAlert();

        createUserPage = menuPage.openCreateUserForm()
                .createUser(zeroAgeUser, "MALE");

        assertEquals(
                createUserPage.getErrorMessage(),
                "Status: Invalid request data",
                "Проверка нулевого возраста не пройдена");
    }

    @Test(testName = "Создание пользователя с отрицательным возрастом")
    public void checkNegativeAgeValidation() {
        UserBuild negativeAgeUser = getUserWithNegativeAge();
        loginPage.login(user, password).checkAlert();

        createUserPage = menuPage.openCreateUserForm()
                .createUser(negativeAgeUser, "FEMALE");

        assertEquals(
                createUserPage.getErrorMessage(),
                "Status: Invalid request data",
                "Проверка отрицательного возраста не пройдена");
    }

    @Test(testName = "Попытка создания пользователя с возрастом > 123 лет")
    public void checkMaxAgeLimitValidation() {
        UserBuild maxAgeLimitUser = getUserWithMaxAge();
        loginPage.login(user, password)
                .checkAlert();

        createUserPage = menuPage.openCreateUserForm()
                .createUser(maxAgeLimitUser, "FEMALE");

        assertEquals(
                createUserPage.getSuccessMessage(),
                "Status: Successfully pushed, code: 201",
                "Пользователь не был создан");
    }


    @Test(testName = "Создание пользователя без выбора пола")
    public void createUserWithoutSexSelection() {
        UserBuild userBuild = getRandomUserBuild();
        loginPage.login(user, password)
                .checkAlert();

        createUserPage = menuPage.openCreateUserForm()
                .createUser(userBuild, "");

        assertEquals(
                createUserPage.getErrorMessage(),
                "Status: Invalid request data",
                "Проверка отсутствия выбора пола не пройдена");
    }
}
