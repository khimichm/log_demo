package com.logi.qa.test.ui.Pages;

import com.logi.qa.test.ui.Chunks.LogiPanel;
import com.logi.qa.test.ui.Dialogs.GeneralYesNoDialog;

import static com.codeborne.selenide.Selenide.$;

/**
 * @author mkhimich
 */
public class PageWithPanels extends GeneralYesNoDialog {


    public LogiPanel getLogiPanel() {
        return new LogiPanel();
    }

    public DataAuthoringPage goToDataAuthoring() {
        getLogiPanel().getDataAuthoring().click();
        return new DataAuthoringPage();
    }

    public PlatformSettingsPage goToPlatformSettings() {
        getLogiPanel().getPlatformSettings().click();
        return new PlatformSettingsPage();
    }

    public GeneralYesNoDialog getYesNoDialog() {
        return new GeneralYesNoDialog();
    }


}
