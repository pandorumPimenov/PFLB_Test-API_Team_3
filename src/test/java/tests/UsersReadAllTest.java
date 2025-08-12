package tests;

import org.testng.annotations.Test;

public class UsersReadAllTest extends BaseTest{

    @Test (testName = "Проверка страницы UsersReadAll")
    public void checkOpenUsersReadAll(){
        loginPage.login(user, password)
                        .checkAlert();
        usersReadAllPage.openUsersReadAllPage()
                .checkSortButtons()
                .checkTableTitles()
                .checkTableNotEmpty()
                .checkSortingByID()
                .checkSortingByName();
    }

    @Test(testName = "Проверка отображения нового пользователя в таблице")
    public void checkNewUserInTable(){
        loginPage.login(user,password)
                .checkAlert();
        createUserPage = menuPage.openCreateUserForm();
        createUserPage.createUser("Lava", "Lava", "56", "male", "1223");
        usersReadAllPage.openUsersReadAllPage()
                .checkNewUserInTable("Lava");
    }
}