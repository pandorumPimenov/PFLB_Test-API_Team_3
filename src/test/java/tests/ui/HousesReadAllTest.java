package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

@Owner("Шишкин Федор")
public class HousesReadAllTest extends BaseTest {
    @Test(testName = "Проверка страницы HousesReadAll",
            description = "Тест проверяет корректность открытия страницы со списком всех домов")
    @Description("Проверка навигации и доступности страницы HousesReadAll после авторизации")
    public void checkOpenHousesReadAllPage() {
        loginPage.login(user, password);
        housesReadAllPage.openHousesReadAllPage();
        webdriver().shouldHave(urlContaining("#/read/houses"));
    }

    @Test(testName = "Проверка кнопки Reload",
            description = "Тест проверяет функциональность кнопки перезагрузки данных на странице")
    @Description("Проверка работоспособности и отклика кнопки Reload на странице HousesReadAll")
    public void checkReloadButton() {
        loginPage.login(user, password);
        housesReadAllPage.openHousesReadAllPage()
                .checkReloadButtons();
    }

    @Test(testName = "Проверка полей в таблицах",
            description = "Тест проверяет наличие и корректность отображения всех полей в таблице домов")
    @Description("Верификация заголовков и структуры таблицы на странице HousesReadAll")
    public void checkTableTitle() {
        loginPage.login(user, password);
        housesReadAllPage.openHousesReadAllPage()
                .checkTableTitles();
    }
}
