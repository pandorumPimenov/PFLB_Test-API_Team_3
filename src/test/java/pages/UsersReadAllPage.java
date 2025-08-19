package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class UsersReadAllPage extends BasePage {

    // Элементы управления
    private final SelenideElement
            RELOAD_BUTTON = $x("//button[contains(text(),'Reload')]");

    // Кнопки сортировки
    private final SelenideElement
            ID_SORT_BUTTON = $x("//button[contains(text(),'ID')]"),
            FIRST_NAME_SORT_BUTTON = $x("//button[contains(text(),'First')]"),
            LAST_NAME_SORT_BUTTON = $x("//button[contains(text(),'Last')]"),
            AGE_SORT_BUTTON = $x("//button[contains(text(),'Age')]"),
            SEX_SORT_BUTTON = $x("//button[contains(text(),'Sex')]"),
            MONEY_SORT_BUTTON = $x("//button[contains(text(),'Money')]");

    // Заголовки столбцов
    private final SelenideElement
            ID_TITLE = $x("//th[contains(text(),'ID')]"),
            FIRST_NAME_TITLE = $x("//th[contains(text(),'First')]"),
            LAST_NAME_TITLE = $x("//th[contains(text(),'Last')]"),
            AGE_TITLE = $x("//th[contains(text(),'Age')]"),
            SEX_TITLE = $x("//th[contains(text(),'Sex')]"),
            MONEY_TITLE = $x("//th[contains(text(),'Money')]");

    // Элементы таблицы
    private final SelenideElement
            TABLE_BODY = $("tbody"),
            USER_WITH_ID_3 = $x("//td[contains(text(), '3')]"),
            SECOND_NAME_CELL = $("tbody tr td:nth-child(2)"),
            THIRD_SURNAME_CELL = $("tbody tr td:nth-child(3)");

    @Step("открытие страницы Users_Read_all")
    public UsersReadAllPage openUsersReadAllPage() {
        log.info("Opening Users Read All page");
        open(BASE_URL + "#/read/users");
        log.info("Users Read All page opened successfully");
        return this;
    }

    @Step("Обновить данные таблицы")
    public UsersReadAllPage reloadTable() {
        log.info("Reloading table data");
        clickElement(RELOAD_BUTTON);
        TABLE_BODY.shouldBe(visible);
        return this;
    }

    @Step("Проверить видимость всех элементов управления")
    public UsersReadAllPage verifyControlsVisible() {
        log.info("Checking visibility of all controls");
        RELOAD_BUTTON.shouldBe(visible);
        checkSortButtons();
        checkTableTitles();
        return this;
    }

    @Step("Проверка отображения всех кнопок сортировки")
    public UsersReadAllPage checkSortButtons() {
        log.info("Checking visibility of all sort buttons");
        ID_SORT_BUTTON.shouldBe(visible);
        FIRST_NAME_SORT_BUTTON.shouldBe(visible);
        LAST_NAME_SORT_BUTTON.shouldBe(visible);
        AGE_SORT_BUTTON.shouldBe(visible);
        SEX_SORT_BUTTON.shouldBe(visible);
        MONEY_SORT_BUTTON.shouldBe(visible);
        return this;
    }

    @Step("Проверка наличия и корректности названий столбцов таблицы")
    public UsersReadAllPage checkTableTitles() {
        log.info("Checking visibility of all column titles");
        ID_TITLE.shouldBe(visible);
        FIRST_NAME_TITLE.shouldBe(visible);
        LAST_NAME_TITLE.shouldBe(visible);
        AGE_TITLE.shouldBe(visible);
        SEX_TITLE.shouldBe(visible);
        MONEY_TITLE.shouldBe(visible);
        return this;
    }

    @Step("Проверка наличия строк в таблице")
    public UsersReadAllPage checkTableNotEmpty() {
        log.info("Checking if table contains data");
        TABLE_BODY.shouldBe(visible);
        log.info("Table contains data");
        return this;
    }

    @Step("Проверка сортировки по ID")
    public UsersReadAllPage checkSortingByID() {
        log.info("Checking sorting by ID");
        ID_SORT_BUTTON.click();
        USER_WITH_ID_3.shouldHave(text("3"));
        log.info("Sorting by ID works correctly");
        return this;
    }

    @Step("Проверка сортировки по Имени")
    public UsersReadAllPage checkSortingByName() {
        log.info("Checking sorting by Name");
        FIRST_NAME_SORT_BUTTON.click();
        SECOND_NAME_CELL.shouldBe(empty);
        log.info("Sorting by Name works correctly");
        return this;
    }

    @Step("Проверка наличия созданного пользователя в таблице")
    public UsersReadAllPage checkNewUserInTable(String firstName, String lastName) {
        log.info("Checking presence of new user with firstName: {} and lastName: {}", firstName, lastName);

        reloadTable();

        TABLE_BODY.shouldHave(text(firstName), text(lastName));

        log.info("User {} {} found in table", firstName, lastName);
        return this;
    }
}
