package com.logi.qa.test.ui;


import com.logi.qa.test.ui.Dialogs.CreateNewConnectionDialog;
import com.logi.qa.test.ui.Dialogs.CreateNewUserDialog;
import com.logi.qa.test.ui.Pages.*;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;


/**
 * @author mkhimich
 */
public class TestDefaultFlow extends AbstractTest {

    @Test(groups = {"ui","login"})
    public void testLoginPage() {
        LoginPage loginPage = new LoginPage();
        loginPage.goToLoginPage();
        StartPage startPage = loginPage.login(LogiUsers.ADMIN);
        startPage.getLogiContent().shouldBe(visible);
    }

    @Test(groups = {"ui","connection"})
    public void testCreateConnection() {
        String sourceName = "test" + getRandomString();
        LoginPage loginPage = new LoginPage();
        loginPage.goToLoginPage();
        StartPage startPage = loginPage.login(LogiUsers.ADMIN);
        DataAuthoringPage dataAuthoringPage = startPage.goToDataAuthoring();
        ConnectionsPage connectionsPage = dataAuthoringPage.createNewConnection();
        connectionsPage.createNewJDBCConnection(sourceName);
        //cleanUp
        connectionsPage.deleteConnector(sourceName);
    }

    @Test(groups = {"ui","flow"})
    public void testFullFlow() {
        String sourceName = "test" + getRandomString();
        String tableName = "Orders";
        LoginPage loginPage = new LoginPage();
        loginPage.goToLoginPage();
        StartPage startPage = loginPage.login(LogiUsers.ADMIN);
        DataAuthoringPage dataAuthoringPage = startPage.goToDataAuthoring();
        ConnectionsPage connectionsPage = dataAuthoringPage.createNewConnection();
        CreateNewConnectionDialog dialog = connectionsPage.openCreateNewConnectionDialog();
        dialog.populateNewConnection(sourceName);
        dialog.populateDatabaseNameWithGetList();
        dialog.testConnection();
        dialog.saveConnection();
        connectionsPage.goToDataAuthoring();
        ReferencesPage referencesPage = dataAuthoringPage.goToReferences();
        referencesPage.createNewReference(sourceName, tableName);
        referencesPage.getCreatedReference(tableName).shouldBe(visible);
    }

    @Test(groups = {"ui","platformSettings"})
    public void platformSettings(){
        LoginPage loginPage = new LoginPage();
        loginPage.goToLoginPage();
        StartPage startPage = loginPage.login(LogiUsers.ADMIN);
        PlatformSettingsPage platformSettingsPage = startPage.goToPlatformSettings();
        platformSettingsPage.selectAuthoringUsers();
        platformSettingsPage.getCreateNewUserButton();
    }

    @Test(groups = {"ui","platform settings"})
    public void createNewUserTest(){
        LoginPage loginPage = new LoginPage();
        loginPage.goToLoginPage();
        StartPage startPage = loginPage.login(LogiUsers.ADMIN);
        PlatformSettingsPage platformSettingsPage = startPage.goToPlatformSettings();
        CreateNewUserDialog dialog = platformSettingsPage.getCreateNewUserButton();
        dialog.createNewUser();
    }
}
