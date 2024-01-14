package pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private Page page;
    // 1. String Locators (Object Repository)
    private String search = "input[name='search']";
    private String searchIcon = "button[class='btn btn-default btn-lg']";
    private String searchResultHeader = "div#content h1";
    private String loginLink = "a:text('Login')";
    private String myAccountLink = "//a[@title='My Account']";

    public HomePage(Page page) {
        this.page = page;
    }

    // 3. page actions/methods
    public String getHomePageTitle() {
        String title = page.title();
        System.out.println("Page title = " + title);
        return title;
    }

    public String getHomePageURL() {
        String url = page.url();
        System.out.println("Page url = " + url);
        return url;
    }

    public String doSearch(String searchKeyword) {
        page.fill(search, searchKeyword);
        page.click(searchIcon);
        String searchHeaderText = page.textContent(searchResultHeader);
        System.out.println("searchHeaderText = " + searchHeaderText);
        return searchHeaderText;
    }

    public LoginPage navigateToLoginPage() {
        page.click(myAccountLink);
        page.click(loginLink);
        return new LoginPage(page);
    }
}
