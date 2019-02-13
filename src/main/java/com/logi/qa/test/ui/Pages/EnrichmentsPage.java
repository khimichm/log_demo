package com.logi.qa.test.ui.Pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class EnrichmentsPage extends PageWithPanels {

    private static final String CREATE_NEW_ENRICHMENT_DVD = "a[href='/composer/data-manager/enrichments/new-enrichment'";
    private static final String CREATED_ENRICHMENTS_LIST = ".table-data-gallery .content-row";
    private static final String DELETE_SELECTED = ".delete-button";

    public void createNewEnrichment(String connectionName, String referenceName, String enrichmentName) {
        getCreateNewEnrichment().click();
        CreateNewEnrichmentPage page = new CreateNewEnrichmentPage();
        page.selectConnection(connectionName);
        page.selectReferenceByName(referenceName);
        page.clickOnSelectColumns();
        page.getEnrichmentNameInput().setValue(enrichmentName);
        page.selectNoneColumnsButtonClick();
        page.selectAllColumnsButtonClick();
        page.getSaveNewEnrichmentButton().click();
    }

    public SelenideElement getCreatedEnrichment(String enrichmentName) {
        return $(".content-row:has(.row-title:contains(" + enrichmentName + "))");
    }

    private List<SelenideElement> getEnrichmentsList() {
        return $$(CREATED_ENRICHMENTS_LIST);
    }

    private SelenideElement getCreateNewEnrichment() {
        return $(CREATE_NEW_ENRICHMENT_DVD);
    }

    public void deleteEnrichment(String enrichmentName) {
        getCreatedEnrichment(enrichmentName).click();
        getDeleteSelectedButton().click();
        getYesNoDialog().confirm();
    }

    private SelenideElement getDeleteSelectedButton() {
        return $(DELETE_SELECTED);
    }


}
