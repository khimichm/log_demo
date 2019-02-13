package com.logi.qa.test.api;


import com.logi.qa.test.ui.LogiUsers;
import com.logi.qa.test.ui.Util.PropertiesContext;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;

public class BaseApiTest extends AbstractApiTest {
    SystemConnection connection = new SystemConnection();
    private String connectionName = "QaMySQL";
    private String referenceName = "QaMySQL_reference_dvd";
    private String enrichmentName = "QaMySQL_enrichment_dvd";
    private String tableId = "eyJjb25uZWN0aW9uSWQiOiJRYU15U1FMIiwiZGF0YWJhc2VJZCI6InFhbm9ydGh3aW5kX2NzIiwidGFibGVJZCI6IjFmdW5ueS10YWJsZSJ9";
    /*getRandomString();*/

    @Test(groups = {"api", "baseflow"}, priority = 1)
    public void testAuthenticate() {
        given().contentType("application/json").
                queryParam("action", "authenticate").
                baseUri(context.getProperty("api.url")).basePath(context.getProperty("api.authenticate")).
                body(LogiUsers.ADMIN.getUserMap()).post().
                then().
                assertThat().statusCode(200).
                assertThat().body(containsString("admin"));

    }

    @Test(groups = {"api", "baseflow", "createConnection"}, priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateConnection() {
        specification.basePath(connection.getURI()).
                body(connection.getConnectionBody(connectionName)).post().
                then().statusCode(201).
                body(containsString("QaMySQL")).
                body(containsString("*******"));
    }

    @Test(groups = {"api", "baseflow", "connectionsGet"}, priority = 2)
    @Severity(SeverityLevel.NORMAL)
    public void testGetConnection() {
        specification.basePath(connection.getDBURI()).get().
                then().statusCode(200).
                body(containsString("QaMySQL"));
    }

    @Test(groups = {"api", "baseflow", "connectionsGet"}, priority = 2)
    @Severity(SeverityLevel.NORMAL)
    public void testGetSchemas() {
        specification.basePath(connection.getDBURI()).
                queryParam("action", "getSchemas").get().
                then().statusCode(200).
                body(containsString("eyJjb25uZWN0aW9uSWQiOiJRYU15U1FMIn0="));
    }

    @Test(groups = {"api", "baseflow", "connectionsGet"}, priority = 2)
    @Severity(SeverityLevel.NORMAL)
    public void testGetDatabases() {
        specification.basePath(connection.getDBURI()).
                queryParam("action", "getDatabases").get().
                then().statusCode(200).
                body(containsString("KMysqlPredictGo"));
    }

    @Test(groups = {"api", "baseflow", "connectionsGet"}, priority = 2)
    @Severity(SeverityLevel.NORMAL)
    public void testGetTables() {
        specification.basePath(connection.getDBURI()).
                queryParam("action", "getTables").get().
                then().statusCode(200).
                body(containsString("1funny-table"));
    }

    @Test(groups = {"api", "baseflow", "connectionsGet"}, priority = 3)
    @Severity(SeverityLevel.NORMAL)
    public void testGetColumnsAndTableId() {
        SystemConnection connection = new SystemConnection();
        specification.basePath(connection.getDBURI()).
                queryParam("action", "getColumns").
                queryParam("tableId", tableId).get().
                then().statusCode(200).
                body(containsString("1TestID"));
    }

    @Test(groups = {"api", "baseflow", "createReference"}, priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    public void testPostReference() throws IOException {
        specification.basePath(connection.getReferenceURI()).
                body(connection.getReferenceJson("reference.json")).post().
                then().statusCode(201).
                body(containsString("MySQL"));
    }

    @Test(groups = {"api", "baseflow", "createReference"}, priority = 4)
    @Severity(SeverityLevel.NORMAL)
    public void testGetReference() {
        specification.basePath(connection.getCustomReferenceURI(referenceName)).
                queryParam("action", "query").get().
                then().statusCode(200).
                body(containsString("OrderID"));
    }

    @Test(groups = {"api", "baseflow", "createReference"}, priority = 4)
    @Severity(SeverityLevel.NORMAL)
    public void testPostEnrichment() throws IOException {
        specification.basePath(connection.getEnrichmentURI()).
                body(connection.getReferenceJson("enrichment.json")).post().
                then().statusCode(201);
    }

    @Test(groups = {"api", "baseflow", "createReference"}, priority = 5)
    @Severity(SeverityLevel.NORMAL)
    public void testGetEnrichment() {
        specification.basePath(connection.getCustomEnrichmentURI(enrichmentName)).
                queryParam("action", "query").get().
                then().statusCode(200).
                body(containsString("OrderID"));
    }

    @Test(groups = {"api", "baseflow", "createReference"}, priority = 6)
    @Severity(SeverityLevel.CRITICAL)
    public void testPutReference() throws IOException {
        specification.basePath(connection.getCustomReferenceURI(referenceName)).
                body(connection.getReferenceJson("reference_put.json")).put().
                then().statusCode(204);
    }

    @Test(groups = {"api", "baseflow", "createReference"}, priority = 7)
    @Severity(SeverityLevel.NORMAL)
    public void testGetUpdatedReference() {
        specification.basePath(connection.getCustomReferenceURI(referenceName)).
                queryParam("action", "query").get().
                then().statusCode(200).
                body(containsString("EmployeeID"));
    }


    @Test(groups = {"api", "baseflow", "createReference"}, priority = 8)
    @Severity(SeverityLevel.NORMAL)
    public void testPutEnrichment() throws IOException {
        specification.basePath(connection.getCustomEnrichmentURI(enrichmentName)).
                body(connection.getReferenceJson("enrichment_put.json")).put().
                then().statusCode(204);
    }

    @Test(groups = {"api", "baseflow", "createReference"}, priority = 9)
    @Severity(SeverityLevel.NORMAL)
    public void testGetUpdatedEnrichment() {
        specification.basePath(connection.getCustomEnrichmentURI(enrichmentName)).
                queryParam("action", "query").get().
                then().statusCode(200).
                body(containsString("EmployeeID"));
    }

    @Test(groups = {"api", "baseflow", "deleteConnection"}, priority = 10)
    @Severity(SeverityLevel.CRITICAL)
    public void testDeleteEnrichment() {
        specification.basePath(connection.getCustomEnrichmentURI(enrichmentName)).
                delete().
                then().statusCode(204);
    }

    @Test(groups = {"api", "baseflow", "deleteConnection"}, priority = 11)
    @Severity(SeverityLevel.CRITICAL)
    public void testDeleteReference() {
        specification.basePath(connection.getCustomReferenceURI(referenceName)).
                delete().
                then().statusCode(204);
    }

    @Test(groups = {"api", "baseflow", "deleteConnection"}, priority = 12)
    @Severity(SeverityLevel.CRITICAL)
    public void testDeleteConnection() {
        specification.basePath(connection.getDBURI()).
                delete().
                then().statusCode(204);
    }


}
