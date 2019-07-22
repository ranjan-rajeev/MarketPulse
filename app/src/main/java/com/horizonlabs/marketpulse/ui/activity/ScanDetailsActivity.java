package com.horizonlabs.marketpulse.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.horizonlabs.marketpulse.R;
import com.horizonlabs.marketpulse.data.local.model.ScanEntity;
import com.horizonlabs.marketpulse.ui.base.BaseActivity;
import com.horizonlabs.marketpulse.utils.Constants;
import com.horizonlabs.marketpulse.viewmodels.MainViewModel;
import com.horizonlabs.marketpulse.viewmodels.ScanDetailsViewModel;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class ScanDetailsActivity extends BaseActivity {

    int id;
    ScanDetailsViewModel scanDetailsViewModel;
    ScanEntity scanEntity;

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

        scanDetailsViewModel = ViewModelProviders.of(this).get(ScanDetailsViewModel.class);

        scanDetailsViewModel.getScanEntityLiveData(id).observe(ScanDetailsActivity.this, new Observer<ScanEntity>() {
            @Override
            public void onChanged(ScanEntity scan) {
                if (scan != null) {
                    scanEntity = scan;
                    Toast.makeText(ScanDetailsActivity.this, ""+scanEntity.getCriteria(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
