package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import listeners.TestListener;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import pages.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Listeners(TestListener.class)
public class BaseTest {
    protected LoginPage loginPage;
    protected MenuPage menuPage;
    protected CreateUserPage createUserPage;
    protected AddMoneyPage addMoneyPage;
    protected SoftAssert softAssert;
    protected UsersReadAllPage usersReadAllPage;
    protected ReadUserWithCarsPage readUserWithCarsPage;
    protected CarPage carPage;
    protected HousesReadAllPage housesReadAllPage;
    protected CreateHousePage createHousePage;
    protected HousesReadOneByIdPage housesReadOneByIdPage;
    protected SettleOrEvictUserPage settleOrEvictUserPage;

    protected final String user = System.getProperty("user", PropertyReader.getProperty("user"));
    protected final String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @BeforeMethod
    public void setup() {
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
        housesReadAllPage = new HousesReadAllPage();
        housesReadOneByIdPage = new HousesReadOneByIdPage();
        settleOrEvictUserPage = new SettleOrEvictUserPage();
        createHousePage = new CreateHousePage();
        readUserWithCarsPage = new ReadUserWithCarsPage();
        carPage = new CarPage();
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
