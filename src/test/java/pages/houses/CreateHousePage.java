package pages.houses;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.BasePage;
import wrappers.Input;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

@Log4j2
public class CreateHousePage extends BasePage {
    private final SelenideElement
            PUSH_BUTTON = $("button.tableButton.btn-primary"),
            SUCCESSFUL_MESSAGE_201 = $x("//button[normalize-space()='Status: " +
                    "Successfully pushed, code: 201']"),
            INVALID_MESSAGE = $x("//button[normalize-space()='Status: Invalid input data']"),
            INVALID_MESSAGE_400 = $x("//button[normalize-space()='Status: " +
                    "AxiosError: Request failed with status code 400']");

    @Step("Открытие страницы Create House")
    public CreateHousePage openCreateHousePage() {
        log.info("Opening Create House page");
        open(BASE_URL + "#/create/house");
        webdriver().shouldHave(urlContaining("/#/create/house"));
        return this;
    }

    @Step("Создание дома с данными {floors}, {price}, {parkingFirst}, {parkingSecond}, " +
            "{parkingThird}, {parkingFourth}")
    public CreateHousePage createHouse(String floors, String price, String parkingFirst, String parkingSecond,
                                       String parkingThird, String parkingFourth) {
        log.info("Creating house with parameters: floors={}, price={}, parking1={}, " +
                        "parking2={}, parking3={}, parking4={}",
                floors, price, parkingFirst, parkingSecond, parkingThird, parkingFourth);

        new Input("floor_send").write(floors);
        new Input("price_send").write(price);
        new Input("parking_first_send").write(parkingFirst);
        new Input("parking_second_send").write(parkingSecond);
        new Input("parking_third_send").write(parkingThird);
        new Input("parking_fourth_send").write(parkingFourth);

        log.info("Clicking submit button");
        clickElement(PUSH_BUTTON);
        return this;
    }

    @Step("Получение сообщения об успешном создании дома")
    public String getSuccessMessage() {
        log.info("Getting success message");
        return getText(SUCCESSFUL_MESSAGE_201);
    }

    @Step("Получение сообщения об ошибке")
    public String getErrorMessage() {
        log.info("Getting error message");
        return getText(INVALID_MESSAGE);
    }

    @Step("Сообщение об ошибке при вводе в поле количества символов, превышающего максимально допустимое")
    public String getErrorMessage400() {
        log.info("Getting 400 error message");
        return getText(INVALID_MESSAGE_400);
    }
}