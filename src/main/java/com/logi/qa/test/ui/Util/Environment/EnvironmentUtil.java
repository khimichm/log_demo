package com.logi.qa.test.ui.Util.Environment;


import com.logi.qa.test.ui.Util.PropertiesContext;

/**
 * Class setups OS and Browser environment
 * <p>
 *
 * @author mkhimich
 */
public class EnvironmentUtil {
    private static final String OS_NAME_KEY = "os.name";

    private static final String RUN_BROWSER_DRIVER = "browser.driver";
    private static final String BROWSER_NAME = "browser.name";

    private Browser browser;
    private OS os;
    private static EnvironmentUtil instance = new EnvironmentUtil();

    /**
     * Default constructor.
     * Browser and operating system are initialised here
     */
    private EnvironmentUtil() {
        this.browser = initBrowser();
        this.os = initOS();

    }

    /**
     * @return instance EnvironmentUtil
     */
    public static EnvironmentUtil getInstance() {
        return instance;
    }

    /**
     * @return browser Browser
     */
    public Browser getBrowser() {
        return browser;
    }

    /**
     * @return os OS
     */
    public OS getOs() {
        return os;
    }

    /**
     * Method to initialize the browser.
     *
     * @return the browser
     */
    private Browser initBrowser() {
        Browser type;
        String browserName = PropertiesContext.getInstance().getProperty(BROWSER_NAME);
        System.out.println("Got browser name " + browserName);
        type = getBrowserByName(browserName);
        System.out.println("Browser type is set to " + type.name());
        return type;
    }

    /**
     * Method returns the browser according to a integer key
     *
     * @param key int
     * @return browser Browser
     */
    private Browser getBrowserByIntKey(int key) {
        Browser type;
        switch (key) {
            case 1:
                type = Browser.FIREFOX;
                break;
            case 2:
                type = Browser.EDGE;
                break;
            case 3:
                type = Browser.CHROME;
                break;
            case 4:
                type = Browser.IE;
                break;
            default:
                type = Browser.CHROME;
        }

        return type;
    }

    private Browser getBrowserByName(String name) {
        Browser type;
        switch (name) {
            case "FIREFOX":
                type = Browser.FIREFOX;
                break;
            case "EDGE":
                type = Browser.EDGE;
                break;
            case "CHROME":
                type = Browser.CHROME;
                break;
            case "IE":
                type = Browser.IE;
                break;
            default:
                type = Browser.CHROME;
        }

        return type;
    }

    /**
     * Method to initialize the OS.
     *
     * @return operating system OS
     */
    private OS initOS() {
        String osKey = System.getProperty(OS_NAME_KEY);
        return getOsByKey(osKey);
    }

    /**
     * Method returns the OS according to a String key
     *
     * @param osKey String
     * @return operating system OS
     */
    public OS getOsByKey(String osKey) {
        OS os;
        System.out.println("OS is " + System.getProperty(OS_NAME_KEY));
        if (osKey.startsWith("Windows")) {
            os = OS.WINDOWS;
        } else if (osKey.startsWith("Linux")) {
            os = OS.LINUX;
        } else if (osKey.startsWith("Mac")) {
            os = OS.MAC;
        } else {
            throw new IllegalStateException("Unknown operating system " + osKey);
        }

        return os;
    }
}

