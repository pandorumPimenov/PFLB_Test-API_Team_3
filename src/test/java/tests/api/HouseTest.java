package tests.api;

import adapters.HouseAdapter;
import adapters.UserAdapter;
import dto.api.House;
import dto.api.Lodger;
import dto.api.ParkingPlace;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Owner("Андреев Дмитрий")
public class HouseTest {

    long id;

    @Test(priority = 1,
            description = "Создание нового дома с жильцами и парковочными местами")
    @Description("Проверка создания дома с параметрами: 3 этажа, цена 123124, " +
            "2 парковочных места (1 теплое), 1 жилец (Tom O`Nil, 44 года)")
    public void createHouse() {
        HouseAdapter houseAdapter = new HouseAdapter();
        House house = House.builder()
                .id(23)
                .floorCount(3)
                .price(123124)
                .parkingPlaces(List.of(  // Используем parkingPlaces (List<ParkingPlace>)
                        ParkingPlace.builder()
                                .id(2)
                                .isWarm(true)
                                .isCovered(false)
                                .placesCount(412)
                                .build()
                ))
                .lodgers(List.of(  // lodgers тоже List<Lodger>
                        Lodger.builder()
                                .id(32)
                                .firstName("Tom")
                                .secondName("O`Nil")
                                .age(44)
                                .sex("MALE")
                                .build()
                ))
                .build();
        House rs = houseAdapter.createHouse(house);
        id = rs.getId();
        Assert.assertEquals(rs.getPrice(), 123124);
    }

    @Test(priority = 2,
            description = "Получение информации о созданном доме")
    @Description("Проверка корректности данных дома после создания (ожидаемая цена: 123124)")
    public void getUser() {
        HouseAdapter houseAdapter = new HouseAdapter();
        House house = houseAdapter.getHouse(id);
        Assert.assertEquals(house.getPrice(), 123124);
    }

    @Test(priority = 4,
            description = "Удаление дома")
    @Description("Проверка удаления созданного дома по ID")
    public void deleteUser() {
        UserAdapter userAdapter = new UserAdapter();
        userAdapter.deleteUser(id);
    }

    @Test(priority = 3,
            description = "Обновление информации о доме")
    @Description("Изменение цены дома с 123124 на 99999 и проверка обновления")
    public void updateUser() {
        HouseAdapter houseAdapter = new HouseAdapter();
        House house = House.builder()
                .id(23)
                .floorCount(3)
                .price(99999)
                .parkingPlaces(List.of(  // Используем List<ParkingPlace>
                        ParkingPlace.builder()
                                .id(2)
                                .isWarm(true)
                                .isCovered(false)
                                .placesCount(412)
                                .build()
                ))
                .lodgers(List.of(  // Используем List<Lodger>
                        Lodger.builder()
                                .id(32)
                                .firstName("Tom")
                                .secondName("O`Nil")
                                .age(44)
                                .sex("MALE")
                                .build()
                ))
                .build();
        House update = houseAdapter.updateHouse(house, id);
        Assert.assertEquals(update.getPrice(), 99999);
    }
}
