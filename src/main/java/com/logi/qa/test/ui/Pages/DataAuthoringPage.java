package com.logi.qa.test.ui.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * @author mkhimich
 */
public class DataAuthoringPage extends PageWithPanels {

    public static final String DATAAUTHORING_CREATE_NEW_CONNECTION = ".data-option " +
        "a[href='/composer/data-manager/connections'";

    public ConnectionsPage createNewConnection() {
        getCreateNewConnection().click();
        return new ConnectionsPage();
    }

    private SelenideElement getCreateNewConnection() {
        return $(DATAAUTHORING_CREATE_NEW_CONNECTION);
    }

}
