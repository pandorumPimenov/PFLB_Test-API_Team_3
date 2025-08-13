package tests;

import dto.ui.UserBuild;
import org.testng.annotations.Test;

import static dto.UserBuildFactory.getRandomUserBuild;

public class UsersReadAllTest extends BaseTest {

    @Test(testName = "Проверка страницы UsersReadAll")
    public void checkOpenUsersReadAll() {
        loginPage.login(user, password)
                .checkAlert();
        usersReadAllPage.openUsersReadAllPage()
                .verifyControlsVisible()
                .checkTableNotEmpty()
                .checkSortingByID()
                .checkSortingByName();
    }

    @Test(testName = "Проверка отображения нового пользователя в таблице")
    public void checkNewUserInTable() {
        // Генерируем случайного пользователя
        UserBuild userBuild = getRandomUserBuild();
        String expectedFirstName = userBuild.getFirstName();
        String expectedLastName = userBuild.getLastName();

        // Логинимся и создаем пользователя
        loginPage.login(user, password)
                .checkAlert();
        createUserPage = menuPage.openCreateUserForm()
                .createUser(userBuild, "MALE");

        // Проверяем, что пользователь отображается в таблице
        usersReadAllPage.openUsersReadAllPage()
                .checkNewUserInTable(expectedFirstName, expectedLastName);
    }
}
