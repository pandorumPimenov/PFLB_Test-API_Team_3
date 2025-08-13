package tests;

import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class HousesReadAllTest extends BaseTest {
    @Test(testName = "Проверка страницы HousesReadAll")
    public void checkOpenHousesReadAllPage() {
        loginPage.login(user, password);
        housesReadAllPage.openHousesReadAllPage();
        webdriver().shouldHave(urlContaining("#/read/houses"));
    }

    @Test(testName = "Проверка кнопки Reload")
    public void checkReloadButton() {
        loginPage.login(user, password);
        housesReadAllPage.openHousesReadAllPage()
                        .checkReloadButtons();
    }

    @Test(testName = "Проверка полей в таблицах")
    public void checkTableTitle() {
        loginPage.login(user,password);
        housesReadAllPage.openHousesReadAllPage()
                .checkTableTitles();
    }
}