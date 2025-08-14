package pages.houses;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class HousesReadAllPage extends BasePage {
    // Элементы управления
    private final SelenideElement
            RELOAD_BUTTON = $x("//button[contains(text(),'Reload')]");

    // Заголовки столбцов таблицы
    private final SelenideElement
            ID_TITLE = $x("//th[contains(text(),'ID')]"),
            FLOOR_COUNT_TITLE = $x("//th[contains(text(),'Floor')]"),
            PRICE_TITLE = $x("//th[contains(text(),'Price')]"),
            PARKING_PLACES_TITLE = $x("//th[contains(text(),'Parking')]"),
            IS_WARM_TITLE = $x("//th[contains(text(),'isWarm')]"),
            IS_COVERED_TITLE = $x("//th[contains(text(),'isCovered')]"),
            PLACES_COUNT_TITLE = $x("//th[contains(text(),'placesCount')]"),
            LODGERS_TITLE = $x("//th[contains(text(),'Lodgers')]"),
            FIRST_NAME_TITLE = $x("//th[contains(text(),'First')]"),
            LAST_NAME_TITLE = $x("//th[contains(text(),'Last')]"),
            AGE_TITLE = $x("//th[contains(text(),'Age')]"),
            SEX_TITLE = $x("//th[contains(text(),'Sex')]"),
            MONEY_TITLE = $x("//th[contains(text(),'Money')]");

    @Step("открытие страницы Houses_Read_all")
    public HousesReadAllPage openHousesReadAllPage() {
        open(BASE_URL + "#/read/houses");
        return this;
    }

    @Step("Проверка отображения кнопки обновления")
    public HousesReadAllPage checkReloadButtons() {
        RELOAD_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Проверка наличия и корректности названий столбцов таблицы")
    public HousesReadAllPage checkTableTitles() {
        ID_TITLE.shouldBe(visible);
        FLOOR_COUNT_TITLE.shouldBe(visible);
        PRICE_TITLE.shouldBe(visible);
        PARKING_PLACES_TITLE.shouldBe(visible);
        IS_WARM_TITLE.shouldBe(visible);
        IS_COVERED_TITLE.shouldBe(visible);
        PLACES_COUNT_TITLE.shouldBe(visible);
        LODGERS_TITLE.shouldBe(visible);
        FIRST_NAME_TITLE.shouldBe(visible);
        LAST_NAME_TITLE.shouldBe(visible);
        AGE_TITLE.shouldBe(visible);
        SEX_TITLE.shouldBe(visible);
        MONEY_TITLE.shouldBe(visible);
        return this;
    }
}
