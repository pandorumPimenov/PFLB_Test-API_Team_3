package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import listeners.TestListener;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.MenuPage;
import pages.cars.CarsPage;
import pages.houses.CreateHousePage;
import pages.houses.HousesReadAllPage;
import pages.houses.HousesReadOneByIdPage;
import pages.houses.SettleOrEvictUserPage;
import pages.users.AddMoneyPage;
import pages.users.CreateUserPage;
import pages.users.ReadUserWithCarsPage;
import pages.users.UsersReadAllPage;
import utils.AllureUtils;
import utils.PropertyReader;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BaseTest {
    protected final String user = System.getProperty("user", PropertyReader.getProperty("user"));
    protected final String password = System.getProperty("password", PropertyReader.getProperty("password"));
    protected LoginPage loginPage;
    protected MenuPage menuPage;
    protected CreateUserPage createUserPage;
    protected AddMoneyPage addMoneyPage;
    protected SoftAssert softAssert;
    protected UsersReadAllPage usersReadAllPage;
    protected ReadUserWithCarsPage readUserWithCarsPage;
    protected CarsPage carsPage;
    protected HousesReadAllPage housesReadAllPage;
    protected CreateHousePage createHousePage;
    protected HousesReadOneByIdPage housesReadOneByIdPage;
    protected SettleOrEvictUserPage settleOrEvictUserPage;

    @BeforeMethod
    public void setup() {
        initializePages();
        configureBrowser();
        setupAllure();
//        iTestContext.setAttribute("driver", WebDriverRunner.getWebDriver());
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
    public void tearDown(ITestResult result) {
        softAssert.assertAll();

        if (result.getStatus() == ITestResult.FAILURE) {
            AllureUtils.takeScreenshot();
        }

        if (getWebDriver() != null) {
            closeWebDriver();
        }
    }
}
