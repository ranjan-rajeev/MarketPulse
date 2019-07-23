package com.horizonlabs.marketpulse.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.horizonlabs.marketpulse.R;
import com.horizonlabs.marketpulse.utils.Utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import okhttp3.internal.Util;

/**
 * Created by Rajeev Ranjan -  ABPB on 22-07-2019.
 */
public class ModifyScanDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    LinkedHashMap<String, JSONObject> variables;
    List<JSONObject> values;

    private static final int ROW_SPINNER = 0;
    private static final int ROW_SET_PARAMETERS = 1;

    public ModifyScanDetailsAdapter(Context context, LinkedHashMap<String, JSONObject> variables) {
        this.context = context;
        this.variables = variables;
        values = new ArrayList<>(variables.values());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = null;
        switch (viewType) {
            case ROW_SPINNER:
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_spinner, viewGroup, false);

                return new SpinnerVewiHolder(view);
            case ROW_SET_PARAMETERS:
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_set_parameters, viewGroup, false);
                return new ParameterViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SpinnerVewiHolder) {
            JSONObject jsonObject = values.get(position);
            JSONArray optJSONArray = jsonObject.optJSONArray("values");
            Double valuesArray[] = new Double[optJSONArray.length()];
            String strings[] = new String[optJSONArray.length()];
            for (int j = 0; j < optJSONArray.length(); j++) {
                valuesArray[j] = optJSONArray.optDouble(j);
            }
            String s = Utility.getFormattedDouble(valuesArray[0]);
            Collections.sort(Arrays.asList(valuesArray));
            strings = Utility.getFormattedStringArray(valuesArray);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, strings);

            ((SpinnerVewiHolder) holder).spValues.setAdapter(adapter);
            ((SpinnerVewiHolder) holder).spValues.setSelection(Utility.getIndex(strings, s), true);


        } else if (holder instanceof ParameterViewHolder) {
            JSONObject jsonObject = values.get(position);
            ((ParameterViewHolder) holder).tvTitle.setText(jsonObject.optString("study_type"));
            ((ParameterViewHolder) holder).til.setHint(
                    jsonObject.optString("parameter_name") + " ( "
                            + Utility.getFormattedDouble(jsonObject.optDouble("min_value")) + " - "
                            + Utility.getFormattedDouble(jsonObject.optDouble("max_value")) + " )"

            );

            ((ParameterViewHolder) holder).etModify.setText(Utility.getFormattedDouble(jsonObject.optDouble("default_value")));

        }
    }

    @Override
    public int getItemViewType(int position) {
        return getViewType(values.get(position));
    }

    public int getViewType(JSONObject jsonObject) {
        if (jsonObject.optString("type").equals("value")) {
            return ROW_SPINNER;
        } else if (jsonObject.optString("type").equals("indicator")) {
            return ROW_SET_PARAMETERS;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return variables.size();
    }

    public class SpinnerVewiHolder extends RecyclerView.ViewHolder {

        Spinner spValues;

        public SpinnerVewiHolder(@NonNull final View itemView) {
            super(itemView);
            spValues = itemView.findViewById(R.id.spValues);
        }
    }

    public class ParameterViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        EditText etModify;
        TextInputLayout til;

        public ParameterViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            etModify = itemView.findViewById(R.id.etModify);
            til = itemView.findViewById(R.id.til);
        }
    }



}
