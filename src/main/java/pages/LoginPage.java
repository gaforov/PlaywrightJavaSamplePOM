package pages;

import com.microsoft.playwright.Page;
import factory.PlaywrightFactory;

public class LoginPage extends PlaywrightFactory {
    private Page page;

    private String userEmailField = "input#input-email";
    private String passwordField = "input#input-password";
    private String loginButton = "//input[@value='Login']";
    private String forgotPasswordLink = "(//a[contains(text(),'Forgotten Password')])[1]";
    private String logoutLink = "(//a[contains(text(),'Logout')])[2]";

    public LoginPage(Page page) {
        this.page = page;
    }

    public String getLoginPageTitle() {
        return page.title();
    }

    public boolean isForgotPasswordLinkExist() {
        return page.isVisible(forgotPasswordLink);
    }

    public boolean doLogin(String username, String password) {
        System.out.println("User credentials: " + username + " : " + password);
        page.fill(userEmailField, username);
        page.fill(passwordField, password);
        page.click(loginButton);
        if (page.isVisible(logoutLink)) {
            System.out.println("User is logged in successfully");
            return true;
        }
        return false;
    }
}
