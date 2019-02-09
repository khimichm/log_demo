package com.logi.qa.test.api;

import com.logi.qa.test.ui.LogiUsers;
import com.logi.qa.test.ui.Util.PropertiesContext;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class AbstractApiTest {
    public RequestSpecification specification;
    public PropertiesContext context = PropertiesContext.getInstance();

    @BeforeClass
    public void tearUp(){
        specification = given().filter(new AllureRestAssured()).log().all().contentType("application/json").baseUri(context.getProperty("api.url"));
        String authToken = getAuthToken();
        specification = specification.header("X-Logi-Auth", authToken);
        specification = specification.cookie("LogiAuth", authToken);
    }

    public RequestSpecification getSpecification(){
        return specification;
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
