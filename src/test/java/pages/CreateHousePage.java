package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.*;

public class CreateHousePage extends BasePage {
    private final SelenideElement floorsField = $("#floor_send");
    private final SelenideElement priceField = $("#price_send");
    private final SelenideElement parkingFirstField = $("#parking_first_send");
    private final SelenideElement parkingSecondField = $("#parking_second_send");
    private final SelenideElement parkingThirdField = $("#parking_third_send");
    private final SelenideElement parkingFourthField = $("#parking_fourth_send");
    private final SelenideElement pushButton = $("button.tableButton.btn-primary");
    private final SelenideElement successMessage201 = $x("//button[normalize-space()='Status: Successfully pushed, code: 201']");
    private final SelenideElement invalidMessage = $x("//button[normalize-space()='Status: Invalid input data']");
    private final SelenideElement invalidMessage400 = $x("//button[normalize-space()='Status: AxiosError: Request failed with status code 400']");

    @Step("открытие страницы Create House")
    public CreateHousePage openCreateHousePage() {
        open(BASE_URL + "#/create/house");
        return this;
    }

    @Step ("Создание дома с данными {floors}, {price}, {parkingFirst}, {parkingSecond}, {parkingThird}, {parkingFourth}")
    public CreateHousePage createHouse (String floors, String price, String parkingFirst, String parkingSecond,
                                        String parkingThird, String parkingFourth) {
        setValue(floorsField,floors);
        setValue(priceField, price);
        setValue(parkingFirstField, parkingFirst);
        setValue(parkingSecondField, parkingSecond);
        setValue(parkingThirdField, parkingThird);
        setValue(parkingFourthField, parkingFourth);
        clickElement(pushButton);
        return this;
    }

    @Step("Получение сообщения об успешном создании дома")
    public String getSuccessMessage() {
        return getText(successMessage201);
    }

    @Step("Получение сообщения об ошибке")
    public String getErrorMessage() {
        return getText(invalidMessage);
    }

    @Step("Сообщение об ошибке при вооде в поле количество символов привышающее максимально допустимое")
    public String getErrorMessage400(){
        return getText(invalidMessage400);
    }
}
