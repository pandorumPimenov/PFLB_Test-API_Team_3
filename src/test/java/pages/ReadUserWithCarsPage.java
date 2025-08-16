package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import wrappers.Input;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

@Log4j2
public class ReadUserWithCarsPage extends BasePage {

    private final SelenideElement
            READ_BUTTON = $x("//button[text()='Read']"),
            STATUS_SIGN = $x("//button[@class = 'status btn btn-secondary']"),
            ARROWS_INPUT = $("#spin");

    // Таблица пользователя
    private final SelenideElement
            ID_CELL = $("table.tableUser td:first-child"),
            FIRST_NAME_CELL = $("table.tableUser td:nth-child(2)"),
            LAST_NAME_CELL = $("table.tableUser td:nth-child(3)"),
            AGE_CELL = $("table.tableUser td:nth-child(4)"),
            SEX_CELL = $("table.tableUser td:nth-child(5)"),
            MONEY_CELL = $("table.tableUser td:nth-child(6)"),
            CARS_CELL = $("table.tableUser td:nth-child(7)");

    // Таблица автомобилей
    private final SelenideElement
            ID_CAR_CELL = $("table.tableCars td:first-child"),
            ENGINE_CELL = $("table.tableCars td:nth-child(2)"),
            MARK_CELL = $("table.tableCars td:nth-child(3)"),
            MODEL_CELL = $("table.tableCars td:nth-child(4)"),
            PRICE_CELL = $("table.tableCars td:nth-child(5)");

    @Step("Открытие страницы Users_Read_user_with_cars")
    public ReadUserWithCarsPage openUsersReadUserWithCars() {
        log.info("Opening Users Read User With Cars page");
        open(BASE_URL + "#/read/userInfo");
        webdriver().shouldHave(urlContaining("#/read/userInfo"));
        return this;
    }

    @Step("Проверка наличия элементов управления на странице")
    public ReadUserWithCarsPage checkNavBar() {
        log.info("Checking navigation elements visibility");
        new Input("user_input").shouldExist();
        READ_BUTTON.shouldBe(visible, enabled);
        STATUS_SIGN.shouldBe(visible);
        return this;
    }

    @Step("Проверка работы инпута c корректным ID существующего пользователя")
    public ReadUserWithCarsPage checkInput(String user_id) {
        log.info("Checking input with user ID: {}", user_id);

        // Используем существующие методы обертки Input
        new Input("user_input")
                .shouldExist()  // Проверяем существование
                .write(user_id); // Записываем значение с проверкой видимости
        log.info("Entered user ID: {}", user_id);

        READ_BUTTON.shouldBe(interactable).click();
        log.info("Clicked Read button");

        STATUS_SIGN.shouldHave(text("Status: 200 ok"), Duration.ofSeconds(10));
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
