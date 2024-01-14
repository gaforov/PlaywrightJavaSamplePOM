package base;

import factory.PlaywrightFactory;
import pages.HomePage;
import pages.LoginPage;

public class PageInitializer extends PlaywrightFactory {
    public static HomePage homePage;
    public static LoginPage loginPage;
    public static PlaywrightFactory pf;

    public static void initialize() {
        homePage = new HomePage(page);
        loginPage = new LoginPage(page);
        pf = new PlaywrightFactory();
    }
}
