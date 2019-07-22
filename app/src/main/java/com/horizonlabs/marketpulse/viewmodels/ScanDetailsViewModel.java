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


}
