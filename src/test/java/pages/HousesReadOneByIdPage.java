package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class HousesReadOneByIdPage extends BasePage {

    //таблица домов
    private final SelenideElement houseIdInput = $("#house_input");
    private final SelenideElement readButton = $x("//button[text()='Read']");
    private final SelenideElement statusButton = $x("//button[@class = 'status btn btn-secondary']");
    private final SelenideElement houseId = $("table.tableHouse td:first-child");
    private final SelenideElement floorCount = $("table.tableHouse td:nth-child(2)");
    private final SelenideElement housePrice = $("table.tableHouse td:nth-child(3)");
    private final SelenideElement parkingPlace = $("table.tableHouse td:nth-child(4)");
    private final SelenideElement lodgers = $("table.tableHouse td:nth-child(5)");


    //таблица жильцов
    private final SelenideElement lodgersId = $("table.tableLodgers td:first-child");
    private final SelenideElement firstName = $("table.tableLodgers td:nth-child(2)");
    private final SelenideElement lastName = $("table.tableLodgers td:nth-child(3)");
    private final SelenideElement lodgersAge = $("table.tableLodgers td:nth-child(4)");
    private final SelenideElement lodgersSex = $("table.tableLodgers td:nth-child(5)");
    private final SelenideElement lodgersMoney = $("table.tableLodgers td:nth-child(6)");

    //таблицы парковок
    private final SelenideElement parkingId = $("table.tableParkings td:first-child");
    private final SelenideElement isWarm = $("table.tableParkings td:nth-child(2)");
    private final SelenideElement isCovered = $("table.tableParkings td:nth-child(3)");
    private final SelenideElement placesCount = $("table.tableParkings td:nth-child(4)");

    @Step("Открытие страницы HousesReadByID")
    public HousesReadOneByIdPage openHousesReadOneById() {
        open(BASE_URL + "/#/read/house");
        return this;
    }

    @Step("Проверка инпута и кнопок на странице")
    public HousesReadOneByIdPage checkInputAndButtons() {
        $(houseIdInput);
        $(readButton);
        $(statusButton);
        return this;
    }

    @Step("Проверка работы инпута  c корректным ID существующего пользователя")
    public HousesReadOneByIdPage checkInput(String user_id) {
        $(houseIdInput).shouldBe(visible).shouldBe(enabled).setValue(user_id);
        $(readButton).shouldBe(interactable).click();
        $(statusButton).shouldHave(text("Status: 200 ok"), Duration.ofSeconds(10));
        return this;
    }

    @Step("Проверка отображения искомого дома")
    public HousesReadOneByIdPage checkHouse(String house_id,String floor_count, String house_price, String parking_place,
                                            String lodger, String lodgers_id, String first_name, String last_name,
                                            String lodgers_age, String lodgers_sex, String lodgers_money,String parking_id,
                                            String is_warm, String is_covered, String place_count) {
        $(houseId).shouldHave(exactText(house_id));
        $(floorCount).shouldHave(exactText(floor_count));
        $(housePrice).shouldHave(exactText(house_price));
        $(parkingPlace).shouldHave(exactText(parking_place));
        $(lodgers).shouldHave(exactText(lodger));
        $(lodgersId).shouldHave(exactText(lodgers_id));
        $(firstName).shouldHave(exactText(first_name));
        $(lastName).shouldHave(exactText(last_name));
        $(lodgersAge).shouldHave(exactText(lodgers_age));
        $(lodgersSex).shouldHave(exactText(lodgers_sex));
        $(lodgersMoney).shouldHave(exactText(lodgers_money));
        $(parkingId).shouldHave(exactText(parking_id));
        $(isWarm).shouldHave(exactText(is_warm));
        $(isCovered).shouldHave(exactText(is_covered));
        $(placesCount).shouldHave(exactText(place_count));
        return this;
    }
}
