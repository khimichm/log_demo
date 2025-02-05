package com.logi.qa.test.ui.Pages;

import com.codeborne.selenide.SelenideElement;
import com.logi.qa.test.ui.Dialogs.CreateNewConnectionDialog;

import static com.codeborne.selenide.Selenide.$;

/**
 * @author mkhimich
 */
public class ConnectionsPage extends PageWithPanels {

    private static final String CONNECTIONSPAGE_CREATE_NEW = ".control-panel-operation .btn.btn-primary";
    private static final String CONNECTIONSPAGE_SELECT_CONNECTOR = ".connection-table .content-row";
    private static final String CONNECTIONSPAGE_DELETE_SELECTED = ".delete-button";


    public CreateNewConnectionDialog openCreateNewConnectionDialog() {
        getCreateNewButton().click();
        return new CreateNewConnectionDialog();
    }

    public void createNewJDBCConnection(String sourceName) {
        openCreateNewConnectionDialog().createNewJDBCConnection(sourceName);


    }

    public SelenideElement getCreateNewButton() {
        return $(CONNECTIONSPAGE_CREATE_NEW);
    }


    public void deleteConnector(String sourceName) {
        getConnectionRow(sourceName).click();
        getDeleteSelectedButton().click();
        getYesNoDialog().confirm();

    }

    private SelenideElement getDeleteSelectedButton() {
        return $(CONNECTIONSPAGE_DELETE_SELECTED);
    }

    private SelenideElement getConnectionRow(String sourceName) {
        return $(".content-row:has(.row-title:contains(" + sourceName + "))");
    }
}
