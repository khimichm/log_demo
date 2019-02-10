package com.logi.qa.test.api;

import com.logi.qa.test.ui.Util.PropertiesContext;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class SystemConnection extends BaseApi{

    public String getConnectionBody(String connectionName){
        return "{" + MessageFormat.format(context.getProperty("api.system.connection.data"),connectionName) + "}";
    }

    public String getURI(){
        return context.getProperty("api.system.connection");
    }

    public String getDBURI() {
        return getURI() + "/" + context.getProperty("api.get.specific.connections");
    }

    public String getReferenceURI() {
        return context.getProperty("api.system.references");
    }

    public String getReferenceJson(String name) {
        return "{\n" +
                "    \"name\": \"QaMySQL_reference_dvd\",\n" +
                "    \"id\": \"QaMySQL_reference_dvd\",\n" +
                "    \"starts\": [\n" +
                "        {\n" +
                "            \"name\": \"Orders\",\n" +
                "            \"type\": \"sql\",\n" +
                "            \"connection\": \""+ name + "\",\n" +
                "            \"dbo\": \"qanorthwind_cs\",\n" +
                "            \"query\": \"orders\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"operations\": [],\n" +
                "    \"ends\": [\n" +
                "        {\n" +
                "            \"type\": \"return\",\n" +
                "            \"format\": {\n" +
                "                \"type\": \"JSON\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
}
