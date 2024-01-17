package factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory_old {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    public static Page page;
    public static Properties properties;


    public Page initBrowser(Properties properties) {
        String browserName = properties.getProperty("browser").trim();
        System.out.println("browser name: " + browserName);
        playwright = Playwright.create(); //< replacing with thread local

        switch (browserName.toLowerCase()) {
            case "chrome" -> browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            case "edge" -> browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false));
            case "firefox" -> browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
            case "safari" -> browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
            default -> throw new RuntimeException("Unsupported browser. Please use 'chrome', 'firefox', 'edge', or 'safari'");
        }

        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.navigate(properties.getProperty("url"));
        return page;
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
