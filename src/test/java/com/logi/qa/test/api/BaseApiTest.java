package com.logi.qa.test.api;


import com.logi.qa.test.ui.Util.PropertiesContext;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class BaseApiTest {

    @Test
    @Step("Authenticate")
    public void testFullCycleMysql() {
        PropertiesContext context = PropertiesContext.getInstance();
        Map<String, String> user = new HashMap<>();
        user.put("username", context.getProperty("admin.user.name"));
        user.put("password", context.getProperty("admin.user.password"));

        Response response = given().filter(new AllureRestAssured()).log().all().
                queryParam("action", "authenticate").
                contentType("application/json").
                baseUri("").
                body(user).post();
        System.out.println(response.getHeaders().get("X-Logi-Auth"));

    }
}
