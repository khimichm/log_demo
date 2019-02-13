package com.logi.qa.test.ui.Dialogs;

import com.codeborne.selenide.SelenideElement;
import com.logi.qa.test.ui.Pages.AbstractPage;
import com.logi.qa.test.ui.Pages.PlatformSettingsPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CreateNewUserDialog extends AbstractPage {

    private static final String NEW_USER_USER_NAME = "userName";
    private static final String NEW_USER_SAVE = ".modal-footer .btn-primary";
    private static final String NEW_USER_FIRST_NAME = "firstName";
    private static final String NEW_USER_LAST_NAME = "lastName";
    private static final String NEW_USER_EMAIL = "email";
    private static final String NEW_USER_PASSWORD = ".modal-body input:nth-child(10)";
    private final String userName = context.getProperty("newuser.username");
    private final String firstName = context.getProperty("newuser.firstname");
    private final String lastValue = context.getProperty("newuser.lastname");
    private final String email = context.getProperty("newuser.email");
    private final String password = context.getProperty("newuser.password");


    public PlatformSettingsPage createNewUser() {
        getUserNameInput().setValue(userName);
        getUserFirstNameInput().setValue(firstName);
        getUserLastNameInput().setValue(lastValue);
        getUserEmailInput().setValue(email);
        getUserPasswordInput().setValue(password);
        getSaveButton().click();
        return new PlatformSettingsPage();
    }

    private SelenideElement getSaveButton() {
        return $(NEW_USER_SAVE);
    }

    private SelenideElement getUserNameInput() {
        return $(By.name(NEW_USER_USER_NAME));
    }

    private SelenideElement getUserFirstNameInput() {
        return $(By.name(NEW_USER_FIRST_NAME));
    }

    private SelenideElement getUserLastNameInput() {
        return $(By.name(NEW_USER_LAST_NAME));
    }

    private SelenideElement getUserEmailInput() {
        return $(By.name(NEW_USER_EMAIL));
    }

    private SelenideElement getUserPasswordInput() {
        return $(NEW_USER_PASSWORD);
    }
}
