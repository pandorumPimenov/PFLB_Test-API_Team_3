package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

@Log4j2
public class CarsPage extends BasePage {

    private final SelenideElement carsFiled = $x("//a[text()='Cars']");
    private final SelenideElement carsReadFiled = $x("//a[text()='Read all']");
    private final SelenideElement carsReloadFiled = $x("//button[text()='Reload']");
    private final SelenideElement idField = $x("//button[contains(text(),'ID')]");
    private final SelenideElement idUpField = $x("//button[text()='↑']");

    private final SelenideElement carsCrateField = $x("//a[text()='Create new']");
    private final SelenideElement carEngineTypeField = $(by("id", "car_engine_type_send"));
    private final SelenideElement carMarkField = $(by("id", "car_mark_send"));
    private final SelenideElement carModelField = $(by("id", "car_model_send"));
    private final SelenideElement carPriceField = $(by("id", "car_price_send"));
    private final SelenideElement pushButton = $("button.btn.btn-primary.tableButton");
    private final SelenideElement successMessage201 = $x("//button[normalize-space()='Status: Successfully pushed, code: 200']");
    private final SelenideElement invalidMessage = $x("//button[normalize-space()='Status: AxiosError: Request failed with status code 400']");

    private final SelenideElement carsByOrSellField = $x("//a[text()='Buy or sell car']");
    private final SelenideElement userIDField = $(by("id", "id_send"));
    private final SelenideElement carIdField = $(by("id", "car_send"));
    private final SelenideElement radioBuyField = $("input[value='buyCar']");
    private final SelenideElement radioSellField = $("input[value='sellCar']");

    @Step("Клик по меню Cars")
    public CarsPage clickMenuCars() {
        log.info("Click menu Cars");
        (carsFiled).click();
        return this;
    }

    @Step("Клик по пункту Read All в меню Cars. Проверка соответствующего открытия окна")
    public CarsPage clickReadAll() {
        log.info("Click menu Cars -> Read All");
        (carsReadFiled).click();
        log.info("Check is opened /#/read/cars");
        webdriver().shouldHave(urlContaining("/#/read/cars"));
        return this;
    }

    @Step("Клик по кнопке Reload")
    public CarsPage isCarsReadPageOpened() {
        log.info("Click button Reload");
        (carsReloadFiled).click();
        return this;
    }

    @Step("Клик по кнопке ID сортировка по возрастанию")
    public CarsPage sortWithId() {
        log.info("Click button ID");
        (idField).click();
        return this;
    }

    @Step("Клик по кнопке ID сортировка по убыванию")
    public CarsPage sortWithIdUp() {
        log.info("Click menu Cars -> Create New");
        (idUpField).click();
        return this;
    }

    @Step("Клик по пункту Create New в меню Cars. Проверка открытия соответствующего окна")
    public CarsPage clickCreateNew() {
        log.info("Click button ↑ ID");
        (carsCrateField).click();
        log.info("Check is opened /#/create/cars");
        webdriver().shouldHave(urlContaining("/#/create/cars"));
        return this;
    }

    @Step("Создание нового автомобиля с данными {engineType}, {mark}, {model}, {price}. Негативный")
    public CarsPage createNewCarNegative(String engineType, String mark, String model, String price) {
        log.info("Set value in Engine Type");
        setValue(carEngineTypeField, engineType);
        log.info("Set value in Mark");
        setValue(carModelField, mark);
        log.info("Set value in Model");
        setValue(carMarkField, model);
        log.info("Set value in Price");
        setValue(carPriceField, price);
        log.info("Click button --PUSH TO API--");
        clickElement(pushButton);
        return this;
    }

    @Step("Получение сообщения об успехе")
    public String getSuccessMessage() {
        return getText(successMessage201);
    }

    @Step("Получение сообщения об ошибке")
    public String getErrorMessage() {
        return getText(invalidMessage);
    }

    @Step("Клик по пункту Buy or sell car в меню Cars. Проверка открытия соответствующего окна")
    public CarsPage clickBuyOrSell() {
        $(carsByOrSellField).click();
        log.info("Check is opened /#/update/users/buyCar");
        webdriver().shouldHave(urlContaining("/#/update/users/buyCar"));
        return this;
    }

    @Step("Покупка автомобиля с данными {userID}, {CarId}. Позитивный")
    public CarsPage buyCarPositive(String userID, String carId) {
        log.info("Set value in User ID BUY");
        setValue(userIDField, userID);
        log.info("Set value in Card Id BUY");
        setValue(carIdField, carId);
        log.info("Tap radio button BUY");
        clickElement(radioBuyField);
        log.info("Click button --PUSH TO API-- BUY");
        clickElement(pushButton);
        return this;
    }

    @Step("Продажа автомобиля с данными {userID}, {CarId}. Позитивный")
    public CarsPage sellCarPositive(String userID, String carId) {
        log.info("Set value in User ID SELL");
        setValue(userIDField, userID);
        log.info("Set value in Card Id SELL");
        setValue(carIdField, carId);
        log.info("Tap radio button SELL");
        clickElement(radioSellField);
        log.info("Click button --PUSH TO API-- SELL");
        clickElement(pushButton);
        return this;
    }
}
