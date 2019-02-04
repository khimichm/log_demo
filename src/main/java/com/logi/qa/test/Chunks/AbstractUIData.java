package com.logi.qa.test.Chunks;

import java.util.HashSet;

/**
 * @author mkhimich
 */
public abstract class AbstractUIData implements UIData {
    protected HashSet<UIData> children = new HashSet<UIData>();

    public HashSet<UIData> getChildren() {
        return children;
    }

    public void addChild(UIData child) {
        children.add(child);
    }

    /**
     * Calls doGetAbsoluteSelector(UIData) method
     *
     * @return absolute selector String
     */
    public String getAbsoluteSelector() {
        return doGetAbsoluteSelector(this);
    }

    /**
     * Get absolute selector for UIData element.
     *
     * @param chunk UIData
     *
     * @return absolute selector String
     */
    private String doGetAbsoluteSelector(UIData chunk) {
        String result = chunk.getSelector();

        UIData parent = chunk.getParent();
        if (parent != null) {
            String prefix = doGetAbsoluteSelector(parent);
            if (prefix != null) {
                result = prefix + " " + result;
            }
        }
        return result;
    }

    /**
     * Pause the execution for animation or any other purposes
     *
     * @param time in milliseconds
     */
    public void suspend(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
