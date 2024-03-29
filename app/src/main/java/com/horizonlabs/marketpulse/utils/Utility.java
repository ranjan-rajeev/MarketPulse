package com.horizonlabs.marketpulse.utils;

import com.google.gson.Gson;
import com.horizonlabs.marketpulse.data.local.model.ScanEntity;
import com.horizonlabs.marketpulse.data.remote.pojo.ScanResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajeev Ranjan -  ABPB on 22-07-2019.
 */
public class Utility {


    public static List<ScanEntity> getScanEntity(List<ScanResponse> scanResponseList) {
        Gson gson = new Gson();

        List<ScanEntity> scanEntities = new ArrayList<>();
        for (ScanResponse scanResponse : scanResponseList) {
            ScanEntity scanEntity = new ScanEntity();
            scanEntity.setId(scanResponse.getId());
            scanEntity.setColor(scanResponse.getColor());
            scanEntity.setName(scanResponse.getName());
            scanEntity.setTag(scanResponse.getTag());
            scanEntity.setCriteria(gson.toJson(scanResponse.getCriteria()));
            scanEntities.add(scanEntity);
        }
        return scanEntities;
    }

    public static List<String> getListKeys(String s) {
        List<String> keys = new ArrayList<>();
        if (s.isEmpty())
            return keys;

        String[] splited = s.split("\\s+");
        if (splited != null) {
            for (int i = 0; i < splited.length; i++) {
                char c = splited[i].charAt(0);
                if (c == '$') {
                    keys.add(splited[i]);
                }
            }
        }
        return keys;
    }

    public static String getReplacedString(String text, JSONObject variable) throws JSONException {
        if (text.isEmpty() || variable == null)
            return "";

        String finalString = "";
        String[] splited = text.split("\\s+");
        if (splited != null) {

            for (int i = 0; i < splited.length; i++) {
                char c = splited[i].charAt(0);
                if (c == '$') {
                    String temp = "";
                    if (variable.getJSONObject(splited[i]).optString("type").equals("indicator")) {

                        temp = getFormattedDouble(variable.getJSONObject(splited[i])
                                .optDouble("default_value"));

                    } else if (variable.getJSONObject(splited[i]).optString("type").equals("value")) {

                        temp = getFormattedDouble(variable.getJSONObject(splited[i])
                                .getJSONArray("values")
                                .optDouble(0));
                    }
                    finalString = finalString + " (" + temp + ")";


                } else {
                    finalString = finalString + " " + splited[i];
                }
            }

        }


        return finalString;
    }

    public static String getFormattedDouble(double d) {
        DecimalFormat format = new DecimalFormat("0.#");
        return format.format(d);
    }

    public static String[] getFormattedStringArray(Double d[]) {
        String[] s = new String[d.length];
        for (int i = 0; i < d.length; i++) {
            s[i] = getFormattedDouble(d[i]);
        }
        return s;
    }

    public static int getIndex(String[] strings, String s) {
        for (int i = 0; i < strings.length; i++) {
            if (s.equals(strings[i]))
                return i;
        }
        return 0;
    }
}
