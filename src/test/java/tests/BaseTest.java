package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Listeners(TestListener.class)
public class BaseTest {
    WebDriver driver;
    protected LoginPage loginPage;
    protected MenuPage menuPage;
    protected CreateUserPage createUserPage;
    protected AddMoneyPage addMoneyPage;
    protected SoftAssert softAssert;
    protected UsersReadAllPage usersReadAllPage;
    protected ReadUserWithCarsPage readUserWithCarsPage;
    protected CarsPage carsPage;

    protected final String user = System.getProperty("user", PropertyReader.getProperty("user"));
    protected final String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
        }
        initializePages();
        configureBrowser();
        setupAllure();
    }

    private void initializePages() {
        loginPage = new LoginPage();
        menuPage = new MenuPage();
        createUserPage = new CreateUserPage();
        addMoneyPage = new AddMoneyPage();
        usersReadAllPage = new UsersReadAllPage();
        readUserWithCarsPage = new ReadUserWithCarsPage();
        carsPage = new CarsPage();
        softAssert = new SoftAssert();
    }

    private void configureBrowser() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.headless = false;
        Configuration.clickViaJs = true;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        Configuration.browserCapabilities = options;
    }

    private void setupAllure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @AfterMethod
    public void tearDown() {
        softAssert.assertAll();
        closeWebDriver();
    }
}
