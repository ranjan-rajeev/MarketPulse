package com.horizonlabs.marketpulse.viewmodels;

import android.app.Application;

import com.horizonlabs.marketpulse.data.local.model.ScanEntity;
import com.horizonlabs.marketpulse.data.repository.ScanRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * Created by Rajeev Ranjan -  ABPB on 22-07-2019.
 */
public class MainViewModel extends AndroidViewModel {

    private ScanRepository scanRepository;

    private LiveData<List<ScanEntity>> allScan;

    public MainViewModel(@NonNull Application application) {
        super(application);
        scanRepository = new ScanRepository(application);
        allScan = scanRepository.getAllScan();
    }


    public void insert(ScanEntity... scanEntities) {
        scanRepository.insert(scanEntities);
    }

    public void update(ScanEntity scanEntity) {
        /*if (userEntity.getIsBookmarked() == 0) {
            userEntity.setIsBookmarked(1);
        } else {
            userEntity.setIsBookmarked(0);
        }*/
        scanRepository.update(scanEntity);
    }

    public LiveData<List<ScanEntity>> getAllScan() {
        return allScan;
    }


}
