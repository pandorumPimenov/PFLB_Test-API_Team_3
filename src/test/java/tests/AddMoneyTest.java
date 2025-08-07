package tests;

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
        loginPage.login(user, password)
                .checkAlert();

        addMoneyPage = menuPage.openAddMoneyForm()
                .addMoney("7205", "1000");

        assertEquals(addMoneyPage.getSuccessMessage(),
                "Status: Successfully pushed, code: 200",
                "Деньги не были добавлены");
    }

    @Test(testName = "Пополнение баланса с отрицательной суммой")
    public void checkNegativeMoney() {
        loginPage.login(user, password)
                .checkAlert();

        addMoneyPage = menuPage.openAddMoneyForm()
                .addMoney("7205", "-500");

        assertEquals(addMoneyPage.getIncorrectMessage(),
                "Status: Incorrect input data",
                "Отрицательное значение не должно быть добавлено");
    }

    @Test(testName = "Пополнение баланса с текстовым значением")
    public void checkMoneyWithText() {
        loginPage.login(user, password)
                .checkAlert();

        addMoneyPage = menuPage.openAddMoneyForm()
                .addMoney("7205", "Сто$");

        assertEquals(addMoneyPage.getIncorrectMessage(),
                "Status: Incorrect input data",
                "Не должен быть принят неправильный формат денег");
    }

    @Test(testName = "Пополнение баланса с нулевой суммой")
    public void checkZeroMoney() {
        loginPage.login(user, password)
                .checkAlert();

        addMoneyPage = menuPage.openAddMoneyForm()
                .addMoney("7205", "0");

        assertEquals(addMoneyPage.getIncorrectMessage(),
                "Status: Incorrect input data",
                "Нулевая сумма должна быть принята");
    }
}
