package tests.ui;

import dto.ui.Money;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import utils.Retry;

import static org.testng.Assert.assertEquals;

@Owner("Пименов Сергей")
public class AddMoneyTest extends BaseTest {

    @Test(testName = "Открытие страницы Add Money",
            description = "После успешной авторизации система должна корректно открывать форму пополнения баланса")
    @Description("Проверка корректности навигации к форме пополнения баланса пользователя")
    public void openedAddMoneyPage() {
        loginPage.login(user, password)
                .checkAlert();

        addMoneyPage = menuPage.openAddMoneyForm();
    }

    @Test(testName = "Пополнение баланса валидной суммой",
            description = "Система должна успешно обрабатывать запрос на пополнение баланса при валидных данных")
    @Description("Позитивный сценарий пополнения баланса пользователя")
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

    @Test(testName = "Validation: Отрицательная сумма",
            description = "Система должна отклонять попытки пополнения баланса отрицательной суммой")
    @Description("Негативный сценарий: пополнение баланса отрицательным значением")
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

    @Test(testName = "Validation: Текст вместо суммы",
            description = "Система должна отклонять нечисловые значения в поле суммы",
            retryAnalyzer = Retry.class)
    @Description("Негативный сценарий: пополнение баланса текстовым значением")
    @Flaky
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

    @Test(testName = "Validation: Нулевая сумма",
            description = "Система должна корректно обрабатывать попытку пополнения на нулевую сумму")
    @Description("Проверка обработки нулевого значения при пополнении баланса")
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

    @Test(testName = "Boundary: Минимальная сумма пополнения",
            description = "Система должна корректно обрабатывать минимально допустимую сумму пополнения (0.01)")
    @Description("Проверка boundary-значения для минимальной суммы пополнения")
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
