package tests;

import org.testng.annotations.Test;

public class UsersReadAllTest extends BaseTest{

    @Test (testName = "Проверка страницы UsersReadAll")
    public void checkOpenUsersReadAll(){
        loginPage.login(user, password);
        usersReadAllPage.openUsersReadAllPage()
                .checkSortButtons()
                .checkTableTitles()
                .checkTableNotEmpty()
                .checkSortingByID()
                .checkSortingByName();
    }
}