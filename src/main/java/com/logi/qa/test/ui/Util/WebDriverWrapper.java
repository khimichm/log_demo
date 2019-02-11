package com.logi.qa.test.ui.Util;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.WebDriverRunner;
import com.logi.qa.test.ui.Util.Environment.Browser;
import com.logi.qa.test.ui.Util.Environment.EnvironmentUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.nio.file.Paths;

/**
 * @author mkhimich
 */
public class WebDriverWrapper {

    private static final PropertiesContext context = PropertiesContext.getInstance();
    /**
     * Method get the browser instance and try to create WebDriver object for test
     *
     * @return WebDriver after initialization
     */
    public static SelenideDriver getWebDriver() {
        return getWebDriver(EnvironmentUtil.getInstance().getBrowser());
    }

    /**
     * Method helps create new WebDriver object for test accordingly to browser
     *
     * @param browser Browser
     *
     * @return driver WebDriver
     */
    private static SelenideDriver getWebDriver(Browser browser) {
        EnvironmentUtil util = EnvironmentUtil.getInstance();

        WebDriver driver = null;
        switch (browser) {
            case IE:
                System.out.println("Setting iedriver...");
                System.setProperty("webdriver.ie.driver", getDriverPath("win32.ie.driver"));
                driver = new InternetExplorerDriver();
                break;
            case FIREFOX:
                System.out.println("Setting Geckodriver...");
                switch (util.getOs()) {
                    case WINDOWS:
                        System.setProperty("webdriver.gecko.driver", getDriverPath("win.gecko.driver"));
                        break;
                    case LINUX:
                        //linux code here
                        break;
                    case MAC:
                        System.setProperty("webdriver.gecko.driver", getDriverPath("mac.gecko.driver"));
                        break;
                }
                driver = new FirefoxDriver();

                break;
            case CHROME:
                System.out.println("Setting chromedriver...");
                switch (util.getOs()) {
                    case WINDOWS:
                        System.setProperty("webdriver.chrome.driver", getDriverPath("win.chrome.driver"));
                        break;
                    case LINUX:
                        //linux code here
                        break;
                    case MAC:
                        System.setProperty("webdriver.chrome.driver", getDriverPath("mac.chrome.driver"));
                        break;
                }
                driver = new ChromeDriver();
                System.out.println("Set Driver to Chrome");
                break;
            case EDGE:
                System.out.println("Setting edgedriver...");
                System.setProperty("webdriver.edge.driver", getDriverPath("win.edge.driver"));
                driver = new EdgeDriver();
                System.out.println("Set Driver to EdgeDriver");
                //For opening clean browser every time
                driver.get("about:InPrivate");
                break;
        }
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
        return WebDriverRunner.getSelenideDriver();
    }

    public static String getDriverPath(String key) {
        return Paths.get(context.USER_DIR, context.getProperty(key)).toString();
    }
}