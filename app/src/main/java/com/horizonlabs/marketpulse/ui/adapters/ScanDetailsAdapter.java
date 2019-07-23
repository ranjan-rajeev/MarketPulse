package com.horizonlabs.marketpulse.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.horizonlabs.marketpulse.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Rajeev Ranjan -  ABPB on 22-07-2019.
 */
public class ScanDetailsAdapter extends RecyclerView.Adapter<ScanDetailsAdapter.UserHolder> {

    Context context;
    List<String> scanEntities = new ArrayList<>();
    List<LinkedHashMap<String, JSONObject>> variables;

    public ScanDetailsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_scan_details, viewGroup, false);
        return new UserHolder(itView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int i) {
        String scanEntity = scanEntities.get(i);
        holder.tvName.setText(scanEntity);
        holder.rvModify.setLayoutManager(new LinearLayoutManager(context));
        holder.rvModify.setHasFixedSize(true);
        holder.rvModify.setAdapter(new ModifyScanDetailsAdapter(context, variables.get(i)));


    }

    @Override
    public int getItemCount() {
        return scanEntities.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        RecyclerView rvModify;

        public UserHolder(@NonNull final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            rvModify = itemView.findViewById(R.id.rvModify);
        }
    }

    public void setScanList(List<String> scanEntities, List<LinkedHashMap<String, JSONObject>> variables) {
        this.scanEntities = scanEntities;
        this.variables = variables;
        notifyDataSetChanged();
    }
}
