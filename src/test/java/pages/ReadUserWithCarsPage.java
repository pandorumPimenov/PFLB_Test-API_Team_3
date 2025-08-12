package pages;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ReadUserWithCarsPage extends BasePage{
    private final SelenideElement input =$x("//input[@id='user_input']");
    private final SelenideElement read_button = $x("//button[text()='Read']");
    private final SelenideElement status_sign = $x("//button[@class = 'status btn btn-secondary']");
    private final SelenideElement arrowsInInput = $("#spin");

    //строки таблицы пользователя
    private final SelenideElement idCell = $("table.tableUser td:first-child");
    private final SelenideElement firstNameTCell = $("table.tableUser td:nth-child(2)");
    private final SelenideElement lastNameCell = $("table.tableUser td:nth-child(3)");
    private final SelenideElement ageCell = $("table.tableUser td:nth-child(4)");
    private final SelenideElement sexCell= $("table.tableUser td:nth-child(5)");
    private final SelenideElement moneyCell = $("table.tableUser td:nth-child(6)");
    private final SelenideElement carsCell = $("table.tableUser td:nth-child(7)");
   //строки таблицы  car
    private final SelenideElement idCarCell = $("table.tableCars td:first-child");
    private final SelenideElement engineCell = $("table.tableCars td:nth-child(2)");
    private final SelenideElement markCell= $("table.tableCars td:nth-child(3)");
    private final SelenideElement modelCell = $("table.tableCars td:nth-child(4)");
    private final SelenideElement priceCell = $("table.tableCars td:nth-child(5)");



    @Step("открытие страницы Users_Read_user_with_cars")
    public ReadUserWithCarsPage openUsersReadUserWithCars(){
        open(BASE_URL + "#/read/userInfo");
        return this;
    }

    @Step("Проверка наличия элементов управления на странице")
    public ReadUserWithCarsPage checkNavBar(){
        $(input);
        $(read_button);
        $(status_sign);
        return this;
    }

    @Step("Проверка работы инпута  c корректным ID существующего пользователя")
    public ReadUserWithCarsPage checkInput(String user_id){
        $(input).shouldBe(visible).shouldBe(enabled).setValue(user_id);
        $(read_button).shouldBe(interactable).click();
        $(status_sign).shouldHave(text("Status: 200 ok"),Duration.ofSeconds(10));
        return this;
    }

    @Step("Проверка отображения запрашиваемого пользователя")
    public ReadUserWithCarsPage checkUserInTable(String id, String first_name, String last_name,
                                                 String age, String sex, String money,String cars,
                                                 String car_id, String engine, String  mark, String model,
                                                 String price){
        $(idCell).shouldHave(exactText(id));
        $(firstNameTCell).shouldHave(exactText(first_name));
        $(lastNameCell).shouldHave(exactText(last_name));
        $(ageCell).shouldHave(exactText(age));
        $(sexCell).shouldHave(exactText(sex));
        $(moneyCell).shouldHave(exactText(money));
        $(carsCell).shouldHave(exactText(cars));
        $(idCarCell).shouldHave(exactText(car_id));
        $(engineCell).shouldHave(exactText(engine));
        $(markCell).shouldHave(exactText(mark));
        $(modelCell).shouldHave(exactText(model));
        $(priceCell).shouldHave(exactText(price));
        return this;
    }
}