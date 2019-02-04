package com.logi.qa.test;


import com.logi.qa.test.Util.PropertiesContext;
import com.logi.qa.test.Util.WebDriverWrapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.Timeout;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * @author mkhimich
 */
public abstract class AbstractTest {
    protected PropertiesContext context;
    protected WebDriver driver = null;

    @Rule
    public TestName testName = new TestName();
    //Any test that might hang will be forced to end within 120 seconds
    @Rule
    public Timeout globalTimeout = new Timeout(120, TimeUnit.SECONDS);

    public AbstractTest() {
        context = PropertiesContext.getInstance();
    }

    @Before
    public void tearUp() {
        System.out.println("========================== START TEST " + testName.getMethodName() + " " +
            "==============================");
        System.out.println("Server URL = " + context.getProperty("app.url"));
        driver = WebDriverWrapper.getWebDriver();
//        Configuration.browser = EnvironmentUtil.getInstance().getBrowser().toString().toLowerCase();
//        driver = WebDriverWrapper.getWebDriver();
    }

    @After
    public void tearDown() {
        System.out.println("========================== END TEST " + testName.getMethodName() + " " +
            "==============================");
        driver.quit();
    }

    public String getRandomString() {
        return RandomStringUtils.random(5, true, false);
    }
}
