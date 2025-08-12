package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;
import java.util.Collection;
import java.util.List;

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
public class UsersReadAllPage extends BasePage {
    private final SelenideElement reloadButton = $x("//button[contains(text(),'Reload')]");
    private final SelenideElement idSortButton = $x("//button[contains(text(),'ID')]");
    private final SelenideElement firstNameSortButton = $x("//button[contains(text(),'First')]");
    private final SelenideElement lastNameSortButton = $x("//button[contains(text(),'Last')]");
    private final SelenideElement ageSortButton = $x("//button[contains(text(),'Age')]");
    private final SelenideElement sexSortButton = $x("//button[contains(text(),'Sex')]");
    private final SelenideElement moneySortButton = $x("//button[contains(text(),'Money')]");
    private final SelenideElement idTitle = $x("//th[contains(text(),'ID')]");
    private final SelenideElement firstNameTitle = $x("//th[contains(text(),'First')]");
    private final SelenideElement lastNameTitle = $x("//th[contains(text(),'Last')]");
    private final SelenideElement ageTitle = $x("//th[contains(text(),'Age')]");
    private final SelenideElement sexTitle = $x("//th[contains(text(),'Sex')]");
    private final SelenideElement moneyTitle = $x("//th[contains(text(),'Money')]");
    private final SelenideElement tableBody = $("tbody");
    private final SelenideElement userWithId3 = $x("//td[contains(text(), '3')]");
    private final SelenideElement secondNameCell = $("tbody tr td:nth-child(2)");// вторая с именем
    private final SelenideElement thirdSurnameCell = $("tbody tr td:nth-child(3)");

    @Step("открытие страницы Users_Read_all")
    public UsersReadAllPage openUsersReadAllPage(){
        open(BASE_URL + "#/read/users");
        return this;
    }

    @Step("Проверка отображения всех кнопок сортировки")
    public UsersReadAllPage checkSortButtons() {
        reloadButton.shouldBe(visible);
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
        tableBody.shouldBe(visible);
        return this;
    }

    @Step("Проверка сортировки по ID")
    public UsersReadAllPage checkSortingByID() {
        idSortButton.click();
        userWithId3.shouldHave(text("3"));
        return this;
    }

    @Step("Проверка сортировки по Имени")
    public UsersReadAllPage checkSortingByName() {
        firstNameSortButton.click();
        secondNameCell.shouldBe(empty);
        return this;
    }
    @Step("Проверка наличия созданного пользователя в таблице")
    public UsersReadAllPage checkNewUserInTable(String newUserName){
     tableBody.$$("tr").findBy(text(newUserName)).should(exist);
     return this;
        }
    }