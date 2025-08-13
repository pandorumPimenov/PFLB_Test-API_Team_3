package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import lombok.extern.log4j.Log4j2;
import java.time.Duration;

@Log4j2
public class MenuPage extends BasePage {
    private final SelenideElement usersMenu = $("#basic-nav-dropdown");
    private final SelenideElement dropDownCreateNew = $("a.dropdown-item[href='#/create/user']");
    private final SelenideElement dropDownAddMoney = $("a.dropdown-item[href='#/update/users/plusMoney']");
    private final SelenideElement dropDownLinkReadAll = $x("//div[@class='dropdown-menu'] " +
            "/descendant:: a[contains (text()='Read all')]");
    private final SelenideElement dropDownHousesReadOneByIdPage = $x("//a[@class='dropdown-item' and @href='#/read/house']");
    private final SelenideElement dropDownReadUserWithCars = $x("//a[@class='dropdown-item' and @href='#/read/userInfo']");

    @Step("Открытие меню Users")
    public MenuPage openUsersMenu() {
        log.info("Opening Users menu");
        clickElement(usersMenu);
        log.info("Users menu opened successfully");
        return this;
    }

    @Step("Открытие формы создания пользователя")
    public CreateUserPage openCreateUserForm() {
        log.info("Opening Create User form");
        openUsersMenu();
        clickElement(dropDownCreateNew);
        return new CreateUserPage();
    }

    @Step("Открытие формы добавления денег")
    public AddMoneyPage openAddMoneyForm() {
        log.info("Opening Add Money form");
        openUsersMenu();
        clickElement(dropDownAddMoney);
        log.info("Add Money form opened successfully");
        return new AddMoneyPage();
    }

    @Step("Открытие страницы Houses Read One By Id")
    public HousesReadOneByIdPage openHousesReadOneByIdPage() {
        openUsersMenu();
        dropDownHousesReadOneByIdPage
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
        dropDownReadUserWithCars
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
