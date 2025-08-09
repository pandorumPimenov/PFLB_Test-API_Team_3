package tests;
import org.testng.annotations.Test;
public class LoginTest extends BaseTest{

    @Test(testName = "Авторизация с валидными данными")
    public void checkLogin() {
        loginPage.login(user, password);
        softAssert.assertEquals(loginPage.checkAlert(), "Successful authorization");
    }
}