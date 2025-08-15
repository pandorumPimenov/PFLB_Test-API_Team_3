package tests;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static org.testng.Assert.assertTrue;

public class ReadUserWithCarsTest extends BaseTest {

    @Test(testName = "Проверка открытия страницы ReadUserWithCars",
            description = "Проверка корректного перехода на страницу информации о пользователях с автомобилями")
    @Owner("Дерюшева Наталья")
    @Description("Позитивный тест: проверка что после авторизации происходит успешный переход " +
            "на страницу ReadUserWithCars по URL")
    public void openedReadUserWithCarsPage() {
        loginPage.login(user, password)
                .checkAlert();
        readUserWithCarsPage = menuPage.openReadUserWithCars();
        webdriver().shouldHave(
                urlContaining("#/read/userInfo"),
                Duration.ofSeconds(20)
        );

        assertTrue(
                WebDriverRunner.url().contains("#/read/userInfo"),
                "Страница ReadUserWithCars не открылась"
        );
    }

    @Test(testName = "Проверка открытия страницы и наличия элементов управления",
            description = "Проверка отображения всех элементов навигации на странице")
    @Owner("Дерюшева Наталья")
    @Description("Проверка что на странице ReadUserWithCars присутствуют все " +
            "необходимые элементы навигации и управления")
    public void checkUsersReadAllWithCarsNavBar() {
        loginPage.login(user, password)
                .checkAlert();
        readUserWithCarsPage.openUsersReadUserWithCars()
                .checkNavBar();
    }

    @Test(testName = "Проверка работы инпута",
            description = "Проверка функциональности поля ввода на странице")
    @Owner("Дерюшева Наталья")
    @Description("Позитивный тест: проверка что поле ввода корректно принимает и обрабатывает вводимые данные")
    public void checkInput() {
        loginPage.login(user, password)
                .checkAlert();
        readUserWithCarsPage.openUsersReadUserWithCars()
                .checkInput("7008");
    }

    @Test(testName = "Проверка отображения пользователя в таблице",
            description = "Проверка корректного отображения данных пользователя в таблице")
    @Owner("Дерюшева Наталья")
    @Description("Комплексная проверка: поиск пользователя по ID и верификация всех его данных в таблице " +
            "(личные данные и информация об автомобиле)")
    public void checkUserInTable() {
        loginPage.login(user, password)
                .checkAlert();
        readUserWithCarsPage.openUsersReadUserWithCars()
                .checkInput("7008")
                .checkUserInTable("7008", "Леопольд", "Бетховен",
                        "45", "MALE", "7996001",
                        "1", "3468", "Gasoline",
                        "VedroS", "Gaykamy", "1000");
    }
}
