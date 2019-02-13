package com.logi.qa.test.ui.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreateNewReferencePage extends PageWithPanels {
    private static final String REFERENCE_CONNECTION_DROP_DOWN = "#select-collection";
    private static final String REFERENCE_CREATE_FROM_TABLE = "#table-dataview-radio";
    private static final String REFERENCE_SELECT_TABLE_LIST_INPUT = "#input-search-columns";
    private static final String REFERENCE_SELECT_TABLE_LIST = ".connection-table-list .connection-table-item";
    private static final String REFERENCE_PREVIEW_TABLE= ".dataview-preview-table";
    private static final String REFERENCE_CREATE_NEW_REFERENCE= ".create-new-reference-btn";

    public void selectDB(String dbName) {
        getConnectionDropDown().selectOption(dbName);
    }

    private SelenideElement getConnectionDropDown() {
        return $(REFERENCE_CONNECTION_DROP_DOWN);
    }

    public SelenideElement getCreateFromRadioButton() {
        return $(REFERENCE_CREATE_FROM_TABLE);
    }

    public SelenideElement getSelectTable() {
        return $(REFERENCE_SELECT_TABLE_LIST_INPUT);
    }

    public void selectConnectionByName(String tableName) {
        for (SelenideElement element : getConnectionsList()) {
            if (element.getText().equals(tableName)) {
                element.click();
                element.$(".check-wrap").shouldBe(Condition.visible);
                element.$(".preview-data-btn").click();
                //Wait for table to load for 30 seconds.
                getPreviewTable().waitUntil(appear, 30000);
                break;
            }
            throw new RuntimeException("There were no table with name " + tableName + " in list of tables.");
        }
    }

    private SelenideElement getPreviewTable() {
        return $(REFERENCE_PREVIEW_TABLE);
    }


    private List<SelenideElement> getConnectionsList() {
        return $$(REFERENCE_SELECT_TABLE_LIST);
    }

    public SelenideElement getCreateNewReference() {
        return $(REFERENCE_CREATE_NEW_REFERENCE);
    }
}
