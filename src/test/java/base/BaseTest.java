package base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest extends PageInitializer {

    @BeforeTest
    protected void setUp() {
        initialize();
        properties = pf.initProperties();
        page = pf.initBrowser(properties);
    }

    @AfterTest
    protected void tearDown() {
        page.context().browser().close();
        //page.pause();
    }
}
