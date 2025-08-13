package wrappers;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RadioButton {
    String value;

    public RadioButton (String value) {
        this.value = value;
    }

    public void checkBuyOrSellCars() {
        String xpath = String.format("//tbody//input[@value = '%s']", value);
        $(By.xpath(xpath))
                .shouldBe(visible,enabled)
                .click();
    }
    public void checkUser() {
        String xpath = String.format("//tbody//input[@value = '%s']", value);
        $(By.xpath(xpath))
                .shouldBe(visible, enabled)
                .click();
    }
}
