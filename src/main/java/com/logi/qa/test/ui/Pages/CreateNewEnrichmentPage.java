package com.logi.qa.test.ui.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreateNewEnrichmentPage extends PageWithPanels {

    private static final String CONNECTIONS_LIST = ".new-enrichment-menu select";
    private static final String LIST_OF_REFERENCES_FORM_SOURCE = ".dataviews-list .dataview-item";
    private static final String LIST_OF_REFERENCES_LOAD = ".dataviews-list";
    private static final String IS_COLUMNS_LOADED = ".add-dataview.disable";
    private static final String ENRICHMENT_NAME_INPUT = "#enrichment-name-input";
    private static final String SAVE_NEW_ENRICHMENT_BUTTON = ".settings-panel-buttons .btn-primary";
    private static final String CANCEL_CREATE_NEW_ENRICHMENT = ".settings-panel-buttons .btn-light";
    private static final String PREVIEW_BUTTON = ".preview-records";
    private static final String EXPAND_NEW_ENRICHMENT_MENU = ".collapsing-button .arrow-right";
    private static final String COLLAPSE_NEW_ENRICHMENT_MENU = ".collapsing-button .arrow-left";
    private static final String NUMBER_OF_RECORDS_INPUT = "#records-input";
    private static final String SELECT_COLUMNS_BUTTON = "Select Column";
    private static final String SELECT_COLUMN_OPERATION = ".select-oparation button";
    private static final String MENU_SUB_TAB_BUTTONS = ".menu-sub-tab";

    public void selectConnection(String connectionName) {
        getDBOption(connectionName).shouldBe(visible);
        connectionsList().selectOption(connectionName);
        waitForReferencesListLoad();
    }

    private SelenideElement getDBOption(String connectionName) {
        return $(".new-enrichment-menu select option:contains(" + connectionName + ")");
    }

    public void waitForReferencesListLoad() {
        referencesList().waitUntil(appear, 10000);
    }

    public void waitForDataLoad() {
        for (SelenideElement element : isDataLoaded()) {
            element.shouldNotBe(disabled);
        }
    }

    public void selectReferenceByName(String referenceName) {
        SelenideElement referenceElement = getAddReferenceByName(referenceName);
        referenceElement.waitUntil(Condition.cssValue("background-color", "rgba(241, 97, 84, 1)"), 10000);
        getAddReferenceByName(referenceName).click();
    }

    private SelenideElement getAddReferenceByName(String referenceName) {
        return $(".dataview-item:has(.dataview-item-name:contains(" + referenceName + ")) .add-dataview");
    }

    public void selectAllColumnsButtonClick() {
        for (SelenideElement element : getSelectColumnsOperation()) {
            if (element.getText().equals("All")) {
                element.click();
            }
        }
    }

    public void selectNoneColumnsButtonClick() {
        for (SelenideElement element : getSelectColumnsOperation()) {
            if (element.getText().equals("None")) {
                element.click();
            }
        }
    }

    public void clickOnSelectColumns() {
        for (SelenideElement element : getMenuSubTab()) {
            if (element.getText().contains("Select Column")) {
                element.click();
            }
        }

    }

    private SelenideElement referencesList() {
        return $(LIST_OF_REFERENCES_LOAD);
    }

    private SelenideElement connectionsList() {
        return $(CONNECTIONS_LIST);
    }

    public List<SelenideElement> isDataLoaded() {
        return $$(IS_COLUMNS_LOADED);
    }

    public List<SelenideElement> getListOfReferences() {
        return $$(LIST_OF_REFERENCES_FORM_SOURCE);
    }

    public SelenideElement getEnrichmentNameInput() {
        return $(ENRICHMENT_NAME_INPUT);
    }

    public SelenideElement getSaveNewEnrichmentButton() {
        return $(SAVE_NEW_ENRICHMENT_BUTTON);
    }

    private SelenideElement getCancelCreateEnrichmnetButton() {
        return $(CANCEL_CREATE_NEW_ENRICHMENT);
    }

    private SelenideElement getPreviewButton() {
        return $(PREVIEW_BUTTON);
    }

    private SelenideElement getExpandNewEnrichmentMenu() {
        return $(EXPAND_NEW_ENRICHMENT_MENU);
    }

    private SelenideElement getCollapseNewEnrichmentMenu() {
        return $(COLLAPSE_NEW_ENRICHMENT_MENU);
    }

    private SelenideElement getNumberOfPreviewRecordsInput() {
        return $(NUMBER_OF_RECORDS_INPUT);
    }

    public List<SelenideElement> getMenuSubTab() {
        return $$(MENU_SUB_TAB_BUTTONS);
    }

    public List<SelenideElement> getSelectColumnsOperation() {
        return $$(SELECT_COLUMN_OPERATION);
    }
}
