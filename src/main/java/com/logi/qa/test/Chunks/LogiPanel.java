package com.logi.qa.test.Chunks;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * @author mkhimich
 */
public class LogiPanel {
    public static final String LOGI_PANEL_SELECTOR = ".logi-panel";
    public static final String LOGI_DATA_AUTHORING_BUTTON = ".logi-panel a[title='Data Authoring']";

    public SelenideElement getDataAuthoring() {
        return $(LOGI_DATA_AUTHORING_BUTTON);
    }

    public SelenideElement getElement() {
        return $(LOGI_PANEL_SELECTOR);
    }
}
