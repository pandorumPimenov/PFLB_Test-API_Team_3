package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class Input {

    private final String value;

    public Input(String value) {
        this.value = value;
    }

    public void write(String text) {
        String xpath = String.format("//tbody//input[@id = '%s']", value);
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
}




