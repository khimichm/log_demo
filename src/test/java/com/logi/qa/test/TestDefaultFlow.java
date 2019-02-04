package com.logi.qa.test;


import com.logi.qa.test.Pages.ConnectionsPage;
import com.logi.qa.test.Pages.DataAuthoringPage;
import com.logi.qa.test.Pages.LoginPage;
import com.logi.qa.test.Pages.StartPage;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


/**
 * @author mkhimich
 */
public class TestDefaultFlow extends AbstractTest {

    @Test
    public void testLoginPage() {
        LoginPage loginPage = new LoginPage();
        loginPage.goToLoginPage();
        StartPage startPage = loginPage.login(LogiUsers.ADMIN);
        assertTrue("Start page was not displayed", startPage.isPageLoaded());
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
