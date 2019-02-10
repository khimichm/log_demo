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
    SystemConnection connection = new SystemConnection();
    private String connectionName = "QaMySQL";
    /*getRandomString();*/

    @Test(groups = {"api", "baseflow"},priority=1)
    public void testAuthenticate() {
        given().contentType("application/json").
                queryParam("action", "authenticate").
                baseUri(context.getProperty("api.url")).basePath(context.getProperty("api.authenticate")).
                body(LogiUsers.ADMIN.getUserMap()).post().
                then().
                assertThat().statusCode(200).
                assertThat().body(containsString("admin"));

    }

    @Test( groups = {"api", "baseflow", "createConnection"},priority=1)
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateConnection(){
        specification.basePath(connection.getURI()).
                body(connection.getConnectionBody(connectionName)).post().
                then().statusCode(201).
                body(containsString("QaMySQL")).
                body(containsString("*******"));
    }

    @Test(groups = {"api", "baseflow", "connectionsGet"},priority=2)
    @Severity(SeverityLevel.NORMAL)
    public void testGetConnection(){
        specification.basePath(connection.getDBURI()).get().
                then().statusCode(200).
                body(containsString("QaMySQL"));
    }

    @Test(groups = {"api", "baseflow", "connectionsGet"},priority=2)
    @Severity(SeverityLevel.NORMAL)
    public void testGetSchemas(){
        specification.basePath(connection.getDBURI()).
                queryParam("action","getSchemas").get().
                then().statusCode(200).
                body(containsString("eyJjb25uZWN0aW9uSWQiOiJRYU15U1FMIn0="));
    }

    @Test(groups = {"api", "baseflow", "connectionsGet"},priority=3)
    @Severity(SeverityLevel.NORMAL)
    public void testGetDatabases(){
        specification.basePath(connection.getDBURI()).
                queryParam("action","getDatabases").get().
                then().statusCode(200).
                body(containsString("KMysqlPredictGo"));
    }

    @Test(groups = {"api", "baseflow", "connectionsGet"},priority=2)
    @Severity(SeverityLevel.NORMAL)
    public void testGetTables(){
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

//    @Test( groups = {"api", "baseflow", "createReference"})
//    @Severity(SeverityLevel.CRITICAL)
//    public void testPostReference(){
//        specification.basePath(connection.getReferenceURI()).
//                body(connection.getReferenceJson(connectionName)).post().
//                then().statusCode(201).
//                body(containsString("MySQL"));
//    }

//    @Test( groups = {"api", "baseflow", "createReference"})
//    @Severity(SeverityLevel.NORMAL)
//    public void testGetReference(){
//        specification.basePath(connection.getReferenceURI()).
//                body(connection.getReferenceJson(connectionName)).get().
//                then().statusCode(200).
//                body(containsString("MySQL"));
//    }

//    @Test( groups = {"api", "baseflow", "deleteConnection"},priority=4)
//    @Severity(SeverityLevel.CRITICAL)
//    public void testDeleteConnection(){
//        specification.basePath(connection.getDBURI()).
//                body(connection.getReferenceJson(connectionName)).delete().
//                then().statusCode(204);
//    }

}
