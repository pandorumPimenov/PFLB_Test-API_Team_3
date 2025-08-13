package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class CreateHousePage extends BasePage {
    private final SelenideElement FLOORS_FIELD = $("#floor_send");
    private final SelenideElement PRICE_FIELD = $("#price_send");
    private final SelenideElement PARKING_FIRST_FIELD = $("#parking_first_send");
    private final SelenideElement PARKING_SECOND_FIELD = $("#parking_second_send");
    private final SelenideElement PARKING_THIRD_FIELD = $("#parking_third_send");
    private final SelenideElement PARKING_FOURTH_FIELD = $("#parking_fourth_send");
    private final SelenideElement PUSH_BUTTON = $("button.tableButton.btn-primary");
    private final SelenideElement SUCCESSFUL_MESSAGE_201 = $x("//button[normalize-space()='Status: Successfully pushed, code: 201']");
    private final SelenideElement INVALID_MESSAGE = $x("//button[normalize-space()='Status: Invalid input data']");
    private final SelenideElement INVALID_MESSAGE_400 = $x("//button[normalize-space()='Status: AxiosError: Request failed with status code 400']");

    @Step("открытие страницы Create House")
    public CreateHousePage openCreateHousePage() {
        open(BASE_URL + "#/create/house");
        return this;
    }

    @Step("Создание дома с данными {floors}, {price}, {parkingFirst}, {parkingSecond}, {parkingThird}, {parkingFourth}")
    public CreateHousePage createHouse(String floors, String price, String parkingFirst, String parkingSecond,
                                       String parkingThird, String parkingFourth) {
        FLOORS_FIELD.setValue(floors);
        PRICE_FIELD.setValue(price);
        PARKING_FIRST_FIELD.setValue(parkingFirst);
        PARKING_SECOND_FIELD.setValue(parkingSecond);
        PARKING_THIRD_FIELD.setValue(parkingThird);
        PARKING_FOURTH_FIELD.setValue(parkingFourth);
        PUSH_BUTTON.click();
        return this;
    }

    @Step("Получение сообщения об успешном создании дома")
    public String getSuccessMessage() {
        return getText(SUCCESSFUL_MESSAGE_201);
    }

    @Step("Получение сообщения об ошибке")
    public String getErrorMessage() {
        return getText(INVALID_MESSAGE);
    }

    @Step("Сообщение об ошибке при вооде в поле количество символов привышающее максимально допустимое")
    public String getErrorMessage400() {
        return getText(INVALID_MESSAGE_400);
    }
}
