package tests;

import org.testng.annotations.Test;

public class CarsTest extends BaseTest {
    @Test(testName = "Автомобили")
    public void checkCars() {
        loginPage.login(user, password)
                .checkAlert();
        carsPage.clickMenuCars()
                .clickReadAll()
                .isCarsReadPageOpened()
                .sortWithId()
                .sortWithIdUp()
                .clickMenuCars()
                .clickCreateNew()
                .createNewCarNegative("diesel", "Toyota", "LCPrado", "500000")
                .getErrorMessage();
        carsPage.clickMenuCars()
                .clickBuyOrSell()
                .buyCarPositive("7335", "677")
                .getSuccessMessage();
        carsPage.sellCarPositive("7335", "677")
                .getSuccessMessage();
    }
}
