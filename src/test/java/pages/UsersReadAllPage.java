package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

/*
Создать пользователя и проверить отображение данных в таблице:
ID
first name
last name
age
sex
money   - найти его там нереально может пропустить?
*/
public class UsersReadAllPage extends BasePage {
    private final SelenideElement USER_TITLE_UP_MENU = $("#basic-nav-dropdown");
    private final SelenideElement DROPDOWN_LINK_READ_ALL = $x("//div[@class='dropdown-menu'] /descendant:: a[contains (text()='Read all')]");
    private final SelenideElement USERS_READ_ALL_RELOAD_BUTTON = $x("//button[contains(text(),'Reload')]");
    private final SelenideElement USERS_READ_ALL_ID_BUTTON = $x("//button[contains(text(),'ID')]");
    private final SelenideElement USERS_READ_ALL_FIRST_NAME_BUTTON = $x("//button[contains(text(),'First')]");
    private final SelenideElement USERS_READ_ALL_LAST_NAME_BUTTON = $x("//button[contains(text(),'Last')]");
    private final SelenideElement USERS_READ_ALL_AGE_BUTTON = $x("//button[contains(text(),'Age')]");
    private final SelenideElement USERS_READ_ALL_SEX_BUTTON = $x("//button[contains(text(),'Sex')]");
    private final SelenideElement USERS_READ_ALL_MONEY_BUTTON = $x("//button[contains(text(),'Money')]");
    private final SelenideElement USERS_READ_ALL_TABLE_ID_TITLE = $x("//th[contains(text(),'ID')]");
    private final SelenideElement USERS_READ_ALL_FIRST_NAME_TITLE = $x("//th[contains(text(),'First')]");
    private final SelenideElement USERS_READ_ALL_LAST_NAME_TITLE = $x("//th[contains(text(),'Last')]");
    private final SelenideElement USERS_READ_ALL_AGE_TITLE = $x("//th[contains(text(),'Age')]");
    private final SelenideElement USERS_READ_ALL_SEX_TITLE = $x("//th[contains(text(),'Sex')]");
    private final SelenideElement USERS_READ_ALL_MONEY_TITLE = $x("//th[contains(text(),'Money')]");
    private final SelenideElement USERS_READ_ALL_TABLE_BODY = $("tbody");
    private final SelenideElement USERS_READ_ALL_TABLE_CELL_ID = $x("//td[contains(text(), '3')]");
    private final SelenideElement USERS_READ_ALL_TABLE_CELL_FIRST_NAME = $("tbody tr td:nth-child(2)");// втроая с именем

    @Step("открытие страницы Users_Read_all")
    public UsersReadAllPage openUsersReadAllPage(){
        open(BASE_URL + "#/read/users");
        return this;
    }

    @Step("Проверка наличия плашки с кнопками")
    public UsersReadAllPage checkSortButtons(){
        $(USERS_READ_ALL_RELOAD_BUTTON);
        $(USERS_READ_ALL_ID_BUTTON);
        $(USERS_READ_ALL_FIRST_NAME_BUTTON);
        $(USERS_READ_ALL_LAST_NAME_BUTTON);
        $(USERS_READ_ALL_AGE_BUTTON);
        $(USERS_READ_ALL_SEX_BUTTON);
        $(USERS_READ_ALL_MONEY_BUTTON);
        return this;
    }

    @Step("Проверка наличия и корректности названий столбцов таблицы")
    public UsersReadAllPage checkTableTitles(){
        $(USERS_READ_ALL_TABLE_ID_TITLE);
        $(USERS_READ_ALL_FIRST_NAME_TITLE);
        $(USERS_READ_ALL_LAST_NAME_TITLE);
        $(USERS_READ_ALL_AGE_TITLE);
        $(USERS_READ_ALL_SEX_TITLE);
        $(USERS_READ_ALL_MONEY_TITLE);
        return this;
    }

    @Step("Проверка наличия строк в таблице")
    public UsersReadAllPage checkTableNotEmpty(){
        $(USERS_READ_ALL_TABLE_BODY);
        return this;
    }

    @Step("Проверка сортировки по ID")
    public UsersReadAllPage checkSortingByID(){
        $(USERS_READ_ALL_ID_BUTTON).click();
        $(USERS_READ_ALL_TABLE_CELL_ID).shouldHave(text("3"));
        return this;
    }

    @Step("Проверка сортировки по Имени")
    public UsersReadAllPage checkSortingByName(){
        $(USERS_READ_ALL_FIRST_NAME_BUTTON).click();
        $(USERS_READ_ALL_TABLE_CELL_FIRST_NAME).shouldBe(empty);
        return this;

    }
}