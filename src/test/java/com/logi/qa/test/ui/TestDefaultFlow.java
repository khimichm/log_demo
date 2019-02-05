package com.logi.qa.test.ui;


import com.logi.qa.test.ui.Pages.ConnectionsPage;
import com.logi.qa.test.ui.Pages.DataAuthoringPage;
import com.logi.qa.test.ui.Pages.LoginPage;
import com.logi.qa.test.ui.Pages.StartPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;


/**
 * @author mkhimich
 */
public class TestDefaultFlow extends AbstractTest {

    @Test
    public void testLoginPage() {
        LoginPage loginPage = new LoginPage();
        loginPage.goToLoginPage();
        StartPage startPage = loginPage.login(LogiUsers.ADMIN);
        startPage.getLogiContent().shouldBe(visible);
    }

    @Test
    public void testGeneralFlow() {
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
}
