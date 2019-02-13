package com.logi.qa.test.api;

import com.logi.qa.test.ui.LogiUsers;
import com.logi.qa.test.ui.Util.PropertiesContext;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public abstract class AbstractApiTest {
    public RequestSpecification specification;
    public PropertiesContext context = PropertiesContext.getInstance();
    public String authToken;

    @BeforeClass
    public void getToken() {
        specification = given().filter(new AllureRestAssured()).log().all().contentType("application/json").baseUri(context.getProperty("api.url"));
        authToken = getAuthToken();
    }

    @BeforeMethod
    public void setSpecification() {
        specification = given().filter(new AllureRestAssured()).log().all().contentType("application/json").baseUri(context.getProperty("api.url"));
        specification = specification.header("X-Logi-Auth", authToken);
        specification = specification.cookie("LogiAuth", authToken);
    }

    public String getRandomString() {
        return RandomStringUtils.random(5, true, false);
    }

    private String getAuthToken() {
        return given().contentType("application/json").
                queryParam("action", "authenticate").
                baseUri(context.getProperty("api.url")).basePath(context.getProperty("api.authenticate")).
                body(LogiUsers.ADMIN.getUserMap()).post().getHeaders().get("X-Logi-Auth").getValue();
    }

}
