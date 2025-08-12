package tests;

import dto.ui.BuyCar;
import dto.ui.Cars;
import dto.ui.SellCar;
import org.testng.annotations.Test;

public class CarsTest extends BaseTest {
    @Test(testName = "Автомобили")
    public void checkCars() {
        Cars cars = Cars.builder()
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
        carsPage.clickMenuCars()
                .clickReadAll()
                .isCarsReadPageOpened()
                .sortWithId()
                .sortWithIdUp()
                .clickMenuCars()
                .clickCreateNew()
                .createNewCar(cars)
                .getErrorMessage();
        carsPage.clickMenuCars()
                .clickBuyOrSell()
                .buyCarPositive(buyCar)
                .getSuccessMessage();
        carsPage.sellCarPositive(sellCar)
                .getSuccessMessage();
    }
}
