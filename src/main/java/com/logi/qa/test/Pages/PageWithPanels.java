package com.logi.qa.test.Pages;

import com.logi.qa.test.Chunks.LogiPanel;
import com.logi.qa.test.Dialogs.GeneralYesNoDialog;

import static com.codeborne.selenide.Selenide.$;

/**
 * @author mkhimich
 */
public class PageWithPanels extends AbstractPage {


    public LogiPanel getLogiPanel() {
        return new LogiPanel();
    }

    public DataAuthoringPage goToDataAuthoring() {
        getLogiPanel().getDataAuthoring().click();
        return new DataAuthoringPage();
    }

    public GeneralYesNoDialog getYesNoDialog() {
        return new GeneralYesNoDialog();
    }


}
