package pages;

import com.codeborne.selenide.SelenideElement;
import dto.ui.BuyCar;
import dto.ui.Car;
import dto.ui.SellCar;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import wrappers.RadioButton;
import wrappers.Input;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

@Log4j2
public class CarPage extends BasePage {

    private final SelenideElement
            CARS_FILED = $x("//a[text()='Cars']"),
            CAR_READ_FILED = $x("//a[text()='Read all']"),
            CAR_RELOAD_FILED = $x("//button[text()='Reload']"),
            ID_FILED = $x("//button[contains(text(),'ID')]"),
            ID_UP_FILED = $x("//button[text()='↑']"),
            ID_DOWN_FILED = $x("//button[text()='↓']"),
            CAR_CREATE_FILED = $x("//a[text()='Create new']"),
            PUSH_BUTTON_FILED = $("button.btn.btn-primary.tableButton"),
            SUCCESS_MESSAGE_FILED = $x("//button[normalize-space()='Status: " +
                    "Successfully pushed, code: 200']"),
            SUCCESS_MESS_CREATE_FILED = $x("//button[normalize-space()='Status: " +
                    "Successfully pushed, code: 201']"),
            INVALID_MESSAGE_FILED = $(byText("Status: AxiosError: " +
                    "Request failed with status code 404")),
            CAR_BUY_OR_SELL_FILED = $x("//a[text()='Buy or sell car']");

    @Step("Клик по меню Cars")
    public CarPage clickMenuCars() {
        log.info("Click menu Cars");
        CARS_FILED.click();
        return this;
    }

    @Step("Клик по пункту Read All в меню Cars. Проверка соответствующего открытия окна")
    public CarPage clickReadAll() {
        log.info("Click menu Cars -> Read All");
        CAR_READ_FILED.click();
        log.info("Check is opened /#/read/cars");
        webdriver().shouldHave(urlContaining("/#/read/cars"));
        return this;
    }

    @Step("Клик по кнопке Reload")
    public CarPage isCarsReadPageOpened() {
        log.info("Click button Reload");
        CAR_RELOAD_FILED.click();
        CAR_RELOAD_FILED.shouldBe(clickable);
        return this;
    }

    @Step("Клик по кнопке ID сортировка по возрастанию")
    public CarPage sortWithId() {
        log.info("Click button ID");
        ID_FILED.click();
        ID_UP_FILED.shouldBe(visible);
        return this;
    }

    @Step("Клик по кнопке ID сортировка по убыванию")
    public CarPage sortWithIdUp() {
        log.info("Click button ↑ ID");
        ID_UP_FILED.click();
        ID_DOWN_FILED.shouldBe(visible);
        return this;
    }

    @Step("Клик по пункту Create New в меню Cars. Проверка открытия соответствующего окна")
    public CarPage clickCreateNew() {
        log.info("Click menu Cars -> Create New");
        CAR_CREATE_FILED.click();
        return this;
    }

    @Step("Создание нового автомобиля с данными {engineType}, {mark}, {model}, {price}")
    public CarPage createNewCar(Car cars) {
        log.info("Set value in Engine Type");
        new Input("car_engine_type_send").write(cars.getEngineType());
        log.info("Set value in Mark");
        new Input("car_mark_send").write(cars.getMark());
        log.info("Set value in Model");
        new Input("car_model_send").write(cars.getModel());
        log.info("Set value in Price");
        new Input("car_price_send").write(cars.getPrice());
        log.info("Click button --PUSH TO API--");
        clickElement(PUSH_BUTTON_FILED);
        return this;
    }

    @Step("Получение сообщения об успехе")
    public String getSuccessMessage() {
        return getText(SUCCESS_MESSAGE_FILED);
    }

    @Step("Получение сообщения об успехе / создание авто")
    public String getSuccessMessageCreate() {
        return getText(SUCCESS_MESS_CREATE_FILED);
    }

    @Step("Получение сообщения об ошибке")
    public String getErrorMessage() {
        return getText(INVALID_MESSAGE_FILED);
    }

    @Step("Клик по пункту Buy or sell car в меню Cars. Проверка открытия соответствующего окна")
    public CarPage clickBuyOrSell() {
        $(CAR_BUY_OR_SELL_FILED).click();
        log.info("Check is opened /#/update/users/buyCar");
        webdriver().shouldHave(urlContaining("/#/update/users/buyCar"));
        return this;
    }

    @Step("Покупка автомобиля с данными {userID}, {CarId}")
    public CarPage buyCar(BuyCar buyCar) {
        log.info("Set value in User ID BUY");
        new Input("id_send").write(buyCar.getUserId());
        log.info("Set value in Card Id BUY");
        new Input("car_send").write(buyCar.getCarId());
        log.info("Tap radio button BUY");
        new RadioButton("buyCar").checkBuyOrSellCars();
        log.info("Click button --PUSH TO API-- BUY");
        clickElement(PUSH_BUTTON_FILED);
        return this;
    }

    @Step("Продажа автомобиля с данными {userID}, {CarId}")
    public CarPage sellCar(SellCar sellCar) {
        log.info("Set value in User ID SELL");
        new Input("id_send").write(sellCar.getUserId());
        log.info("Set value in Card Id SELL");
        new Input("car_send").write(sellCar.getCarId());
        log.info("Tap radio button SELL");
        new RadioButton("sellCar").checkBuyOrSellCars();
        log.info("Click button --PUSH TO API-- SELL");
        clickElement(PUSH_BUTTON_FILED);
        return this;
    }
}
