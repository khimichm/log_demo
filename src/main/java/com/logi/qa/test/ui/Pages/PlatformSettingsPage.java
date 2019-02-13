package com.logi.qa.test.ui.Pages;

import com.codeborne.selenide.SelenideElement;
import com.logi.qa.test.ui.Dialogs.CreateNewUserDialog;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PlatformSettingsPage extends PageWithPanels {

    private static final String CREATE_NEW_USER_BUTTON = "CREATE A NEW USER";
    private static final String USER_TYPE_RADIO_BUTTON = "usersType";

    public void selectAuthoringUsers() {
        userTypeRadioButton().selectRadio("authoring");
    }

    public void selectTAUsers() {
        userTypeRadioButton().selectRadio("trustedAccess");
    }

    public CreateNewUserDialog getCreateNewUserButton() {
        createNewUserButton().click();
        return new CreateNewUserDialog();
    }

    private SelenideElement createNewUserButton() {
        return $(byText(CREATE_NEW_USER_BUTTON));
    }

    public SelenideElement userTypeRadioButton() {
        return $(By.name(USER_TYPE_RADIO_BUTTON));
    }
}
