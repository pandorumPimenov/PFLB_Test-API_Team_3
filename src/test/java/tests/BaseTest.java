package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import listeners.TestListener;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.AllureUtils;
import utils.PropertyReader;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BaseTest {
    protected LoginPage loginPage;
    protected MenuPage menuPage;
    protected CreateUserPage createUserPage;
    protected AddMoneyPage addMoneyPage;
    protected UsersReadAllPage usersReadAllPage;
    protected ReadUserWithCarsPage readUserWithCarsPage;
    protected CarPage carPage;
    protected HousesReadAllPage housesReadAllPage;
    protected CreateHousePage createHousePage;
    protected HousesReadOneByIdPage housesReadOneByIdPage;
    protected SettleOrEvictUserPage settleOrEvictUserPage;

    protected final String user = System.getProperty("user", PropertyReader.getProperty("user"));
    protected final String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        // Очищаем предыдущий драйвер
        closeWebDriver();

        // Настройки Selenide
        Configuration.timeout = 20000;
        Configuration.clickViaJs = true;
//        Configuration.headless = true; // Для отладки отключаем headless

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
//            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            options.addArguments("--start-maximized");
            Configuration.browserCapabilities = options;
            Configuration.browser = "chrome";
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized");
            Configuration.browserCapabilities = options;
            Configuration.browser = "firefox";
        }

        initializePages();
        setupAllure();

        // Открываем базовый URL
        open(PropertyReader.getProperty("base.url"));
    }

    private void initializePages() {
        loginPage = new LoginPage();
        menuPage = new MenuPage();
        createUserPage = new CreateUserPage();
        addMoneyPage = new AddMoneyPage();
        usersReadAllPage = new UsersReadAllPage();
        housesReadAllPage = new HousesReadAllPage();
        housesReadOneByIdPage = new HousesReadOneByIdPage();
        settleOrEvictUserPage = new SettleOrEvictUserPage();
        createHousePage = new CreateHousePage();
        readUserWithCarsPage = new ReadUserWithCarsPage();
        carPage = new CarPage();
        softAssert = new SoftAssert();

    }

    private void setupAllure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @AfterMethod
    public void tearDown(ITestResult result) {


        if (result.getStatus() == ITestResult.FAILURE) {
            AllureUtils.takeScreenshot();
        }

        if (getWebDriver() != null) {
            closeWebDriver();
        }
        softAssert.assertAll();
    }
}
