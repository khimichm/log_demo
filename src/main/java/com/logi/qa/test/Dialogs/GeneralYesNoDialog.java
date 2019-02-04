package com.logi.qa.test.Dialogs;

import com.codeborne.selenide.SelenideElement;
import com.logi.qa.test.Pages.AbstractPage;

import static com.codeborne.selenide.Selenide.$;

/**
 * @author mkhimich
 */
public class GeneralYesNoDialog extends AbstractPage {
    private static final String CONFIRM_BUTTON = ".modal-content .btn-primary";

    public void confirm() {
        getConfirmButton().click();
    }

    private SelenideElement getConfirmButton() {
        return $(CONFIRM_BUTTON);
    }

}
