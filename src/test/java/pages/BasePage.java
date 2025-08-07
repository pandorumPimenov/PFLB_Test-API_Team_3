package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;

public class BasePage {
    protected static final String BASE_URL = "http://82.142.167.37:4881/";

    protected void openPath(String path) {
        Selenide.open(BASE_URL + path);
    }

    protected void clickElement(SelenideElement element) {
        element.shouldBe(visible).click();
    }

    protected void setValue(SelenideElement element, String value) {
        element.shouldBe(visible).setValue(value);
    }

    protected String getText(SelenideElement element) {
        return element.shouldBe(visible).getText();
    }
}
