package tests;

import dto.ui.Money;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static org.testng.Assert.assertEquals;

public class AddMoneyTest extends BaseTest {

    @Test(testName = "Проверка открытия страницы Add money")
    public void openedAddMoneyPage() {
        loginPage.login(user, password)
                .checkAlert();

        addMoneyPage = menuPage.openAddMoneyForm();

        webdriver().shouldHave(urlContaining("/#/update/users/plusMoney"));
    }

    @Test(testName = "Пополнение баланса с валидными данными")
    public void checkValidMoneyAddition() {
        Money money = Money.builder()
                .userId("7205")
                .money("1000")
                .build();
        loginPage.login(user, password)
                .checkAlert();

        addMoneyPage = menuPage.openAddMoneyForm()
                .addMoney(money);

        assertEquals(addMoneyPage.getSuccessMessage(),
                "Status: Successfully pushed, code: 200",
                "Деньги не были добавлены");
    }

    @Test(testName = "Пополнение баланса с отрицательной суммой")
    public void checkNegativeMoney() {
        Money money = Money.builder()
                .userId("7205")
                .money("-500")
                .build();
        loginPage.login(user, password)
                .checkAlert();

        addMoneyPage = menuPage.openAddMoneyForm()
                .addMoney(money);

        assertEquals(addMoneyPage.getIncorrectMessage(),
                "Status: Incorrect input data",
                "Отрицательное значение не должно быть добавлено");
    }

    @Test(testName = "Пополнение баланса с текстовым значением")
    public void checkMoneyWithText() {
        Money money = Money.builder()
                .userId("7205")
                .money("Сто$")
                .build();
        loginPage.login(user, password)
                .checkAlert();

        addMoneyPage = menuPage.openAddMoneyForm()
                .addMoney(money);

        assertEquals(addMoneyPage.getIncorrectMessage(),
                "Status: Incorrect input data",
                "Не должен быть принят неправильный формат денег");
    }

    @Test(testName = "Пополнение баланса с нулевой суммой")
    public void checkZeroMoney() {
        Money money = Money.builder()
            .userId("7205")
            .money("0")
            .build();

        loginPage.login(user, password)
                .checkAlert();

        addMoneyPage = menuPage.openAddMoneyForm()
                .addMoney(money);

        assertEquals(addMoneyPage.getIncorrectMessage(),
                "Status: Incorrect input data",
                "Нулевая сумма должна быть принята");
    }
    @Test(testName = "Пополнение баланса минимально допустимой суммой")
    public void checkMinAndMaxMoney() {
        Money money = Money.builder()
                .userId("7205")
                .money("0.01")
                .build();

        loginPage.login(user, password)
                .checkAlert();

        addMoneyPage = menuPage.openAddMoneyForm()
                .addMoney(money);

        assertEquals(addMoneyPage.getSuccessMessage(),
                "Status: Successfully pushed, code: 200",
                "Деньги не были добавлены");
    }
}
