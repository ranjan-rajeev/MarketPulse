package com.horizonlabs.marketpulse.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.horizonlabs.marketpulse.R;
import com.horizonlabs.marketpulse.data.local.model.ScanEntity;
import com.horizonlabs.marketpulse.ui.adapters.ScanDetailsAdapter;
import com.horizonlabs.marketpulse.ui.base.BaseActivity;
import com.horizonlabs.marketpulse.utils.Constants;
import com.horizonlabs.marketpulse.viewmodels.ScanDetailsViewModel;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScanDetailsActivity extends BaseActivity {

    int id;
    ScanDetailsViewModel scanDetailsViewModel;
    ScanEntity scanEntity;
    ScanDetailsAdapter scanDetailsAdapter;
    RecyclerView rvScanDetails;
    TextView tvName, tvTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra(Constants.SCAN)) {
            id = getIntent().getIntExtra(Constants.SCAN, -1);
        }
        init_widgets();
        scanDetailsViewModel = ViewModelProviders.of(this).get(ScanDetailsViewModel.class);

        scanDetailsViewModel.getScanEntityLiveData(id).observe(ScanDetailsActivity.this, new Observer<ScanEntity>() {
            @Override
            public void onChanged(ScanEntity scan) {
                if (scan != null) {
                    scanEntity = scan;
                    bindViews();
                    //Toast.makeText(ScanDetailsActivity.this, "" + scanEntity.getCriteriaStringList(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void bindViews() {
        tvName.setText(scanEntity.getName());
        tvTag.setText(scanEntity.getTag());
        if (scanEntity.getColor().equals("green"))
            tvTag.setTextColor(Color.GREEN);
        else
            tvTag.setTextColor(Color.RED);

        scanDetailsAdapter.setScanList(
                scanDetailsViewModel.getCriteriaStringList(scanEntity.getCriteria()),
                scanDetailsViewModel.getVariables(scanEntity.getCriteria()));

    }

    private void init_widgets() {
        tvName = findViewById(R.id.tvName);
        tvTag = findViewById(R.id.tvTag);
        rvScanDetails = findViewById(R.id.rvScanDetails);
        rvScanDetails.setLayoutManager(new LinearLayoutManager(this));
        scanDetailsAdapter = new ScanDetailsAdapter(this);
        rvScanDetails.setAdapter(scanDetailsAdapter);
    }
}
