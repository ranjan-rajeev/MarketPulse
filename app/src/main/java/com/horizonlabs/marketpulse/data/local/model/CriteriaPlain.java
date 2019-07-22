package com.horizonlabs.marketpulse.data.local.model;

public class CriteriaPlain {
    /**
     * type : plain_text
     * text : Sort - %price change in descending order
     */

    private String type;
    private String text;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}