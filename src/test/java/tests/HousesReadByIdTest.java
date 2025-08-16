package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class HousesReadByIdTest extends BaseTest {

    @Test(testName = "Проверка открытия страницы HousesReadById",
            description = "Тест проверяет корректность открытия страницы просмотра дома по ID")
    @Owner("Шишкин Федор")
    @Description("Проверка навигации к странице детальной информации о доме по его идентификатору")
    public void openReadHouseById() {
        loginPage.login(user, password)
                .checkAlert();
        housesReadOneByIdPage = menuPage.openHousesReadOneByIdPage();
        webdriver().shouldHave(urlContaining("#/read/house"), Duration.ofSeconds(5));
    }

    @Test(testName = "Проверка открытия страницы и наличия элементов управления",
            description = "Тест проверяет наличие всех необходимых UI-элементов на странице")
    @Owner("Шишкин Федор")
    @Description("Верификация корректности отображения элементов управления на странице HousesReadById")
    public void checkUsersReadAllWithCarsNavBar() {
        loginPage.login(user, password)
                .checkAlert();
        housesReadOneByIdPage.openHousesReadOneById()
                .checkInputAndButtons();
    }

    @Test(testName = "Проверка работы инпута",
            description = "Тест проверяет функциональность поля ввода ID дома")
    @Owner("Шишкин Федор")
    @Description("Проверка корректности работы поля для ввода идентификатора дома")
    public void checkInput() {
        loginPage.login(user, password)
                .checkAlert();
        housesReadOneByIdPage.openHousesReadOneById()
                .checkInput("3");
    }

    @Test(testName = "Проверка отображения дома в таблице",
            description = "Тест проверяет корректность отображения данных о доме в таблице")
    @Owner("Шишкин Федор")
    @Description("Комплексная проверка отображения всех атрибутов дома при поиске по ID")
    public void checkHouse() {
        loginPage.login(user, password)
                .checkAlert();
        housesReadOneByIdPage.openHousesReadOneById()
                .checkInput("5")
                .checkHouse("5", "2", "230", "2", "1", "5",
                        "Mike", "Tyson", "42", "MALE", "10000000",
                        "9", "warm", "covered", "1");
    }
}
