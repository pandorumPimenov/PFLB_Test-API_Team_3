package utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;

public class AllureUtils {

    @Attachment(value = "Screenshot (Selenide)", type = "image/png")
    public static byte[] takeScreenshot() {
        // Проверяем, что драйвер инициализирован
        if (WebDriverRunner.hasWebDriverStarted()) {
            return Selenide.screenshot(OutputType.BYTES);
        }
        return new byte[0];
    }
}
