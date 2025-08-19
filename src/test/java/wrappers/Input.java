package wrappers;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class Input {

    private final String value;

    public Input(String value) {
        this.value = value;
    }

    public void write(String text) {
        String xpath = String.format("//input[@id = '%s']", value);
        $(By.xpath(xpath))
                .shouldBe(visible)
                .setValue(text);
    }

    public void writeLogin(String text) {
        String xpath = String.format("//div//input[@name = '%s']", value);
        $(By.xpath(xpath))
                .shouldBe(visible)
                .setValue(text);
    }

    public Input shouldExist() {
        String xpath = String.format("//input[@id = '%s']", value);
        $(By.xpath(xpath)).should(exist)
                .shouldBe(visible)
                .shouldBe(clickable);
        return this;
    }
}
