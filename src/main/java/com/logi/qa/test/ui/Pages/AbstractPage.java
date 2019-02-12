package com.logi.qa.test.ui.Pages;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import com.logi.qa.test.ui.Chunks.AbstractUIData;
import com.logi.qa.test.ui.Chunks.UIData;
import com.logi.qa.test.ui.Util.PropertiesContext;

/**
 * @author mkhimich
 */
public abstract class AbstractPage extends AbstractUIData {
    protected SelenideDriver driver;
    protected PropertiesContext context = PropertiesContext.getInstance();

    public UIData getParent() {
        return null;
    }

    public PropertiesContext getContext() {
        return context;
    }

    public SelenideDriver getDriver() {
        return driver;
    }

    public String getSelector() {
        return "";
    }

    public void reload() {
        driver.refresh();
    }

}
