package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HousesReadAllPage extends BasePage {
    private final SelenideElement reloadButton = $x("//button[contains(text(),'Reload')]");
    private final SelenideElement idTitle = $x("//th[contains(text(),'ID')]");
    private final SelenideElement floorCountTitle = $x("//th[contains(text(),'Floor')]");
    private final SelenideElement priceTitle = $x("//th[contains(text(),'Price')]");
    private final SelenideElement parkingPlacesTitle = $x("//th[contains(text(),'Parking')]");
    private final SelenideElement isWarmTitle = $x("//th[contains(text(),'isWarm')]");
    private final SelenideElement isCoveredTitle = $x("//th[contains(text(),'isCovered')]");
    private final SelenideElement placesCountTitle = $x("//th[contains(text(),'placesCount')]");
    private final SelenideElement lodgersTitle = $x("//th[contains(text(),'Lodgers')]");
    private final SelenideElement firstNameTitle = $x("//th[contains(text(),'First')]");
    private final SelenideElement lastNameTitle = $x("//th[contains(text(),'Last')]");
    private final SelenideElement ageTitle = $x("//th[contains(text(),'Age')]");
    private final SelenideElement sexTitle = $x("//th[contains(text(),'Sex')]");
    private final SelenideElement moneyTitle = $x("//th[contains(text(),'Money')]");

    @Step("открытие страницы Houses_Read_all")
    public HousesReadAllPage openHousesReadAllPage() {
        open(BASE_URL + "#/read/houses");
        return this;
    }

    @Step("Проверка отображения кнопки обновления")
    public HousesReadAllPage checkReloadButtons() {
        reloadButton.shouldBe(visible).click();
        return this;
    }

    @Step("Проверка наличия и корректности названий столбцов таблицы")
    public HousesReadAllPage checkTableTitles() {
        idTitle.shouldBe(visible);
        floorCountTitle.shouldBe(visible);
        priceTitle.shouldBe(visible);
        parkingPlacesTitle.shouldBe(visible);
        isWarmTitle.shouldBe(visible);
        isCoveredTitle.shouldBe(visible);
        placesCountTitle.shouldBe(visible);
        lodgersTitle.shouldBe(visible);
        firstNameTitle.shouldBe(visible);
        lastNameTitle.shouldBe(visible);
        ageTitle.shouldBe(visible);
        sexTitle.shouldBe(visible);
        moneyTitle.shouldBe(visible);
        return this;
    }
}
