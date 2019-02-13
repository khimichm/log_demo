package com.logi.qa.test.ui.Dialogs;

import com.codeborne.selenide.SelenideElement;
import com.logi.qa.test.ui.Pages.AbstractPage;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * @author mkhimich
 */
public class CreateNewConnectionDialog extends AbstractPage {
    public static final String CONNECTIONDIALOG_ADDSOURCE_LIST = "#create-connection-form .form-group.row";
    private static final String CONNECTIONDIALOG_SAVE = ".modal-dialog .btn.btn-primary:nth-child(3)";
    private static final String CLOSE_DIALOG_BUTTON = ".modal-content .close";
    private static final String CONNECTIONDIALOG_SUCCESS = ".alert-success";
    private static final String CONNECTIONDIALOG_GET_LIST = ".btn-sm";
    private static final String CONNECTIONDIALOG_DATABASE_NAME_AFTER_LIST = ".database-select input";
    private static final String CONNECTIONDIALOG_TEST_SOURCE = ".modal-footer .btn.btn-primary";
    private static final String EXPAND_ADVANCED_OPTIONS = ".logi-icon-expand-o";
    private static final String COLLAPSE_ADVANCED_OPTIONS = ".logi-icon-expand-o.expanded";


    private final String dataProvider = "Microsoft SQL Server";
    private final String serverName = context.getProperty("jdbc.server.name");
    private final String userName = context.getProperty("jdbc.user.name");
    private final String password = context.getProperty("jdbc.user.password");
    private final String dbName = context.getProperty("jdbc.db.name");
    private final String portNumber = context.getProperty("jdbc.port.number");

    public void createNewJDBCConnection(String sourceName) {
        populateNewConnection(sourceName);
        saveConnection();
    }

    public void populateNewConnection(String sourceName) {
        getAddSourceProperties().get(0).$("select").selectOption(dataProvider);
        getAddSourceProperties().get(1).$("input").setValue(sourceName);
        getAddSourceProperties().get(2).$("input").setValue(serverName);
        getAddSourceProperties().get(3).$("input").setValue(userName);
        getAddSourceProperties().get(4).$("input").setValue(password);
        getAddSourceProperties().get(5).$("input").setValue(dbName);
    }

    public void saveConnection() {
        getSaveButton().click();
        getSuccessElement().shouldBe(visible);
        //getCloseDialogButton().click();
    }

    private SelenideElement getSuccessElement() {
        return $(CONNECTIONDIALOG_SUCCESS);
    }

    private SelenideElement getCloseDialogButton() {
        return $(CLOSE_DIALOG_BUTTON);
    }

    private SelenideElement getSaveButton() {
        return $(CONNECTIONDIALOG_SAVE);
    }

    public List<SelenideElement> getAddSourceProperties() {
        return $$(CONNECTIONDIALOG_ADDSOURCE_LIST);
    }

    public void populateDatabaseNameWithGetList() {
        getListButton().click();
        getDatabaseNameInput().setValue(dbName);
    }

    private SelenideElement getDatabaseNameInput() {
        return $(CONNECTIONDIALOG_DATABASE_NAME_AFTER_LIST);
    }

    private SelenideElement getListButton() {
        return $(CONNECTIONDIALOG_GET_LIST);
    }

    public void testConnection() {
        getTestConnectionButton().click();
        getSuccessElement().shouldBe(visible);
    }

    private SelenideElement getTestConnectionButton() {
        return $(CONNECTIONDIALOG_TEST_SOURCE);
    }

    public SelenideElement expandAdvancedOptions() {
        return $(EXPAND_ADVANCED_OPTIONS);
    }

    public SelenideElement collapceAdvancedOptions() {
        return $(COLLAPSE_ADVANCED_OPTIONS);
    }

}
