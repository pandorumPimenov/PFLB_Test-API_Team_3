package tests;

import dto.ui.BuyCar;
import dto.ui.Car;
import dto.ui.SellCar;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import utils.Retry;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CarTest extends BaseTest {
    @Test(testName = "Сортировка авто", priority = 1,
            description = "Сортировка авто по id")
    @Owner("Бессолицын Игорь")
    @Description("Сортировка авто по id")
    public void sortCars() {
        loginPage.login(user, password)
                .checkAlert();
        carPage.clickMenuCars()
                .clickReadAll()
                .isCarsReadPageOpened()
                .sortWithId()
                .sortWithIdUp();
    }

    @Test(testName = "Создание нового авто", priority = 2,
            retryAnalyzer = Retry.class,
            description = "Тест проверяет создание нового авто")
    @Owner("Бессолицын Игорь")
    @Description("Проверка создания нового авто")
    public void createCar() {
        Car cars = Car.builder()
                .engineType("Electric")
                .model("bmv")
                .mark("i8")
                .price("22098")
                .build();
        loginPage.login(user, password)
                .checkAlert();
        carPage.clickMenuCars()
                .clickCreateNew()
                .createNewCar(cars);
        assertEquals(carPage.getSuccessMessageCreate(),
                "Status: Successfully pushed, code: 201",
                "Авто не создан");
    }

    @Test(testName = "Создание нового авто. Негативный", priority = 3,
            description = "Тест проверяет создание нового авто")
    @Owner("Бессолицын Игорь")
    @Description("Проверка создания нового авто. Негативный")
    public void createCarNegative() {
        Car cars = Car.builder()
                .engineType("diesel")
                .model("1")
                .mark("2")
                .price("22")
                .build();
        loginPage.login(user, password)
                .checkAlert();
        carPage.clickMenuCars()
                .clickCreateNew()
                .createNewCar(cars);
        assertEquals(carPage.getErrorMessage(),
                "Status: AxiosError: Request failed with status code 400",
                "Авто создан");
    }

    @Test(testName = "Покупка авто", priority = 4,
            description = "Тест проверяет покупку авто")
    @Owner("Бессолицын Игорь")
    @Description("Проверка покупки авто")
    public void buyCar() {
        BuyCar buyCar = BuyCar.builder()
                .UserId("7335")
                .CarId("677")
                .build();
        loginPage.login(user, password)
                .checkAlert();
        carPage.clickMenuCars()
                .clickBuyOrSell()
                .buyCar(buyCar);
        assertEquals(carPage.getSuccessMessage(),
                "Status: Successfully pushed, code: 200",
                "Авто не куплен");
    }

    @Test(testName = "Покупка авто. Негативный", priority = 5,
            description = "Тест проверяет покупку авто. Негативный")
    @Owner("Бессолицын Игорь")
    @Description("Проверка покупки авто. Негативный")
    public void buyCarNegative() {
        BuyCar buyCar = BuyCar.builder()
                .UserId("7335")
                .CarId("7687687686786")
                .build();
        loginPage.login(user, password)
                .checkAlert();
        carPage.clickMenuCars()
                .clickBuyOrSell()
                .buyCar(buyCar);
        String actualError = carPage.getErrorMessage();
        List<String> expectedErrors = List.of(
                "Status: AxiosError: Request failed with status code 400",
                "Status: AxiosError: Request failed with status code 404"
        );
        assertTrue(expectedErrors.contains(actualError),
                "Текст ошибки не совпадает с ожидаемыми вариантами");
    }

    @Test(testName = "Продажа авто", priority = 6,
            description = "Тест проверяет продажу авто")
    @Owner("Бессолицын Игорь")
    @Description("Проверка продажи авто")
    public void sellCar() {
        SellCar sellCar = SellCar.builder()
                .UserId("7335")
                .CarId("677")
                .build();
        loginPage.login(user, password)
                .checkAlert();
        carPage.clickMenuCars()
                .clickBuyOrSell()
                .sellCar(sellCar);
        assertEquals(carPage.getSuccessMessage(),
                "Status: Successfully pushed, code: 200",
                "Авто не продан");
    }

    @Test(testName = "Продажа авто. Негативный", priority = 7,
            description = "Тест проверяет продажу авто. Негативный")
    @Owner("Бессолицын Игорь")
    @Description("Проверка продажи авто. Негативный")
    public void sellCarNegative() {
        SellCar sellCar = SellCar.builder()
                .UserId("7335")
                .CarId("7687687686786")
                .build();
        loginPage.login(user, password)
                .checkAlert();
        carPage.clickMenuCars()
                .clickBuyOrSell()
                .sellCar(sellCar);
        String actualError = carPage.getErrorMessage();
        List<String> expectedErrors = List.of(
                "Status: AxiosError: Request failed with status code 400",
                "Status: AxiosError: Request failed with status code 404"
        );
        assertTrue(expectedErrors.contains(actualError),
                "Текст ошибки не совпадает с ожидаемыми вариантами");
    }
}
