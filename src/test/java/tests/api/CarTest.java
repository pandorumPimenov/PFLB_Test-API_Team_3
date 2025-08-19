package tests.api;

import adapters.CarAdapter;
import dto.api.Car;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

@Owner("Андреев Дмитрий")
public class CarTest {

    long id;

    @Test(priority = 1,
            description = "Тест создания нового автомобиля через API")
    @Description("Проверка успешного создания автомобиля с валидными данными: " +
            "марка Toyota, модель Karina, тип двигателя Diesel")
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

    @Test(priority = 2,
            description = "Тест получения данных об автомобиле")
    @Description("Проверка корректности получения данных созданного автомобиля по его ID")
    public void getCar() {
        CarAdapter carAdapter = new CarAdapter();
        Car get = carAdapter.getCar(id);
        Assert.assertEquals(get.getModel(), "Karina");
    }

    @Test(priority = 3,
            description = "Тест обновления данных автомобиля")
    @Description("Проверка изменения модели автомобиля с 'Karina' на 'Corolla' через API")
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

    @Test(priority = 4,
            description = "Тест удаления автомобиля")
    @Description("Проверка успешного удаления автомобиля из системы по его ID")
    public void deleteCar() {
        CarAdapter carAdapter = new CarAdapter();
        carAdapter.deleteCar(id);
    }
}
