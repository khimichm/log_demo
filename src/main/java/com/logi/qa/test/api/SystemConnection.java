package com.logi.qa.test.api;

import com.logi.qa.test.ui.Util.PropertiesContext;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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

    public String getCustomReferenceURI(String name){
        return getReferenceURI() + "/" + name;
    }

    public String getEnrichmentURI() {
        return context.getProperty("api.system.enrichment");
    }

    public String getCustomEnrichmentURI(String name){
        return getEnrichmentURI() + "/" + name;
    }

    public String getReferenceJson(String name) throws IOException{
        return getJsonFromFile(name);
    }

    private String getJsonFromFile(String name) throws IOException {
        File json = new File(Paths.get(context.USER_DIR, context.getProperty("json.location.path"), name).toString());
        return FileUtils.readFileToString(json, "UTF-8");
    }
}
