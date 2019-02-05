package com.logi.qa.test.ui.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * @author mkhimich
 */
public class StartPage extends PageWithPanels {
    private final static String LOGI_CONTENT = ".content";

    public boolean isPageLoaded() {
        return getLogiPanel().getElement().exists() && getLogiContent().exists();
    }

    public SelenideElement getLogiContent() {
        return $(LOGI_CONTENT);
    }

}
