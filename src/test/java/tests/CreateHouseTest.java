package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static org.testng.Assert.assertEquals;

public class CreateHouseTest extends BaseTest {

    @Test(testName = "Проверка открытия страницы Create new",
            description = "Тест проверяет корректность открытия формы создания нового дома в системе")
    @Owner("Шишкин Федор")
    @Description("Проверка корректного открытия страницы создания нового дома после авторизации")
    public void openedCreateNewPage() {
        loginPage.login(user, password)
                .checkAlert();
        createHousePage.openCreateHousePage();
        webdriver().shouldHave(urlContaining("/#/create/house"));
    }

    @Test(testName = "Создание дома с валидными данными",
            description = "Позитивный тест создания дома с заполнением всех полей валидными значениями")
    @Owner("Шишкин Федор")
    @Description("Проверка успешного создания дома при заполнении всех полей валидными данными")
    public void checkValidHouseCreation() {
        loginPage.login(user, password)
                .checkAlert();

        createHousePage.openCreateHousePage()
                .createHouse("1", "9999", "1", "0", "0", "2");

        assertEquals(createHousePage.getSuccessMessage(),
                "Status: Successfully pushed, code: 201",
                "Дом создан успешно");
    }

    @Test(testName = "Заполнены только floors и price",
            description = "Тест проверяет создание дома при заполнении только обязательных полей")
    @Owner("Шишкин Федор")
    @Description("Проверка создания дома при заполнении только обязательных полей (floors и price)")
    public void checkEmptyOptionalFields() {
        loginPage.login(user, password)
                .checkAlert();
        createHousePage.openCreateHousePage()
                .createHouse("2", "9999", " ", " ", " ", " ");
        assertEquals(createHousePage.getSuccessMessage(),
                "Status: Successfully pushed, code: 201",
                "Дом создан успешно");
    }

    @Test(testName = "Не заполнены основные поля",
            description = "Негативный тест: попытка создания дома без заполнения обязательных полей")
    @Owner("Шишкин Федор")
    @Description("Негативный сценарий: попытка создания дома без заполнения обязательных полей")
    public void checkEmptyRequiredFields() {
        loginPage.login(user, password)
                .checkAlert();
        createHousePage.openCreateHousePage()
                .createHouse("", "", "0", "1", "2", "3");
        assertEquals(createHousePage.getErrorMessage(),
                "Status: Invalid input data",
                "Дом не был создан");
    }

    @Test(testName = "Ввод данных превышающих максимальную длину полей",
            description = "Тест проверяет обработку значений, превышающих максимально допустимую длину")
    @Owner("Шишкин Федор")
    @Description("Проверка валидации при вводе значений, превышающих максимально допустимую длину")
    public void checkMoreThenMaximumValue() {
        loginPage.login(user, password)
                .checkAlert();
        createHousePage.openCreateHousePage()
                .createHouse("100000000000000000", "100000000000000000", "0", "1", "2", "3");
        assertEquals(createHousePage.getErrorMessage400(),
                "Status: AxiosError: Request failed with status code 400",
                "Дом не был создан");
    }

    @Test(testName = "Ввод невалидных числовых значений",
            description = "Тест проверяет обработку отрицательных значений в полях формы")
    @Owner("Шишкин Федор")
    @Description("Проверка валидации при вводе отрицательных значений в поля формы")
    public void checkInvalidValue() {
        loginPage.login(user, password)
                .checkAlert();
        createHousePage.openCreateHousePage()
                .createHouse("-2", "-2", "0", "1", "2", "3");
        assertEquals(createHousePage.getErrorMessage(),
                "Status: Invalid input data",
                "Дом не был создан");
    }
}
