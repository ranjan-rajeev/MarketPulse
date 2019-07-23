package com.horizonlabs.marketpulse.viewmodels;

import android.app.Application;

import com.horizonlabs.marketpulse.data.local.model.ScanEntity;
import com.horizonlabs.marketpulse.data.repository.ScanRepository;
import com.horizonlabs.marketpulse.utils.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * Created by Rajeev Ranjan -  ABPB on 22-07-2019.
 */
public class ScanDetailsViewModel extends AndroidViewModel {

    private ScanRepository scanRepository;

    public ScanDetailsViewModel(@NonNull Application application) {
        super(application);
        scanRepository = new ScanRepository(application);
    }

    public void insert(ScanEntity... scanEntities) {
        scanRepository.insert(scanEntities);
    }

    public void update(ScanEntity scanEntity) {
        scanRepository.update(scanEntity);
    }

    public LiveData<ScanEntity> getScanEntityLiveData(int id) {
        return scanRepository.getScanEntity(id);
    }

    public List<String> getCriteria(String jsonString) {
        List<String> criteriaList = new ArrayList<>();

        try {
            JSONArray criteriaArray = new JSONArray(jsonString);
            for (int i = 0; i < criteriaArray.length(); i++) {

                JSONObject criteria = criteriaArray.getJSONObject(i);

                String type = criteria.optString("type");
                String text = criteria.optString("text");
                String study_type, parameter_name, min_value, max_value, default_value;
                int[] values;
                //region  variable parsing

                if (type.equals("variable") && criteria.has("variable")) {

                    List<String> keys = Utility.getListKeys(text);

                    JSONObject variable = criteria.getJSONObject("variable");

                    text = Utility.getReplacedString(text, variable);

                    /*for (String key : keys) {

                        String typeDetail = variable.getString("type"); //indicator or value

                        if (typeDetail.equals("indicator")) { // indicator parsing

                            study_type = variable.optString("study_type");
                            parameter_name = variable.optString("parameter_name");
                            min_value = variable.optString("min_value");
                            max_value = variable.optString("max_value");
                            default_value = variable.optString("default_value");

                        } else if (typeDetail.equals("value")) { //value parsing
                            if (variable.has("values")) {
                                JSONArray valuesArray = variable.getJSONArray("values");
                                values = new int[valuesArray.length()];
                                for (int j = 0; j < valuesArray.length(); j++) {
                                    values[i] = valuesArray.optInt(i);
                                }
                            }
                        }
                    }*/
                }
                //endregion


                criteriaList.add(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return criteriaList;
    }

    public List<JSONObject> getVariable(String jsonString) {
        List<JSONObject> variables = new ArrayList<>();
        try {
            JSONArray criteriaArray = new JSONArray(jsonString);
            for (int i = 0; i < criteriaArray.length(); i++) {
                JSONObject criteria = criteriaArray.getJSONObject(i);
                variables.add(criteria);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return variables;
    }


    public List<LinkedHashMap<String, JSONObject>> getVariables(String jsonString) {

        List<LinkedHashMap<String, JSONObject>> variables = new ArrayList<>();
        try {
            JSONArray criteriaArray = new JSONArray(jsonString);
            for (int i = 0; i < criteriaArray.length(); i++) {

                JSONObject criteria = criteriaArray.getJSONObject(i);
                String type = criteria.optString("type");
                String text = criteria.optString("text");
                LinkedHashMap<String, JSONObject> stringJSONObjectHashMap = new LinkedHashMap<>();

                if (type.equals("variable") && criteria.has("variable")) {

                    List<String> keys = Utility.getListKeys(text);

                    JSONObject variable = criteria.getJSONObject("variable");

                    for (String key : keys) {
                        JSONObject jsonObject = variable.optJSONObject(key);
                        if (jsonObject != null) {
                            stringJSONObjectHashMap.put(key, jsonObject);
                        }
                    }
                }
                variables.add(stringJSONObjectHashMap);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return variables;
    }
}
