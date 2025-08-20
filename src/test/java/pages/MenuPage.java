package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class MenuPage extends BasePage {

    // Основные пункты меню
    private final SelenideElement
            USERS_MENU = $(byText("Users")),
            CARS_MENU = $(byText("Cars")),
            HOUSES_MENU = $(byText("Houses"));

    // Элементы выпадающего меню Users
    private final SelenideElement
            DROPDOWN_CREATE_NEW = $("a.dropdown-item[href='#/create/user']"),
            DROPDOWN_ADD_MONEY = $("a.dropdown-item[href='#/update/users/plusMoney']");

    // Элементы выпадающего меню Cars
    private final SelenideElement
            CARS_DROPDOWN_MENU = $("div.dropdown-menu.show"),
            CAR_READ_ALL = $x("//a[text()='Read all']"),
            CAR_CREATE_NEW = $x("//a[text()='Create new']"),
            CAR_BUY_OR_SELL = $x("//a[text()='Buy or sell car']");

    // Подменю Houses
    private final SelenideElement
            DROPDOWN_HOUSES_READ_ONE_BY_ID_PAGE = $x("//a[@class='dropdown-item' and @href='#/read/house']"),
            DROPDOWN_READ_USER_WITH_CARS = $x("//a[@class='dropdown-item' and @href='#/read/userInfo']"),
            DROPDOWN_READ_ALL = $x("//a[@class='dropdown-item' and @href='#/read/users']");

    private final SelenideElement
            ID_SORT_BUTTON = $x("//button[contains(text(),'ID')]"),
            ENGINE_TYPE_INPUT = $("#car_engine_type_send"),
            ID_SEND_INPUT = $("#id_send"),
            FIRST_NAME_INPUT = $("#first_name_send"),
            HOUSE_INPUT = $("#house_input"),
            USER_INPUT = $("#user_input");

    @Step("Открытие меню Users")
    public MenuPage openUsersMenu() {
        Selenide.sleep(5000);
        USERS_MENU.shouldBe(visible, Duration.ofSeconds(20));
        log.info("Opening Users menu");
        clickElement(USERS_MENU);
        log.info("Users menu opened successfully");
        return this;
    }

    @Step("Открытие меню Houses")
    public MenuPage openHousesMenu() {
        Selenide.sleep(5000);
        HOUSES_MENU.shouldBe(visible, Duration.ofSeconds(20));
        log.info("Opening Houses menu");
        clickElement(HOUSES_MENU);
        log.info("Houses menu opened successfully");
        return this;
    }

    @Step("Открытие меню Cars и ожидание появления выпадающего списка")
    public MenuPage openCarsMenu() {
        Selenide.sleep(5000);
        log.info("Opening Cars menu");
        CARS_MENU.shouldBe(visible, Duration.ofSeconds(20));
        clickElement(CARS_MENU);
        CARS_DROPDOWN_MENU.shouldBe(visible);
        return this;
    }

    @Step("Открытие пункта Read All в меню Cars")
    public MenuPage openCarsReadAll() {
        log.info("Opening Cars Read All");
        openCarsMenu();
        clickElement(CAR_READ_ALL);
        ID_SORT_BUTTON.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }

    @Step("Открытие формы создания нового автомобиля")
    public MenuPage openCarsCreateNew() {
        log.info("Opening Create New Car form");
        openCarsMenu();
        clickElement(CAR_CREATE_NEW);
        ENGINE_TYPE_INPUT.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }

    @Step("Открытие формы покупки/продажи автомобиля")
    public MenuPage openBuyOrSellCar() {
        Selenide.sleep(5000);
        log.info("Opening Buy or Sell Car form");
        openCarsMenu();
        clickElement(CAR_BUY_OR_SELL);
        ID_SEND_INPUT.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }

    @Step("Открытие формы создания пользователя")
    public CreateUserPage openCreateUserForm() {
        Selenide.sleep(5000);
        log.info("Opening Create User form");
        openUsersMenu();
        clickElement(DROPDOWN_CREATE_NEW);
        FIRST_NAME_INPUT.shouldBe(visible, Duration.ofSeconds(10));
        return new CreateUserPage();
    }

    @Step("Открытие формы добавления денег")
    public AddMoneyPage openAddMoneyForm() {
        Selenide.sleep(5000);
        log.info("Opening Add Money form");
        openUsersMenu();
        clickElement(DROPDOWN_ADD_MONEY);
        log.info("Add Money form opened successfully");
        ID_SEND_INPUT.shouldBe(visible, Duration.ofSeconds(10));
        return new AddMoneyPage();
    }

    @Step("Открытие страницы Houses Read One By Id")
    public HousesReadOneByIdPage openHousesReadOneByIdPage() {
        Selenide.sleep(5000);
        openHousesMenu();
        DROPDOWN_HOUSES_READ_ONE_BY_ID_PAGE
                .shouldBe(visible, enabled, interactable)
                .click();
        HOUSE_INPUT.shouldBe(visible, Duration.ofSeconds(10));
        return new HousesReadOneByIdPage();
    }

    @Step("Открытие страницы Read user with cars")
    public ReadUserWithCarsPage openReadUserWithCars() {
        log.info("Opening Read User With Cars page");
        openUsersMenu();
        DROPDOWN_READ_USER_WITH_CARS
                .shouldBe(visible, enabled, interactable)
                .click();
        log.info("Clicked Read User With Cars link");
        USER_INPUT.shouldBe(visible, Duration.ofSeconds(10));
        log.info("Successfully navigated to Read User With Cars page");
        return page(ReadUserWithCarsPage.class);
    }
    @Step("Открытие страницы Read all")
    public UsersReadAllPage openReadAll() {
        log.info("Opening Read all page");
        openUsersMenu();
        DROPDOWN_READ_ALL
                .shouldBe(visible, enabled, interactable)
                .click();
        log.info("Clicked Read all link");
        log.info("Successfully navigated to Read all page");
        return page(UsersReadAllPage.class);
    }
}
