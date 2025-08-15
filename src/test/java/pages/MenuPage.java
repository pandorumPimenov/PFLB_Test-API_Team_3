package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import lombok.extern.log4j.Log4j2;
import java.time.Duration;

@Log4j2
public class MenuPage extends BasePage {

    // Основные пункты меню
    private final SelenideElement
            USERS_MENU = $(byText("Users")),
            HOUSES_MENU = $(byText("Houses"));

    // Элементы выпадающего меню Users
    private final SelenideElement
            DROPDOWN_CREATE_NEW = $("a.dropdown-item[href='#/create/user']"),
            DROPDOWN_ADD_MONEY = $("a.dropdown-item[href='#/update/users/plusMoney']"),
            DROPDOWN_LINK_READ_ALL = $x("//div[@class='dropdown-menu'] " +
                    "/descendant:: a[contains (text()='Read all')]");

    // Подменю Houses
    private final SelenideElement
            DROPDOWN_HOUSES_READ_ONE_BY_ID_PAGE = $x("//a[@class='dropdown-item' and @href='#/read/house']"),
            DROPDOWN_READ_USER_WITH_CARS = $x("//a[@class='dropdown-item' and @href='#/read/userInfo']");

    @Step("Открытие меню Users")
    public MenuPage openUsersMenu() {
        log.info("Opening Users menu");
        clickElement(USERS_MENU);
        log.info("Users menu opened successfully");
        return this;
    }

    @Step("Открытие меню Houses")
    public MenuPage openHousesMenu() {
        log.info("Opening Houses menu");
        clickElement(HOUSES_MENU);
        log.info("Houses menu opened successfully");
        return this;
    }

    @Step("Открытие формы создания пользователя")
    public CreateUserPage openCreateUserForm() {
        log.info("Opening Create User form");
        openUsersMenu();
        clickElement(DROPDOWN_CREATE_NEW);
        return new CreateUserPage();
    }

    @Step("Открытие формы добавления денег")
    public AddMoneyPage openAddMoneyForm() {
        log.info("Opening Add Money form");
        openUsersMenu();
        clickElement(DROPDOWN_ADD_MONEY);
        log.info("Add Money form opened successfully");
        return new AddMoneyPage();
    }

    @Step("Открытие страницы Houses Read One By Id")
    public HousesReadOneByIdPage openHousesReadOneByIdPage() {
        openHousesMenu();
        DROPDOWN_HOUSES_READ_ONE_BY_ID_PAGE
                .shouldBe(visible, enabled, interactable)
                .click();
        webdriver().shouldHave(
                urlContaining("#/read/house"),
                Duration.ofSeconds(5));
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
        webdriver().shouldHave(
                urlContaining("#/read/userInfo"),
                Duration.ofSeconds(5));
        log.info("Successfully navigated to Read User With Cars page");
        return page(ReadUserWithCarsPage.class);
    }
}
