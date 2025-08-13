package tests;

import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static org.testng.Assert.assertEquals;

public class CreateHouseTest extends BaseTest {

    @Test(testName = "Проверка открытия страницы Create new")
    public void openedCreateNewPage() {
        loginPage.login(user, password)
                .checkAlert();
        createHousePage.openCreateHousePage();
        webdriver().shouldHave(urlContaining("/#/create/house"));
    }

    @Test(testName = "Создание дома с валидными данными")
    public void checkValidHouseCreation() {
        loginPage.login(user, password)
                .checkAlert();

        createHousePage.openCreateHousePage()
                .createHouse("1", "9999", "1", "0", "0", "2");

        assertEquals(createHousePage.getSuccessMessage(),
                "Status: Successfully pushed, code: 201",
                "Дом создан успешно");
    }

    @Test(testName = "Заполнены только floors и price")
    public void checkEmptyOptionalFields() {
        loginPage.login(user, password)
                .checkAlert();
        createHousePage.openCreateHousePage()
                .createHouse("2", "9999", " ", " ", " ", " ");
        assertEquals(createHousePage.getSuccessMessage(),
                "Status: Successfully pushed, code: 201",
                "Дом создан успешно");
    }

    @Test(testName = "Не заполнены основные поля")
    public  void checkEmptyRequiredFields() {
        loginPage.login(user, password)
                .checkAlert();
        createHousePage.openCreateHousePage()
                .createHouse("","","0","1","2","3");
        assertEquals(createHousePage.getErrorMessage(),
                "Status: Invalid input data",
                "Дом не был создан");
    }

    @Test(testName = "Ввод данных превышающих максимальную длину полей")
    public void checkMoreThenMaximumValue() {
        loginPage.login(user, password)
                .checkAlert();
        createHousePage.openCreateHousePage()
                .createHouse("100000000000000000","100000000000000000","0","1","2","3");
        assertEquals(createHousePage.getErrorMessage400(),
                "Status: AxiosError: Request failed with status code 400",
                "Дом не был создан");
    }

    @Test(testName = "Ввод данных превышающих максимальную длину полей")
    public void checkInvalidValue() {
        loginPage.login(user, password)
                .checkAlert();
        createHousePage.openCreateHousePage()
                .createHouse("-2","-2","0","1","2","3");
        assertEquals(createHousePage.getErrorMessage(),
                "Status: Invalid input data",
                "Дом не был создан");
    }

}
