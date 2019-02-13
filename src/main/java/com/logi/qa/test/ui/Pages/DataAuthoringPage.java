package com.logi.qa.test.ui.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * @author mkhimich
 */
public class DataAuthoringPage extends PageWithPanels {

    public static final String DATAAUTHORING_CREATE_NEW_CONNECTION = ".data-option a[href='/composer/data-manager/connections'";
    public static final String DATAAUTHORING_CREATE_NEW_REFERENCE = ".data-option a[href='/composer/data-manager/references'";
    public static final String DATAAUTHORING_VIEW_ALL_ENRICHMENTS = ".data-option a[href='/composer/data-manager/enrichments'";

    public ConnectionsPage goToCreateNewConnection() {
        getCreateNewConnection().click();
        return new ConnectionsPage();
    }

    private SelenideElement getCreateNewConnection() {
        return $(DATAAUTHORING_CREATE_NEW_CONNECTION);
    }

    public ReferencesPage goToReferences() {
        getReferencesLink().click();
        return new ReferencesPage();
    }


    public EnrichmentsPage viewAllEnrichments() {
        getViewAllEnrichments().click();
        return new EnrichmentsPage();
    }

    private SelenideElement getReferencesLink() {
        return $(DATAAUTHORING_CREATE_NEW_REFERENCE);
    }

    private SelenideElement getViewAllEnrichments() {
        return $(DATAAUTHORING_VIEW_ALL_ENRICHMENTS);
    }

}
