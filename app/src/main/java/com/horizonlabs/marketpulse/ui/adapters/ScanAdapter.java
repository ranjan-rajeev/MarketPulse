package com.horizonlabs.marketpulse.ui.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.horizonlabs.marketpulse.R;
import com.horizonlabs.marketpulse.data.local.model.ScanEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Rajeev Ranjan -  ABPB on 22-07-2019.
 */
public class ScanAdapter extends RecyclerView.Adapter<ScanAdapter.UserHolder> {

    private ItemClick itemClick;
    List<ScanEntity> scanEntities = new ArrayList<>();

    public ScanAdapter() {
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_scan_home, viewGroup, false);
        return new UserHolder(itView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int i) {
        ScanEntity scanEntity = scanEntities.get(i);
        holder.tvName.setText(scanEntity.getName());
        holder.tvTag.setText(scanEntity.getTag());
        if (scanEntity.getColor().equals("green"))
            holder.tvTag.setTextColor(Color.GREEN);
        else
            holder.tvTag.setTextColor(Color.RED);
    }

    @Override
    public int getItemCount() {
        return scanEntities.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvTag;

        public UserHolder(@NonNull final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvTag = itemView.findViewById(R.id.tvTag);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemView != null && getAdapterPosition() != RecyclerView.NO_POSITION)
                        itemClick.onItemClick(scanEntities.get(getAdapterPosition()));
                }
            });


        }

        public TextView getTextViewNAme() {
            return tvName;
        }
    }

    public void setScanEntities(List<ScanEntity> scanEntities) {
        this.scanEntities = scanEntities;
        notifyDataSetChanged();
    }

    public interface ItemClick {
        void onItemClick(ScanEntity scanEntity);
    }

    public void setOnItemClickListener(ItemClick listener) {
        this.itemClick = listener;
    }
}
