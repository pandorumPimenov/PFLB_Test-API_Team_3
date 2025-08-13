package tests;

import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static org.testng.Assert.assertTrue;

public class ReadUserWithCarsTest extends BaseTest {

    @Test(testName = "Проверка открытия страницы ReadUserWithCars")
    public void openedReadUserWithCarsPage() {
        loginPage.login(user, password)
                .checkAlert();
        readUserWithCarsPage = menuPage.openReadUserWithCars();
        webdriver().shouldHave(
                urlContaining("#/read/userInfo"),
                Duration.ofSeconds(20));
        assertTrue(WebDriverRunner.url().contains("#/read/userInfo"),
                "Страница ReadUserWithCars не открылась");
    }

    @Test(testName = "Проверка открытия страницы и наличия элементов управления")
    public void checkUsersReadAllWithCarsNavBar() {
        loginPage.login(user, password)
                .checkAlert();
        readUserWithCarsPage.openUsersReadUserWithCars()
                .checkNavBar();
    }

    @Test(testName = "Проверка работы инпута")
    public void checkInput() {
        loginPage.login(user, password)
                .checkAlert();
        readUserWithCarsPage.openUsersReadUserWithCars()
                .checkInput("7008");
    }

    @Test(testName = "Проверка отображения пользователя в таблице")
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