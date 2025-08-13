package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HousesReadOneByIdPage extends BasePage {

    //таблица домов
    private final SelenideElement HOUSE_ID_INPUT = $("#house_input");
    private final SelenideElement READ_BUTTON = $x("//button[text()='Read']");
    private final SelenideElement STATUS_BUTTON = $x("//button[@class = 'status btn btn-secondary']");
    private final SelenideElement HOUSE_ID = $("table.tableHouse td:first-child");
    private final SelenideElement FLOOR_COUNT = $("table.tableHouse td:nth-child(2)");
    private final SelenideElement HOUSE_PRICE = $("table.tableHouse td:nth-child(3)");
    private final SelenideElement PARKING_PLACE = $("table.tableHouse td:nth-child(4)");
    private final SelenideElement LODGERS = $("table.tableHouse td:nth-child(5)");

    //таблица жильцов
    private final SelenideElement LODGERS_ID = $("table.tableLodgers td:first-child");
    private final SelenideElement FIRST_NAME = $("table.tableLodgers td:nth-child(2)");
    private final SelenideElement LAST_NAME = $("table.tableLodgers td:nth-child(3)");
    private final SelenideElement LODGERS_AGE = $("table.tableLodgers td:nth-child(4)");
    private final SelenideElement LODGERS_SEX = $("table.tableLodgers td:nth-child(5)");
    private final SelenideElement LODGERS_MONEY = $("table.tableLodgers td:nth-child(6)");

    //таблицы парковок
    private final SelenideElement PARKING_ID = $("table.tableParkings td:first-child");
    private final SelenideElement IS_WARM = $("table.tableParkings td:nth-child(2)");
    private final SelenideElement IS_COVERED = $("table.tableParkings td:nth-child(3)");
    private final SelenideElement PLACE_COUNT = $("table.tableParkings td:nth-child(4)");

    @Step("Открытие страницы HousesReadByID")
    public HousesReadOneByIdPage openHousesReadOneById() {
        open(BASE_URL + "/#/read/house");
        return this;
    }

    @Step("Проверка инпута и кнопок на странице")
    public HousesReadOneByIdPage checkInputAndButtons() {
        $(HOUSE_ID_INPUT);
        $(READ_BUTTON);
        $(STATUS_BUTTON);
        return this;
    }

    @Step("Проверка работы инпута  c корректным ID существующего пользователя")
    public HousesReadOneByIdPage checkInput(String user_id) {
        $(HOUSE_ID_INPUT).shouldBe(visible).shouldBe(enabled).setValue(user_id);
        $(READ_BUTTON).shouldBe(interactable).click();
        $(STATUS_BUTTON).shouldHave(text("Status: 200 ok"), Duration.ofSeconds(10));
        return this;
    }

    @Step("Проверка отображения искомого дома")
    public HousesReadOneByIdPage checkHouse(String house_id, String floor_count, String house_price, String parking_place,
                                            String lodger, String lodgers_id, String first_name, String last_name,
                                            String lodgers_age, String lodgers_sex, String lodgers_money, String parking_id,
                                            String is_warm, String is_covered, String place_count) {
        $(HOUSE_ID).shouldHave(exactText(house_id));
        $(FLOOR_COUNT).shouldHave(exactText(floor_count));
        $(HOUSE_PRICE).shouldHave(exactText(house_price));
        $(PARKING_PLACE).shouldHave(exactText(parking_place));
        $(LODGERS).shouldHave(exactText(lodger));
        $(LODGERS_ID).shouldHave(exactText(lodgers_id));
        $(FIRST_NAME).shouldHave(exactText(first_name));
        $(LAST_NAME).shouldHave(exactText(last_name));
        $(LODGERS_AGE).shouldHave(exactText(lodgers_age));
        $(LODGERS_SEX).shouldHave(exactText(lodgers_sex));
        $(LODGERS_MONEY).shouldHave(exactText(lodgers_money));
        $(PARKING_ID).shouldHave(exactText(parking_id));
        $(IS_WARM).shouldHave(exactText(is_warm));
        $(IS_COVERED).shouldHave(exactText(is_covered));
        $(PLACE_COUNT).shouldHave(exactText(place_count));
        return this;
    }
}