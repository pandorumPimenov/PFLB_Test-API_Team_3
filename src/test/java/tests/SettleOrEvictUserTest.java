package tests;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static org.testng.Assert.assertEquals;

public class SettleOrEvictUserTest extends BaseTest {

    @Test(testName = "Проверка открытия страницы Settle or Evict user")
    public void openedSettleOrEvictUserPage() {
        loginPage.login(user, password)
                .checkAlert();
        settleOrEvictUserPage.openSettleOrEvictPage();
        webdriver().shouldHave(urlContaining("/#/update/houseAndUser"));
    }

    @Test(testName = "Проверка заселения пользователя в дом")
    public void settleUserInHouse() {
        loginPage.login(user, password)
                .checkAlert();
        settleOrEvictUserPage.openSettleOrEvictPage()
                .settleUser("3", "2203");
//        assertEquals(settleOrEvictUserPage.getSuccessMessage(),
//                "Status: Successfully pushed, code: 200",
//                "Пользователя не удалось заселить");
    }

    @Test(testName = "Проверка выселения пользователя из дома")
    public void evictUserFromHouse() {
        loginPage.login(user, password)
                .checkAlert();
        settleOrEvictUserPage.openSettleOrEvictPage()
                .settleUser("3", "2203");
//        assertEquals(settleOrEvictUserPage.getSuccessMessage(),
//                "Status: Successfully pushed, code: 200",
//                "Пользователь в дома не живет");
    }
}
