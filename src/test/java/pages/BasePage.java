package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import static com.codeborne.selenide.Condition.visible;

@Log4j2
public class BasePage {
    protected static final String BASE_URL = "http://82.142.167.37:4881/";

    protected void openPath(String path) {
        String fullUrl = BASE_URL + path;
        log.info("Opening URL: {}", fullUrl);
        Selenide.open(fullUrl);
        log.info("URL opened successfully: {}", fullUrl);
    }

    protected void clickElement(SelenideElement element) {
        log.info("Clicking element: {}", element.getSearchCriteria());
        element.shouldBe(visible).click();
        log.info("Element clicked successfully: {}", element.getSearchCriteria());
    }

    protected String getText(SelenideElement element) {
        log.info("Getting text from element: {}", element.getSearchCriteria());
        String text = element.shouldBe(visible).getText();
        log.info("Text retrieved: '{}' from element: {}", text, element.getSearchCriteria());
        return text;
    }
}
