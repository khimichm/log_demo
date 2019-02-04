package com.logi.qa.test.Util;

import com.codeborne.selenide.WebDriverRunner;
import com.logi.qa.test.Util.Environment.Browser;
import com.logi.qa.test.Util.Environment.EnvironmentUtil;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author mkhimich
 */
public class WebDriverWrapper {
    /**
     * Method get the browser instance and try to create WebDriver object for test
     *
     * @return WebDriver after initialization
     */
    public static WebDriver getWebDriver() {
        return getWebDriver(EnvironmentUtil.getInstance().getBrowser());
    }

    /**
     * Method helps create new WebDriver object for test accordingly to browser
     *
     * @param browser Browser
     *
     * @return driver WebDriver
     */
    public static WebDriver getWebDriver(Browser browser) {
        EnvironmentUtil util = EnvironmentUtil.getInstance();
        PropertiesContext context = PropertiesContext.getInstance();
        WebDriver driver = null;
        switch (browser) {
            case IE:
                System.out.println("Setting iedriver...");
                System.setProperty("webdriver.ie.driver", context.getDriverPath("win32.ie.driver"));
                driver = new InternetExplorerDriver();
                break;
            case FIREFOX:
                System.out.println("Setting Geckodriver...");
                switch (util.getOs()) {
                    case WINDOWS:
                        System.setProperty("webdriver.gecko.driver", context.getDriverPath("win.gecko.driver"));
                        break;
                    case LINUX:
                        //linux code here
                        break;
                    case MAC:
                        System.setProperty("webdriver.gecko.driver", context.getDriverPath("mac.gecko.driver"));
                        break;
                }
                driver = new FirefoxDriver();

                break;
            case CHROME:
                System.out.println("Setting chromedriver...");
                switch (util.getOs()) {
                    case WINDOWS:
                        System.setProperty("webdriver.chrome.driver", context.getDriverPath("win.chrome.driver"));
                        break;
                    case LINUX:
                        //linux code here
                        break;
                    case MAC:
                        System.setProperty("webdriver.chrome.driver", context.getDriverPath("mac.chrome.driver"));
                        break;
                }
                driver = new ChromeDriver();
                System.out.println("Set Driver to Chrome");
                break;
            case EDGE:
                System.out.println("Setting edgedriver...");
                System.setProperty("webdriver.edge.driver", context.getDriverPath("win.edge.driver"));
                driver = new EdgeDriver();
                System.out.println("Set Driver to EdgeDriver");
                //For opening clean browser every time
                driver.get("about:InPrivate");
                break;
        }
        Configuration.browser = browser.toString().toLowerCase();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
        return driver;
    }
}