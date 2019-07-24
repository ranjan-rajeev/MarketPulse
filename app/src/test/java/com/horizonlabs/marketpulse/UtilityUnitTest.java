package com.horizonlabs.marketpulse;

import com.horizonlabs.marketpulse.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilityUnitTest {
    @Test
    public void testFormattngDouble() {
        String actual = Utility.getFormattedDouble(1.345);
        String expected = "1.345";
        assertEquals("Formatting double failed ", expected, actual);
    }

    @Test
    public void testGettingIndex() {
        String[] s = {"-10", "-2", "5"};
        int actual = Utility.getIndex(s, "-2");
        int expected = 1;
        assertEquals("Finding index failed ", expected, actual);
    }

    @Test
    public void testDoubleArrayToStringArray() {
        Double[] d = {1.5, 6.8, 9.0};
        String[] actual = Utility.getFormattedStringArray(d);
        String[] expected = {"1.5", "6.8", "9"};
        assertEquals("Finding index failed ", expected, actual);
    }

    @Test
    public void testStringReplace() throws JSONException {
        String s = "Today’s open < yesterday’s low by $1 %";
        JSONObject jsonObject = null;
        try {

            jsonObject = new JSONObject("{\"$1\":{\"type\":\"value\",\"values\":[-3,-1,-2,-5,-10]}}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String actual = Utility.getReplacedString(s, jsonObject);
        String expected = "Today’s open < yesterday’s low by -3 %";
        assertEquals("Finding index failed ", expected, actual);
    }
}
