package tests.ui;

import dto.ui.UserBuild;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static dto.ui.UserBuildFactory.getRandomUserBuild;

@Owner("Дерюшева Наталья")
public class UsersReadAllTest extends BaseTest {

    @Test(testName = "Проверка страницы UsersReadAll",
            description = "Комплексная проверка страницы со списком всех пользователей")
    @Description("Проверка корректного отображения страницы UsersReadAll: " +
            "1. Видимость элементов управления " +
            "2. Наличие данных в таблице " +
            "3. Работа сортировки по ID " +
            "4. Работа сортировки по имени")
    public void checkOpenUsersReadAll() {
        loginPage.login(user, password)
                .checkAlert();
        usersReadAllPage = menuPage.openReadAll();
        usersReadAllPage.openUsersReadAllPage().verifyControlsVisible()
                .checkTableNotEmpty()
                .checkSortingByID()
                .checkSortingByName();
    }

    @Test(testName = "Проверка отображения нового пользователя в таблице",
            description = "Проверка отображения созданного пользователя в общем списке")
    @Description("E2E тест создания и проверки пользователя: " +
            "1. Генерация случайного пользователя " +
            "2. Создание пользователя через UI " +
            "3. Проверка его отображения в таблице UsersReadAll")
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
