package com.logi.qa.test;

import com.logi.qa.test.Util.PropertiesContext;

/**
 * @author mkhimich
 */
public enum LogiUsers {
    //    private static final PropertiesContext context = PropertiesContext.getInstance();
    ADMIN(
        PropertiesContext.getInstance().getProperty("admin.user.name"),
        PropertiesContext.getInstance().getProperty("admin.user.password")
    );
    private String userName;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    LogiUsers(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
