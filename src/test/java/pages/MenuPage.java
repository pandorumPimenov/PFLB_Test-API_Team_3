package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class MenuPage extends BasePage {
    private final SelenideElement usersMenu = $("#basic-nav-dropdown");
    private final SelenideElement dropDownCreateNew = $("a.dropdown-item[href='#/create/user']");
    private final SelenideElement dropDownAddMoney = $("a.dropdown-item[href='#/update/users/plusMoney']");
    private final SelenideElement dropDownLinkReadAll = $x("//div[@class='dropdown-menu'] " +
            "/descendant:: a[contains (text()='Read all')]");
    private final SelenideElement dropDownReadUserWithCars = $x("//a[@class='dropdown-item' and @href='#/read/userInfo']");

    @Step("Открытие меню Users")
    public MenuPage openUsersMenu() {
        clickElement(usersMenu);
        return this;
    }

    @Step("Открытие формы создания пользователя")
    public CreateUserPage openCreateUserForm() {
        openUsersMenu();
        clickElement(dropDownCreateNew);
        return new CreateUserPage();
    }

    @Step("Открытие формы добавления денег")
    public AddMoneyPage openAddMoneyForm() {
        openUsersMenu();
        clickElement(dropDownAddMoney);
        return new AddMoneyPage();
    }

    @Step("Открытие страницы Read user with cars")
    public ReadUserWithCarsPage openReadUserWithCars() {
        openUsersMenu();
        dropDownReadUserWithCars
                .shouldBe(visible, enabled, interactable)
                .click();
        webdriver().shouldHave(
                urlContaining("#/read/userInfo"),
                Duration.ofSeconds(5));
        return page(ReadUserWithCarsPage.class);
    }
}