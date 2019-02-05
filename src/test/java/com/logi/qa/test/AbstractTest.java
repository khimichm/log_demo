package com.logi.qa.test;


import com.codeborne.selenide.logevents.SelenideLogger;
import com.logi.qa.test.Util.PropertiesContext;
import com.logi.qa.test.Util.WebDriverWrapper;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;


/**
 * @author mkhimich
 */
public abstract class AbstractTest {
    protected PropertiesContext context;
    protected WebDriver driver = null;


    public AbstractTest() {
        context = PropertiesContext.getInstance();
    }

    @BeforeTest
    public void tearUp(ITestContext testContext) {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        System.out.println("========================== START TEST " + testContext.getName() + " " +
            "==============================");
        System.out.println("Server URL = " + context.getProperty("app.url"));
        driver = WebDriverWrapper.getWebDriver();
//        Configuration.browser = EnvironmentUtil.getInstance().getBrowser().toString().toLowerCase();
//        driver = WebDriverWrapper.getWebDriver();
    }

    @AfterTest
    public void tearDown(ITestContext testContext) {
        System.out.println("========================== END TEST " + testContext.getName() + " " +
            "==============================");
        driver.quit();
    }

    public String getRandomString() {
        return RandomStringUtils.random(5, true, false);
    }
}
