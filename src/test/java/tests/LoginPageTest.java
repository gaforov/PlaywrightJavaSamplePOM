package tests;

import base.BaseTest;
import constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {
    @Test(priority = 1)
    public void loginPageNavigationTest() {
        loginPage = homePage.navigateToLoginPage();
        String actualLoginPageTitle = loginPage.getLoginPageTitle();
        System.out.println("Actual login page title: " + actualLoginPageTitle);
        Assert.assertEquals(actualLoginPageTitle, AppConstants.LOGIN_PAGE_TITLE, "Login Page Title is mismatch.");
    }

    @Test(priority = 2)
    public void forgotPasswordLinkExistTest() {
        loginPage = homePage.navigateToLoginPage();
        Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
    }

    @Test(priority = 3)
    public void appLoginTest() {
        loginPage = homePage.navigateToLoginPage();
        Assert.assertTrue(loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password")));
    }

}
