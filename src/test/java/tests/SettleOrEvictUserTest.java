package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static org.testng.Assert.assertEquals;

public class SettleOrEvictUserTest extends BaseTest {

    @Test(testName = "Проверка открытия страницы Settle or Evict user",
            description = "Проверка корректного отображения страницы управления заселением/выселением")
    @Owner("Шишкин Федор")
    @Description("Проверка успешного перехода на страницу управления проживанием пользователей после авторизации")
    public void openedSettleOrEvictUserPage() {
        loginPage.login(user, password)
                .checkAlert();
        settleOrEvictUserPage.openSettleOrEvictPage();
    }

    @Test(testName = "Проверка заселения пользователя в дом",
            description = "Проверка функционала заселения пользователя в указанный дом")
    @Owner("Шишкин Федор")
    @Description("Позитивный тест: проверка успешного заселения пользователя с валидными данными " +
            "(houseId: 3, userId: 2203)")
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
            description = "Проверка функционала выселения пользователя из дома")
    @Owner("Шишкин Федор")
    @Description("Позитивный тест: проверка успешного выселения пользователя (houseId: 3, userId: 2203) " +
            "с подтверждением статуса операции")
    public void evictUserFromHouse() {
        loginPage.login(user, password)
                .checkAlert();
        settleOrEvictUserPage.openSettleOrEvictPage()
                .settleUser("3", "2203");
        assertEquals(settleOrEvictUserPage.getSuccessMessage(),
                "Status: Successfully pushed, code: 200",
                "Пользователь в дома не живет");
    }
}
