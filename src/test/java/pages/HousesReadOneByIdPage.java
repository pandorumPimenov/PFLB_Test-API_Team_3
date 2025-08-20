package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import wrappers.Input;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

@Log4j2
public class HousesReadOneByIdPage extends BasePage {

    // Элементы управления
    private final SelenideElement
            READ_BUTTON = $x("//button[text()='Read']"),
            STATUS_BUTTON = $x("//button[@class = 'status btn btn-secondary']");

    //таблица домов
    private final SelenideElement
            HOUSE_ID = $("table.tableHouse td:first-child"),
            FLOOR_COUNT = $("table.tableHouse td:nth-child(2)"),
            HOUSE_PRICE = $("table.tableHouse td:nth-child(3)"),
            PARKING_PLACE = $("table.tableHouse td:nth-child(4)"),
            LODGERS = $("table.tableHouse td:nth-child(5)");

    //таблица жильцов
    private final SelenideElement
            LODGERS_ID = $("table.tableLodgers td:first-child"),
            FIRST_NAME = $("table.tableLodgers td:nth-child(2)"),
            LAST_NAME = $("table.tableLodgers td:nth-child(3)"),
            LODGERS_AGE = $("table.tableLodgers td:nth-child(4)"),
            LODGERS_SEX = $("table.tableLodgers td:nth-child(5)"),
            LODGERS_MONEY = $("table.tableLodgers td:nth-child(6)");

    //таблицы парковок
    private final SelenideElement
            PARKING_ID = $("table.tableParkings td:first-child"),
            IS_WARM = $("table.tableParkings td:nth-child(2)"),
            IS_COVERED = $("table.tableParkings td:nth-child(3)"),
            PLACE_COUNT = $("table.tableParkings td:nth-child(4)");

    @Step("Открытие страницы HousesReadByID")
    public HousesReadOneByIdPage openHousesReadOneById() {
        Selenide.sleep(5000);
        log.info("Opening Houses Read By ID page");
        open(BASE_URL + "/#/read/house");
        HOUSE_ID.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }

    @Step("Проверка инпута и кнопок на странице")
    public HousesReadOneByIdPage checkInputAndButtons() {
        log.info("Checking input and buttons visibility");
        new Input("house_input").shouldExist();
        READ_BUTTON.shouldBe(visible, enabled);
        STATUS_BUTTON.shouldBe(visible);
        return this;
    }

    @Step("Проверка работы инпута  c корректным ID {houseId} существующего пользователя")
    public HousesReadOneByIdPage checkInput(String houseId) {
        log.info("Searching for house with ID: {}", houseId);

        new Input("house_input").write(houseId);
        log.info("Entered house ID: {}", houseId);

        clickElement(READ_BUTTON);
        log.info("Clicked Read button");

        STATUS_BUTTON.shouldHave(text("Status: 200 ok"), Duration.ofSeconds(10));
        log.info("Status confirmed: 200 ok");

        return this;
    }

    @Step("Проверка данных дома и связанных сущностей")
    public HousesReadOneByIdPage checkHouse(
            String expectedHouseId, String expectedFloorCount, String expectedHousePrice,
            String expectedParkingPlace, String expectedLodger,
            String expectedLodgerId, String expectedFirstName, String expectedLastName,
            String expectedLodgerAge, String expectedLodgerSex, String expectedLodgerMoney,
            String expectedParkingId, String expectedIsWarm, String expectedIsCovered,
            String expectedPlaceCount) {

        log.info("Verifying house and related data");

        // Проверка данных дома
        verifyTableCell("House ID", HOUSE_ID, expectedHouseId);
        verifyTableCell("Floor count", FLOOR_COUNT, expectedFloorCount);
        verifyTableCell("House price", HOUSE_PRICE, expectedHousePrice);
        verifyTableCell("Parking place", PARKING_PLACE, expectedParkingPlace);
        verifyTableCell("Lodgers", LODGERS, expectedLodger);

        // Проверка данных жильца
        verifyTableCell("Lodger ID", LODGERS_ID, expectedLodgerId);
        verifyTableCell("First name", FIRST_NAME, expectedFirstName);
        verifyTableCell("Last name", LAST_NAME, expectedLastName);
        verifyTableCell("Age", LODGERS_AGE, expectedLodgerAge);
        verifyTableCell("Sex", LODGERS_SEX, expectedLodgerSex);
        verifyTableCell("Money", LODGERS_MONEY, expectedLodgerMoney);

        // Проверка данных парковки
        verifyTableCell("Parking ID", PARKING_ID, expectedParkingId);
        verifyTableCell("Is warm", IS_WARM, expectedIsWarm);
        verifyTableCell("Is covered", IS_COVERED, expectedIsCovered);
        verifyTableCell("Places count", PLACE_COUNT, expectedPlaceCount);

        return this;
    }

    private void verifyTableCell(String fieldName, SelenideElement cell, String expectedValue) {
        log.info("Verifying {}: expected '{}'", fieldName, expectedValue);
        cell.shouldHave(exactText(expectedValue));
    }
}
