package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class MenuPage extends BasePage {
    private final SelenideElement usersMenu = $("#basic-nav-dropdown");
    private final SelenideElement createNew = $("a.dropdown-item[href='#/create/user']");
    private final SelenideElement addMoney = $("a.dropdown-item[href='#/update/users/plusMoney']");

    @Step("Открытие меню Users")
    public MenuPage openUsersMenu() {
        clickElement(usersMenu);
        return this;
    }

    @Step("Открытие формы создания пользователя")
    public CreateUserPage openCreateUserForm() {
        openUsersMenu();
        clickElement(createNew);
        return new CreateUserPage();
    }

    @Step("Открытие формы добавления денег")
    public AddMoneyPage openAddMoneyForm() {
        openUsersMenu();
        clickElement(addMoney);
        return new AddMoneyPage();
    }
}
