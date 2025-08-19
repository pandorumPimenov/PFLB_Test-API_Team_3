package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import utils.Retry;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

@Owner("Дерюшева Наталья")
public class ReadUserWithCarsTest extends BaseTest {

    @Test(testName = "Проверка открытия страницы ReadUserWithCars",
            description = "Проверка корректного перехода на страницу информации о пользователях с автомобилями")
    @Description("Позитивный тест: проверка что после авторизации происходит успешный переход " +
            "на страницу ReadUserWithCars по URL")
    public void openedReadUserWithCarsPage() {
        loginPage.login(user, password)
                .checkAlert();
        readUserWithCarsPage = menuPage.openReadUserWithCars();
    }

    @Test(testName = "Проверка открытия страницы и наличия элементов управления",
            description = "Проверка отображения всех элементов навигации на странице")
    @Description("Проверка что на странице ReadUserWithCars присутствуют все " +
            "необходимые элементы навигации и управления")
    public void checkUsersReadAllWithCarsNavBar() {
        loginPage.login(user, password)
                .checkAlert();
        readUserWithCarsPage = menuPage.openReadUserWithCars();
        webdriver().shouldHave(
                urlContaining("#/read/userInfo"),
                Duration.ofSeconds(20)
        );
        readUserWithCarsPage.checkNavBar();
    }

    @Test(testName = "Проверка работы инпута",
            description = "Проверка функциональности поля ввода на странице")
    @Description("Позитивный тест: проверка что поле ввода корректно принимает и обрабатывает вводимые данные")
    public void checkInput() {
        loginPage.login(user, password)
                .checkAlert();
        readUserWithCarsPage = menuPage.openReadUserWithCars();
        webdriver().shouldHave(
                urlContaining("#/read/userInfo"),
                Duration.ofSeconds(20)
        );
        readUserWithCarsPage.checkInput("7008");
    }

    @Test(testName = "Проверка отображения пользователя в таблице",
            description = "Проверка корректного отображения данных пользователя в таблице",retryAnalyzer = Retry.class)
    @Description("Комплексная проверка: поиск пользователя по ID и верификация всех его данных в таблице " +
            "(личные данные и информация об автомобиле)")
    @Flaky
    public void checkUserInTable() {
        loginPage.login(user, password)
                .checkAlert();
        readUserWithCarsPage = menuPage.openReadUserWithCars();
        webdriver().shouldHave(
                urlContaining("#/read/userInfo"),
                Duration.ofSeconds(40)
        );
        readUserWithCarsPage.checkInput("7008")
                .checkUserInTable("7008", "Леопольд", "Бетховен",
                        "45", "MALE", "7996001",
                        "1", "3468", "Gasoline",
                        "VedroS", "Gaykamy", "1000");
    }
}
