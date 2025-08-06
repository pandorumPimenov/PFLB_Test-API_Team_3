package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {
    LoginPage loginPage = new LoginPage();
    String user = System.getProperty("user", PropertyReader.getProperty("user"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));
    SoftAssert softAssert = new SoftAssert();
    @BeforeMethod
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.timeout = 5000;
        Configuration.headless = false;
        Configuration.clickViaJs = true;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--maximized");
//      options.addArguments("--incognito");
        Configuration.browserCapabilities = options;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @AfterMethod
    public void tearDown() {
        if(getWebDriver() !=null) {
            closeWebDriver();
        }
    }
}
