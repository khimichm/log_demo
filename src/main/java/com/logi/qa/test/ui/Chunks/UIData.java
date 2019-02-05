package com.logi.qa.test.ui.Chunks;

import com.codeborne.selenide.SelenideDriver;
import com.logi.qa.test.ui.Util.PropertiesContext;

import java.util.Set;

/**
 * UIData interface is the base interface for all page objects of automation framework. It implements Composite design
 * pattern, that is, it has a single UIData parent and a number of UIData children.
 * @author mkhimich
 */
public interface UIData {
    SelenideDriver getDriver();

    PropertiesContext getContext();

    Set<UIData> getChildren();

    UIData getParent();

    void addChild(UIData child);

    String getSelector();

    String getAbsoluteSelector();
}