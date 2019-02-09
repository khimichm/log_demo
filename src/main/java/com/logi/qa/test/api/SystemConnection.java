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
}
