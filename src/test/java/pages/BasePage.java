package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import java.util.Properties;

import static com.codeborne.selenide.Condition.visible;

@Log4j2
public class BasePage {
    protected static final String BASE_URL = loadBaseUrl();

    private static String loadBaseUrl() {
        log.info("Loading base URL from config.properties");
        var props = new Properties();
        try (var input = BasePage.class.getResourceAsStream("/config.properties")) {
            props.load(input);
            String url = props.getProperty("base.url"); // URL берётся из файла
            log.info("Successfully loaded base URL: {}", url);
            return url;
        } catch (Exception e) {
            throw new RuntimeException("Не удалось загрузить base.url из config.properties", e);
        }
    }

    protected void openPath(String path) {
        Selenide.open(BASE_URL + path);
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
