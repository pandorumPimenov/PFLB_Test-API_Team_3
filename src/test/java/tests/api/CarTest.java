package tests.api;

import adapters.CarAdapter;
import dto.api.Car;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CarTest {

    long id;

    @Test(priority = 1)
    @Description("Test verifies successful creation of a new car via API")
    @Owner("Андреев Дмитрий")
    public void createCar() {
        CarAdapter carAdapter = new CarAdapter();
        Car car = Car.builder()
                .id(23)
                .engineType("Diesel")
                .mark("Toyota")
                .model("Karina")
                .build();
        Car rs = carAdapter.createCar(car);
        id = rs.getId();
        Assert.assertEquals(rs.getModel(), "Karina");
    }

    @Test(priority = 2)
    @Owner("Андреев Дмитрий")
    @Description("Test verifies ability to retrieve car information by ID")
    public void getCar() {
        CarAdapter carAdapter = new CarAdapter();
        Car get = carAdapter.getCar(id);
        Assert.assertEquals(get.getModel(), "Karina");
    }


    @Test(priority = 3)
    @Owner("Андреев Дмитрий")
    @Description("Test verifies successful update of existing car details")
    public void updateUser() {
        CarAdapter carAdapter = new CarAdapter();
        Car car = Car.builder()
                .id(23)
                .engineType("Diesel")
                .mark("Toyota")
                .model("Corolla")
                .build();
        Car update = carAdapter.updateCar(car, id);
        Assert.assertEquals(update.getModel(), "Corolla");
    }

    @Test(priority = 4)
    @Description("Test verifies successful deletion of a car")
    @Owner("Андреев Дмитрий")
    public void deleteCar() {
        CarAdapter carAdapter = new CarAdapter();
        carAdapter.deleteCar(id);
    }
}
