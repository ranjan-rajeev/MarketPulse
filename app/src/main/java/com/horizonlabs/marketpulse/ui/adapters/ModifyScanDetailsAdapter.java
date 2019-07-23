package com.horizonlabs.marketpulse.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.horizonlabs.marketpulse.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

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
            for (int j = 0; j < optJSONArray.length(); j++) {
                valuesArray[j] = optJSONArray.optDouble(j);
            }
            ArrayAdapter<Double> adapter = new ArrayAdapter<Double>(context, android.R.layout.simple_spinner_dropdown_item, valuesArray);

            ((SpinnerVewiHolder) holder).spValues.setAdapter(adapter);


        } else if (holder instanceof ParameterViewHolder) {
            JSONObject jsonObject = values.get(position);
            ((ParameterViewHolder) holder).tvTitle.setText(jsonObject.optString("study_type"));
            ((ParameterViewHolder) holder).etModify.setHint(
                    jsonObject.optString("parameter_name") + " "
                            + jsonObject.optString("min_value") + " "
                            + jsonObject.optString("max_value") + " "

            );

            ((ParameterViewHolder) holder).etModify.setText(jsonObject.optString("default_value"));

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

        public ParameterViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            etModify = itemView.findViewById(R.id.etModify);
        }
    }

    public void setScanList(LinkedHashMap<String, JSONObject> variables) {
        this.variables = variables;
        values = new ArrayList<>(variables.values());
        notifyDataSetChanged();
    }

}
