package com.logi.qa.test.ui.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class EnrichmentsPage extends PageWithPanels{

    private static final String CREATE_NEW_ENRICHMENT_DVD = ".data-option a[href='/composer/data-manager/enrichments'";


    public CreateNewEnrichmentPage openCreateNewEnrichmentPage() {
        getCreateNewEnrichment().click();
        return new CreateNewEnrichmentPage();
    }



    private SelenideElement getCreateNewEnrichment(){return $(CREATE_NEW_ENRICHMENT_DVD);}

}
