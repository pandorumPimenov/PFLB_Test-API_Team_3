package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class ReadUserWithCarsPage extends BasePage {

    private final SelenideElement INPUT = $x("//input[@id='user_input']");
    private final SelenideElement READ_BUTTON = $x("//button[text()='Read']");
    private final SelenideElement STATUS_SIGN = $x("//button[@class = 'status btn btn-secondary']");
    private final SelenideElement ARROWS_INPUT = $("#spin");

    //строки таблицы пользователя
    private final SelenideElement ID_CELL = $("table.tableUser td:first-child");
    private final SelenideElement FIRST_NAME_CELL = $("table.tableUser td:nth-child(2)");
    private final SelenideElement LAST_NAME_CELL = $("table.tableUser td:nth-child(3)");
    private final SelenideElement AGE_CELL = $("table.tableUser td:nth-child(4)");
    private final SelenideElement SEX_CELL = $("table.tableUser td:nth-child(5)");
    private final SelenideElement MONEY_CELL = $("table.tableUser td:nth-child(6)");
    private final SelenideElement CARS_CELL = $("table.tableUser td:nth-child(7)");

    //строки таблицы  car
    private final SelenideElement ID_CAR_CELL = $("table.tableCars td:first-child");
    private final SelenideElement ENGINE_CELL = $("table.tableCars td:nth-child(2)");
    private final SelenideElement MARK_CELL = $("table.tableCars td:nth-child(3)");
    private final SelenideElement MODEL_CELL = $("table.tableCars td:nth-child(4)");
    private final SelenideElement PRICE_CELL = $("table.tableCars td:nth-child(5)");


    @Step("открытие страницы Users_Read_user_with_cars")
    public ReadUserWithCarsPage openUsersReadUserWithCars() {
        log.info("Opening Users Read User With Cars page");
        open(BASE_URL + "#/read/userInfo");
        return this;
    }

    @Step("Проверка наличия элементов управления на странице")
    public ReadUserWithCarsPage checkNavBar() {
        log.info("Checking navigation elements visibility");
        $(INPUT);
        log.info("Input field is visible");
        $(READ_BUTTON);
        log.info("Read button is visible");
        $(STATUS_SIGN);
        log.info("Status sign is visible");
        return this;
    }

    @Step("Проверка работы инпута  c корректным ID существующего пользователя")
    public ReadUserWithCarsPage checkInput(String user_id) {
        log.info("Checking input with user ID: {}", user_id);
        $(INPUT).shouldBe(visible).shouldBe(enabled).setValue(user_id);
        log.info("Entered user ID: {}", user_id);
        $(READ_BUTTON).shouldBe(interactable).click();
        log.info("Clicked Read button");
        $(STATUS_SIGN).shouldHave(text("Status: 200 ok"), Duration.ofSeconds(10));
        log.info("Status is '200 ok'");
        return this;
    }

    @Step("Проверка отображения запрашиваемого пользователя")
    public ReadUserWithCarsPage checkUserInTable(String id, String first_name, String last_name,
                                                 String age, String sex, String money, String cars,
                                                 String car_id, String engine, String mark, String model,
                                                 String price) {
        log.info("Verifying user data in table");
        verifyTableCell("ID", ID_CELL, id);
        verifyTableCell("First Name", FIRST_NAME_CELL, first_name);
        verifyTableCell("Last Name", LAST_NAME_CELL, last_name);
        verifyTableCell("Age", AGE_CELL, age);
        verifyTableCell("Sex", SEX_CELL, sex);
        verifyTableCell("Money", MONEY_CELL, money);
        verifyTableCell("Cars", CARS_CELL, cars);

        log.info("Verifying car data in table");
        verifyTableCell("Car ID", ID_CAR_CELL, car_id);
        verifyTableCell("Engine", ENGINE_CELL, engine);
        verifyTableCell("Mark", MARK_CELL, mark);
        verifyTableCell("Model", MODEL_CELL, model);
        verifyTableCell("Price", PRICE_CELL, price);
        return this;
    }

    private void verifyTableCell(String fieldName, SelenideElement cell, String expectedValue) {
        cell.shouldHave(exactText(expectedValue));
        log.info("{} matches expected value: {}", fieldName, expectedValue);
    }
}