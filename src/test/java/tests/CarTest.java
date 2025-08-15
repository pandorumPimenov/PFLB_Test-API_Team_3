package tests;

import dto.ui.BuyCar;
import dto.ui.Car;
import dto.ui.SellCar;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class CarTest extends BaseTest {
    @Test(testName = "Автомобили")
    @Owner("Бессолицын Игорь")
    public void checkCars() {
        Car cars = Car.builder()
                .engineType("diesel")
                .model("1")
                .mark("2")
                .price("22")
                .build();
        BuyCar buyCar = BuyCar.builder()
                .CarId("7335")
                .CarId("677")
                .build();
        SellCar sellCar = SellCar.builder()
                .CarId("7335")
                .UserId("677")
                .build();
        loginPage.login(user, password)
                .checkAlert();
        carPage.clickMenuCars()
                .clickReadAll()
                .isCarsReadPageOpened()
                .sortWithId()
                .sortWithIdUp()
                .clickMenuCars()
                .clickCreateNew()
                .createNewCar(cars)
                .getErrorMessage();
        carPage.clickMenuCars()
                .clickBuyOrSell()
                .buyCarPositive(buyCar)
                .getSuccessMessage();
        carPage.sellCarPositive(sellCar)
                .getSuccessMessage();
    }
}
