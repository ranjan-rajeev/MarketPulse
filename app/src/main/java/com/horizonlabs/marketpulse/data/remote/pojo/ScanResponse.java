package com.horizonlabs.marketpulse.data.remote.pojo;

import java.util.List;

/**
 * Created by Rajeev Ranjan -  ABPB on 22-07-2019.
 */
public class ScanResponse {

    /**
     * id : 1
     * name : Top gainers
     * tag : Intraday Bullish
     * color : green
     * criteria : [{"type":"plain_text","text":"Sort - %price change in descending order"}]
     */

    private int id;
    private String name;
    private String tag;
    private String color;
    private Object criteria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Object getCriteria() {
        return criteria;
    }

    public void setCriteria(Object criteria) {
        this.criteria = criteria;
    }
}
