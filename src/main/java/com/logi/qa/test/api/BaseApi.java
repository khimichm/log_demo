package com.logi.qa.test.api;

import com.logi.qa.test.ui.Util.PropertiesContext;

public abstract class BaseApi {

    public PropertiesContext context = PropertiesContext.getInstance();

    public String getBaseURI(){
        return context.getProperty("api.url");
    }

}
