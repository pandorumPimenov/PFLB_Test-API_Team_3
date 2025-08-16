package tests;

import dto.ui.UserBuild;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static dto.ui.UserBuildFactory.getRandomUserBuild;
import static dto.ui.UserBuildFactory.*;
import static org.testng.Assert.assertEquals;

public class CreateUserTest extends BaseTest {

    @Test(testName = "Открытие формы создания пользователя Create new",
            description = "После успешной авторизации система должна корректно открывать форму создания нового пользователя")
    @Owner("Пименов Сергей")
    @Description("Проверка корректности навигации к форме создания пользователя")
    public void openedCreateNewPage() {
        loginPage.login(user, password)
                .checkAlert();

        createUserPage = menuPage.openCreateUserForm();

        webdriver().shouldHave(urlContaining("/#/create/user"));
    }

    @Test(testName = "Создание пользователя с валидными данными",
            description = "Система должна успешно создавать пользователя при заполнении всех обязательных полей валидными данными")
    @Owner("Пименов Сергей")
    @Description("Позитивный сценарий создания пользователя с полным набором валидных данных")
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

    @Test(testName = "Boundary: Создание с минимальным/максимальным возрастом",
            description = "Проверка граничных значений возраста (1 год и 123 года) при создании пользователя")
    @Owner("Пименов Сергей")
    @Description("Проверка boundary-значений для поля возраста пользователя")
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
                "Status: Succesfully pushed, code: 201",
                "Пользователь с минимальным возрастом не был создан");

        createUserPage = menuPage.openCreateUserForm()
                .createUser(maxAgeUser, "FEMALE");

        softAssert.assertEquals(
                createUserPage.getSuccessMessage(),
                "Status: Successfully pushed, code: 201",
                "Пользователь с максимальным возрастом (123 года) не был создан");
    }

    @Test(testName = "Validation: Пустое поле First Name",
            description = "Система должна отклонять попытку создания пользователя с пустым именем")
    @Owner("Пименов Сергей")
    @Description("Негативный сценарий: создание пользователя без указания имени")
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

    @Test(testName = "Special: Символы в Last Name",
            description = "Проверка поддержки специальных символов и цифр в поле фамилии пользователя")
    @Owner("Пименов Сергей")
    @Description("Проверка обработки нестандартных символов в поле фамилии")
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

    @Test(testName = "Validation: Нулевой возраст",
            description = "Система должна отклонять попытки создания пользователя с нулевым возрастом")
    @Owner("Пименов Сергей")
    @Description("Негативный сценарий: проверка валидации нулевого возраста")
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

    @Test(testName = "Validation: Отрицательный возраст",
            description = "Система должна блокировать создание пользователей с отрицательным возрастом")
    @Owner("Пименов Сергей")
    @Description("Негативный сценарий: проверка валидации отрицательного возраста")
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

    @Test(testName = "Validation: Возраст > 123 лет",
            description = "Система должна корректно обрабатывать максимально допустимый возраст пользователя (123 года)")
    @Owner("Пименов Сергей")
    @Description("Проверка создания пользователя с предельно допустимым возрастом")
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


    @Test(testName = "Validation: Отсутствие пола",
            description = "Проверка обязательности выбора пола при создании пользователя")
    @Owner("Пименов Сергей")
    @Description("Негативный сценарий: создание пользователя без указания пола")
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