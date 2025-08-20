package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import utils.Retry;

import static org.testng.Assert.assertEquals;

@Owner("Шишкин Федор")
public class SettleOrEvictUserTest extends BaseTest {

    @Test(testName = "Проверка открытия страницы Settle or Evict user",
            description = "Проверка корректного отображения страницы управления заселением/выселением")
    @Description("Проверка успешного перехода на страницу управления проживанием пользователей после авторизации")
    public void openedSettleOrEvictUserPage() {
        loginPage.login(user, password)
                .checkAlert();
        settleOrEvictUserPage.openSettleOrEvictPage();
    }

    @Test(testName = "Проверка заселения пользователя в дом",
            description = "Проверка функционала заселения пользователя в указанный дом",
            retryAnalyzer = Retry.class)
    @Description("Позитивный тест: проверка успешного заселения пользователя с валидными данными " +
            "(houseId: 3, userId: 2203)")
    @Flaky
    public void settleUserInHouse() {
        loginPage.login(user, password)
                .checkAlert();
        settleOrEvictUserPage.openSettleOrEvictPage()
                .settleUser("3", "2203");
        assertEquals(settleOrEvictUserPage.getSuccessMessage(),
                "Status: Successfully pushed, code: 200",
                "Пользователя не удалось заселить");
    }

    @Test(testName = "Проверка выселения пользователя из дома",
            description = "Проверка функционала выселения пользователя из дома",
            retryAnalyzer = Retry.class)
    @Description("Позитивный тест: проверка успешного выселения пользователя (houseId: 3, userId: 2203) " +
            "с подтверждением статуса операции")
    @Flaky
    public void evictUserFromHouse() {
        loginPage.login(user, password)
                .checkAlert();
        settleOrEvictUserPage.openSettleOrEvictPage()
                .evictUser("3", "2203");
        assertEquals(settleOrEvictUserPage.getSuccessMessage(),
                "Status: Successfully pushed, code: 200",
                "Пользователь в дома не живет");
    }
}
