package tests.api;

import adapters.CarAdapter;
import dto.api.Car;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CarTest {

    long id;

    @Test(priority = 1)
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
    public void getCar() {
        CarAdapter carAdapter = new CarAdapter();
        Car get = carAdapter.getCar(id);
        Assert.assertEquals(get.getModel(), "Karina");
    }


    @Test(priority = 3)
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
    public void deleteCar() {
        CarAdapter carAdapter = new CarAdapter();
        carAdapter.deleteCar(id);
    }
}
