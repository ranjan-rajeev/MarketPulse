package com.horizonlabs.marketpulse.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.horizonlabs.marketpulse.R;
import com.horizonlabs.marketpulse.data.local.model.ScanEntity;
import com.horizonlabs.marketpulse.ui.adapters.ScanAdapter;
import com.horizonlabs.marketpulse.ui.base.BaseActivity;
import com.horizonlabs.marketpulse.utils.Constants;
import com.horizonlabs.marketpulse.viewmodels.MainViewModel;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends BaseActivity implements ScanAdapter.ItemClick {

    MainViewModel mainViewModel;
    RecyclerView rvScanHome;
    ScanAdapter scanAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);
        setUpRecyclerView();

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mainViewModel.getAllScan().observe(MainActivity.this, new Observer<List<ScanEntity>>() {
            @Override
            public void onChanged(List<ScanEntity> scanEntities) {
                if (scanEntities != null && scanEntities.size() > 0) {
                    scanAdapter.setScanEntities(scanEntities);
                }
            }
        });

        scanAdapter.setOnItemClickListener(this);

    }

    private void setUpRecyclerView() {
        rvScanHome = findViewById(R.id.rvScanHome);
        rvScanHome.setLayoutManager(new LinearLayoutManager(this));
        rvScanHome.setHasFixedSize(true);
        scanAdapter = new ScanAdapter();
        rvScanHome.setAdapter(scanAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(ScanEntity scanEntity) {
        startActivity(new Intent(MainActivity.this, ScanDetailsActivity.class)
                .putExtra(Constants.SCAN, scanEntity.getId()));
    }
}
