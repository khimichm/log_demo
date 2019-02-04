package com.logi.qa.test.Pages;

import com.logi.qa.test.Chunks.AbstractUIData;
import com.logi.qa.test.Chunks.UIData;
import com.logi.qa.test.Util.PropertiesContext;
import org.openqa.selenium.WebDriver;

/**
 * @author mkhimich
 */
public abstract class AbstractPage extends AbstractUIData {
    protected WebDriver driver;
    protected PropertiesContext context = PropertiesContext.getInstance();

    public UIData getParent() {
        return null;
    }

    public PropertiesContext getContext() {
        return context;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getSelector() {
        return "";
    }

    public void reload() {
        driver.navigate().refresh();
    }

}
