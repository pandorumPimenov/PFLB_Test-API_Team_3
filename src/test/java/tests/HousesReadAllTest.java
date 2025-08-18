package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class HousesReadAllTest extends BaseTest {
    @Test(testName = "Проверка страницы HousesReadAll",
            description = "Тест проверяет корректность открытия страницы со списком всех домов")
    @Owner("Шишкин Федор")
    @Description("Проверка навигации и доступности страницы HousesReadAll после авторизации")
    public void checkOpenHousesReadAllPage() {
        loginPage.login(user, password);
        housesReadAllPage.openHousesReadAllPage();
    }

    @Test(testName = "Проверка кнопки Reload",
            description = "Тест проверяет функциональность кнопки перезагрузки данных на странице")
    @Owner("Шишкин Федор")
    @Description("Проверка работоспособности и отклика кнопки Reload на странице HousesReadAll")
    public void checkReloadButton() {
        loginPage.login(user, password);
        housesReadAllPage.openHousesReadAllPage()
                .checkReloadButtons();
    }

    @Test(testName = "Проверка полей в таблицах",
            description = "Тест проверяет наличие и корректность отображения всех полей в таблице домов")
    @Owner("Шишкин Федор")
    @Description("Верификация заголовков и структуры таблицы на странице HousesReadAll")
    public void checkTableTitle() {
        loginPage.login(user, password);
        housesReadAllPage.openHousesReadAllPage()
                .checkTableTitles();
    }
}
