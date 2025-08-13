package tests;

import org.testng.annotations.Test;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class HousesReadByIdTest extends BaseTest {

    @Test(testName = "Проверка открытия страницы HousesReadById")
    public void openReadHouseById() {
        loginPage.login(user, password)
                .checkAlert();
        housesReadOneByIdPage = menuPage.openHousesReadOneByIdPage();
        webdriver().shouldHave(urlContaining("#/read/house"), Duration.ofSeconds(5));
    }

    @Test(testName = "Проверка открытия страницы и наличия элементов управления")
    public void checkUsersReadAllWithCarsNavBar() {
        loginPage.login(user, password)
                .checkAlert();
        housesReadOneByIdPage.openHousesReadOneById()
                .checkInputAndButtons();
    }

    @Test(testName = "Проверка работы инпута")
    public void checkInput() {
        loginPage.login(user, password)
                .checkAlert();
        housesReadOneByIdPage.openHousesReadOneById()
                .checkInput("3");
    }

    @Test(testName = "Проверка отображения дома в таблице")
    public void checkHouse() {
        loginPage.login(user, password)
                .checkAlert();
        housesReadOneByIdPage.openHousesReadOneById()
                .checkInput("5")
                .checkHouse("5", "2", "230", "2", "1","5" ,
                        "Mike" ,"Tyson" ,"42", "MALE", "10000000",
                        "9", "warm", "covered", "1");
    }
}
