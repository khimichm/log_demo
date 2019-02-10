package com.logi.qa.test.ui.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ReferencesPage extends AbstractPage{
    private static final String CREATE_NEW_REFERENCE = "a[href=/composer/data-manager/references/new-reference]";
    public CreateNewReferencePage createNewReference(String dbName) {
        getCreateNewButton().click();
        return new CreateNewReferencePage();
    }

    private SelenideElement getCreateNewButton() {
        return $(CREATE_NEW_REFERENCE);
    }
}
