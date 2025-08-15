package tests.api;

import adapters.HouseAdapter;
import adapters.UserAdapter;
import dto.api.House;
import dto.api.Lodger;
import dto.api.ParkingPlace;
import dto.api.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HouseTest {

    long id;

    @Test(priority = 1)
    public void createHouse() {
        HouseAdapter houseAdapter = new HouseAdapter();
        House house = House.builder()
                .id(23)
                .floorCount(3)
                .price(123124)
                .parkingPlace(ParkingPlace.builder()
                        .id(2)
                        .isWarm(true)
                        .isCovered(false)
                        .placesCount(412)
                        .build())
                .lodger(Lodger.builder()
                        .id(32)
                        .firstName("Tom")
                        .secondName("O`Nil")
                        .age(44)
                        .sex("MALE")
                        .build())
                .build();
        House rs = houseAdapter.createHouse(house);
        id = rs.getId();
        Assert.assertEquals(rs.getPrice(), 123124);
    }

    @Test(priority = 2)
    public void getUser() {
        HouseAdapter houseAdapter = new HouseAdapter();
        House house = houseAdapter.getHouse(id);
        Assert.assertEquals(house.getPrice(), 123124);
    }

    @Test(priority = 4)
    public void deleteUser() {
        UserAdapter userAdapter = new UserAdapter();
        userAdapter.deleteUser(id);
    }

    @Test(priority = 3)
    public void updateUser() {
        HouseAdapter houseAdapter = new HouseAdapter();
        House house = House.builder()
                .id(23)
                .floorCount(3)
                .price(99999)
                .parkingPlace(ParkingPlace.builder()
                        .id(2)
                        .isWarm(true)
                        .isCovered(false)
                        .placesCount(412)
                        .build())
                .lodger(Lodger.builder()
                        .id(32)
                        .firstName("Tom")
                        .secondName("O`Nil")
                        .age(44)
                        .sex("MALE")
                        .build())
                .build();
        House update = houseAdapter.updateHouse(house, id);
        Assert.assertEquals(update.getPrice(), 99999);
    }
}
