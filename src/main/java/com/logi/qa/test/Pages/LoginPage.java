package com.logi.qa.test.Pages;

import com.codeborne.selenide.SelenideElement;
import com.logi.qa.test.LogiUsers;
import com.logi.qa.test.Util.PropertiesContext;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * @author mkhimich
 */
public class LoginPage extends AbstractPage {
    private final String LOGIN_URI = getContext().getProperty("app.url");
    private static final String LOGIN_USERNAME_FIELD = ".login-form .form-group input[placeholder=\"username\"]";
    private static final String LOGIN_PASSWORD_FIELD = ".login-form .form-group input[placeholder=\"password\"]";
    private static final String LOGIN_BUTTON = ".btn.btn-primary";

    public void goToLoginPage() {
        open(LOGIN_URI);
    }

    public StartPage login(LogiUsers user) {
        getUserNameField().setValue(user.getUserName());
        getPasswordField().setValue(user.getPassword());
        getLoginButton().click();
        return new StartPage();
    }

    private SelenideElement getUserNameField() {
        return $(LOGIN_USERNAME_FIELD);
    }

    private SelenideElement getPasswordField() {
        return $(LOGIN_PASSWORD_FIELD);
    }

    private SelenideElement getLoginButton() {
        return $(LOGIN_BUTTON);
    }

}
