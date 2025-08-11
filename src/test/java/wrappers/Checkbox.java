package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Checkbox {
    String value;


    public Checkbox(String value) {
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
