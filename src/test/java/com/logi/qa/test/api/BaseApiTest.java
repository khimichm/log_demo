package com.logi.qa.test.api;


import com.logi.qa.test.ui.LogiUsers;
import com.logi.qa.test.ui.Util.PropertiesContext;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;

public class BaseApiTest extends AbstractApiTest{
    private String tableId;

    @Test(groups = {"api", "baseflow"})
    public void testAuthenticate() {
        given().contentType("application/json").
                queryParam("action", "authenticate").
                baseUri(context.getProperty("api.url")).basePath(context.getProperty("api.authenticate")).
                body(LogiUsers.ADMIN.getUserMap()).post().
                then().
                assertThat().statusCode(200).
                assertThat().body(containsString("admin"));

    }

    @Test( groups = {"api", "baseflow", "createConnection"})
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateConnection(){
        String connectionName = getRandomString();
        SystemConnection connection = new SystemConnection();
        specification.basePath(connection.getURI()).
                body(connection.getConnectionBody(connectionName)).post().
                then().statusCode(201).
                body(containsString("QaMySQL")).
                body(containsString("*******"));
    }

    @Test( dependsOnMethods = {"testCreateConnection"}, groups = {"api", "baseflow", "connectionsGet"})
    @Severity(SeverityLevel.NORMAL)
    public void testGetConnection(){
        SystemConnection connection = new SystemConnection();
        specification.basePath(connection.getDBURI()).get().
                then().statusCode(200).
                body(containsString("QaMySQL"));
    }

    @Test(dependsOnMethods = {"testCreateConnection"}, groups = {"api", "baseflow", "connectionsGet"})
    @Severity(SeverityLevel.NORMAL)
    public void testGetSchemas(){
        SystemConnection connection = new SystemConnection();
        specification.basePath(connection.getDBURI()).
                queryParam("action","getSchemas").get().
                then().statusCode(200).
                body(containsString("eyJjb25uZWN0aW9uSWQiOiJRYU15U1FMIn0="));
    }

    @Test(dependsOnMethods = {"testCreateConnection"}, groups = {"api", "baseflow", "connectionsGet"})
    @Severity(SeverityLevel.NORMAL)
    public void testGetDatabases(){
        SystemConnection connection = new SystemConnection();
        specification.basePath(connection.getDBURI()).
                queryParam("action","getDatabases").get().
                then().statusCode(200).
                body(containsString("KMysqlPredictGo"));
    }

    @Test( groups = {"api", "baseflow", "connectionsGet"})
    @Severity(SeverityLevel.NORMAL)
    public void testGetTables(){
        SystemConnection connection = new SystemConnection();
        specification.basePath(connection.getDBURI()).
                queryParam("action","getTables").get().
                then().statusCode(200).
                body(containsString("1funny-table"));
    }

//    @Test( groups = {"api", "baseflow", "connectionsGet"})
//    @Severity(SeverityLevel.NORMAL)
//    public void testGetColumnsAndTableId(){
//        SystemConnection connection = new SystemConnection();
//        specification.basePath(connection.getDBURI()).
//                queryParam("action","getColumns").
//                queryParam("tableId", "1funny-table").get().
//                then().statusCode(200).
//                body(containsString("1TestID"));
//    }


}
