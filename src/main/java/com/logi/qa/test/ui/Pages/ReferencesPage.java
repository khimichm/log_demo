package com.logi.qa.test.ui.Pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class ReferencesPage extends PageWithPanels {
    private static final String CREATE_NEW_REFERENCE = "a[href='/composer/data-manager/references/new-reference']";
    private static final String DELETE_SELECTED = ".delete-button";

    public void createNewReference(String dbName, String tableName) {
        getCreateNewButton().click();
        CreateNewReferencePage page = new CreateNewReferencePage();
        page.selectDB(dbName);
        page.getCreateFromRadioButton().click();
        page.getSelectTable().setValue(tableName);
        page.selectConnectionByName(tableName);
        page.getCreateNewReference().click();
        page.confirm();

    }

    private SelenideElement getCreateNewButton() {
        return $(CREATE_NEW_REFERENCE);
    }

    public SelenideElement getCreatedReference(String tableName) {
        return $(".content-row:has(span:contains(" + tableName + "))");
    }

    public void deleteReference(String tableName) {
        getCreatedReference(tableName).click();
        getDeleteSelectedButton().click();
        getYesNoDialog().confirm();
    }

    private SelenideElement getDeleteSelectedButton() {
        return $(DELETE_SELECTED);
    }
}
