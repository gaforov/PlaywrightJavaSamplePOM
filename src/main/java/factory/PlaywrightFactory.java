package factory;

import com.microsoft.playwright.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    public static Page page;
    public static Properties properties;

    private final ThreadLocal<Browser> threadLocalBrowser = new ThreadLocal<>();
    private final ThreadLocal<BrowserContext> threadLocalBrowserContext = new ThreadLocal<>();
    private final ThreadLocal<Page> threadLocalPage = new ThreadLocal<>();
    private final ThreadLocal<Playwright> threadLocalPlaywright = new ThreadLocal<>();

    public Playwright getPlaywright() {
        return threadLocalPlaywright.get();
    }

    public Browser getBrowser() {
        return threadLocalBrowser.get();
    }

    public BrowserContext getBrowserContext() {
        return threadLocalBrowserContext.get();
    }

    public Page getPage() {
        return threadLocalPage.get();
    }

    public Page initBrowser(Properties properties) {
        String browserName = properties.getProperty("browser").trim();
        System.out.println("browser name: " + browserName);
        //playwright = Playwright.create(); //< replacing with thread local
        threadLocalPlaywright.set(Playwright.create());

        switch (browserName.toLowerCase()) {
            //case "chrome" -> browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            case "chrome" -> threadLocalBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
            //case "edge" -> browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false));
            case "edge" -> threadLocalBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false)));
            //case "firefox" -> browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
            case "firefox" -> threadLocalBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
            //case "safari" -> browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
            case "safari" -> threadLocalBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
            default -> throw new RuntimeException("Unsupported browser. Please use 'chrome', 'firefox', 'edge', or 'safari'");
        }


        //browserContext = browser.newContext();
        threadLocalBrowserContext.set(getBrowser().newContext());
        //page = browserContext.newPage();
        threadLocalPage.set(getBrowserContext().newPage());
        //page.navigate(properties.getProperty("url"));
        getPage().navigate(properties.getProperty("url").trim());
        //return page;
        return getPage();
    }

    public Properties initProperties() {
        try {
            FileInputStream fis = new FileInputStream("./src/test/resources/config/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}
