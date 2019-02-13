package com.logi.qa.test.ui.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class ReferencesPage extends PageWithPanels {
    private static final String CREATE_NEW_REFERENCE = "a[href='/composer/data-manager/references/new-reference'";
    private static final String CREATED_REFERENCES_LIST = ".table-data-gallery .content-row";

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

    private List<SelenideElement> getReferenceList() {
        return $$(CREATED_REFERENCES_LIST);
    }
}
