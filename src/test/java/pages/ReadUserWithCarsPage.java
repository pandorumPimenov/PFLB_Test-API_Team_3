package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ReadUserWithCarsPage extends BasePage {
    private final SelenideElement input = $x("//input[@id='user_input']");
    private final SelenideElement read_button = $x("//button[text()='Read']");
    private final SelenideElement status_sign = $x("//button[@class = 'status btn btn-secondary']");
    private final SelenideElement arrowsInInput = $("#spin");

    //строки таблицы пользователя
    private final SelenideElement idCell = $("table.tableUser td:first-child");
    private final SelenideElement firstNameTCell = $("table.tableUser td:nth-child(2)");
    private final SelenideElement lastNameCell = $("table.tableUser td:nth-child(3)");
    private final SelenideElement ageCell = $("table.tableUser td:nth-child(4)");
    private final SelenideElement sexCell = $("table.tableUser td:nth-child(5)");
    private final SelenideElement moneyCell = $("table.tableUser td:nth-child(6)");
    private final SelenideElement carsCell = $("table.tableUser td:nth-child(7)");
    //строки таблицы  car
    private final SelenideElement idCarCell = $("table.tableCars td:first-child");
    private final SelenideElement engineCell = $("table.tableCars td:nth-child(2)");
    private final SelenideElement markCell = $("table.tableCars td:nth-child(3)");
    private final SelenideElement modelCell = $("table.tableCars td:nth-child(4)");
    private final SelenideElement priceCell = $("table.tableCars td:nth-child(5)");


    @Step("открытие страницы Users_Read_user_with_cars")
    public ReadUserWithCarsPage openUsersReadUserWithCars() {
        log.info("Opening Users Read User With Cars page");
        open(BASE_URL + "#/read/userInfo");
        return this;
    }

    @Step("Проверка наличия элементов управления на странице")
    public ReadUserWithCarsPage checkNavBar() {
        log.info("Checking navigation elements visibility");
        $(input);
        log.info("Input field is visible");
        $(read_button);
        log.info("Read button is visible");
        $(status_sign);
        log.info("Status sign is visible");
        return this;
    }

    @Step("Проверка работы инпута  c корректным ID существующего пользователя")
    public ReadUserWithCarsPage checkInput(String user_id) {
        log.info("Checking input with user ID: {}", user_id);
        $(input).shouldBe(visible).shouldBe(enabled).setValue(user_id);
        log.info("Entered user ID: {}", user_id);
        $(read_button).shouldBe(interactable).click();
        log.info("Clicked Read button");
        $(status_sign).shouldHave(text("Status: 200 ok"), Duration.ofSeconds(10));
        log.info("Status is '200 ok'");
        return this;
    }

    @Step("Проверка отображения запрашиваемого пользователя")
    public ReadUserWithCarsPage checkUserInTable(String id, String first_name, String last_name,
                                                 String age, String sex, String money, String cars,
                                                 String car_id, String engine, String mark, String model,
                                                 String price) {
        log.info("Verifying user data in table");
        verifyTableCell("ID", idCell, id);
        verifyTableCell("First Name", firstNameTCell, first_name);
        verifyTableCell("Last Name", lastNameCell, last_name);
        verifyTableCell("Age", ageCell, age);
        verifyTableCell("Sex", sexCell, sex);
        verifyTableCell("Money", moneyCell, money);
        verifyTableCell("Cars", carsCell, cars);

        log.info("Verifying car data in table");
        verifyTableCell("Car ID", idCarCell, car_id);
        verifyTableCell("Engine", engineCell, engine);
        verifyTableCell("Mark", markCell, mark);
        verifyTableCell("Model", modelCell, model);
        verifyTableCell("Price", priceCell, price);

        return this;
    }

    private void verifyTableCell(String fieldName, SelenideElement cell, String expectedValue) {
        cell.shouldHave(exactText(expectedValue));
        log.info("{} matches expected value: {}", fieldName, expectedValue);
    }
}