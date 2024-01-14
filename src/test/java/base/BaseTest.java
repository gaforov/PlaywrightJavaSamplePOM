package base;

import com.microsoft.playwright.Page;
import factory.PlaywrightFactory;
import pages.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.LoginPage;

import java.util.Properties;

public class BaseTest {
    PlaywrightFactory pf;
    Page page;
    protected Properties properties;
    protected HomePage homePage;
    protected LoginPage loginPage;

    @BeforeTest()
    protected void setUp() {
        pf = new PlaywrightFactory();
        properties = pf.initProperties();
        page = pf.initBrowser(properties);
        homePage = new HomePage(page);
    }

    @AfterTest
    protected void tearDown() {
        //page.context().browser().close();
        page.pause();
    }
}
