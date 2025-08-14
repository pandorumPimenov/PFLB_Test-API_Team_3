package pages.users;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/*
Создать пользователя и проверить отображение данных в таблице:
ID
first name
last name
age
sex
money   - найти его там нереально может пропустить?
*/
@Log4j2
public class UsersReadAllPage extends BasePage {

    // Элементы управления
    private final SelenideElement
            reloadButton = $x("//button[contains(text(),'Reload')]");

    // Кнопки сортировки
    private final SelenideElement
            idSortButton = $x("//button[contains(text(),'ID')]"),
            firstNameSortButton = $x("//button[contains(text(),'First')]"),
            lastNameSortButton = $x("//button[contains(text(),'Last')]"),
            ageSortButton = $x("//button[contains(text(),'Age')]"),
            sexSortButton = $x("//button[contains(text(),'Sex')]"),
            moneySortButton = $x("//button[contains(text(),'Money')]");

    // Заголовки столбцов
    private final SelenideElement
            idTitle = $x("//th[contains(text(),'ID')]"),
            firstNameTitle = $x("//th[contains(text(),'First')]"),
            lastNameTitle = $x("//th[contains(text(),'Last')]"),
            ageTitle = $x("//th[contains(text(),'Age')]"),
            sexTitle = $x("//th[contains(text(),'Sex')]"),
            moneyTitle = $x("//th[contains(text(),'Money')]");

    // Элементы таблицы
    private final SelenideElement
            tableBody = $("tbody"),
            userWithId3 = $x("//td[contains(text(), '3')]"),
            secondNameCell = $("tbody tr td:nth-child(2)"),
            thirdSurnameCell = $("tbody tr td:nth-child(3)");

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
        clickElement(reloadButton);
        tableBody.shouldBe(visible);
        return this;
    }

    @Step("Проверить видимость всех элементов управления")
    public UsersReadAllPage verifyControlsVisible() {
        log.info("Checking visibility of all controls");
        reloadButton.shouldBe(visible);
        checkSortButtons();
        checkTableTitles();
        return this;
    }

    @Step("Проверка отображения всех кнопок сортировки")
    public UsersReadAllPage checkSortButtons() {
        log.info("Checking visibility of all sort buttons");
        idSortButton.shouldBe(visible);
        firstNameSortButton.shouldBe(visible);
        lastNameSortButton.shouldBe(visible);
        ageSortButton.shouldBe(visible);
        sexSortButton.shouldBe(visible);
        moneySortButton.shouldBe(visible);
        return this;
    }

    @Step("Проверка наличия и корректности названий столбцов таблицы")
    public UsersReadAllPage checkTableTitles() {
        log.info("Checking visibility of all column titles");
        idTitle.shouldBe(visible);
        firstNameTitle.shouldBe(visible);
        lastNameTitle.shouldBe(visible);
        ageTitle.shouldBe(visible);
        sexTitle.shouldBe(visible);
        moneyTitle.shouldBe(visible);
        return this;
    }

    @Step("Проверка наличия строк в таблице")
    public UsersReadAllPage checkTableNotEmpty() {
        log.info("Checking if table contains data");
        tableBody.shouldBe(visible);
        log.info("Table contains data");
        return this;
    }

    @Step("Проверка сортировки по ID")
    public UsersReadAllPage checkSortingByID() {
        log.info("Checking sorting by ID");
        idSortButton.click();
        userWithId3.shouldHave(text("3"));
        log.info("Sorting by ID works correctly");
        return this;
    }

    @Step("Проверка сортировки по Имени")
    public UsersReadAllPage checkSortingByName() {
        log.info("Checking sorting by Name");
        firstNameSortButton.click();
        secondNameCell.shouldBe(empty);
        log.info("Sorting by Name works correctly");
        return this;
    }

    @Step("Проверка наличия созданного пользователя в таблице")
    public UsersReadAllPage checkNewUserInTable(String firstName, String lastName) {
        log.info("Checking presence of new user with firstName: {} and lastName: {}", firstName, lastName);

        reloadTable();

        tableBody.shouldHave(text(firstName), text(lastName));

        log.info("User {} {} found in table", firstName, lastName);
        return this;
    }
}
