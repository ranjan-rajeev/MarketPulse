package com.horizonlabs.marketpulse.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.horizonlabs.marketpulse.data.local.MarketPulseDatabase;
import com.horizonlabs.marketpulse.data.local.dao.ScanDao;
import com.horizonlabs.marketpulse.data.local.model.ScanEntity;
import com.horizonlabs.marketpulse.data.remote.RetrofitClent;
import com.horizonlabs.marketpulse.data.remote.ScanApi;
import com.horizonlabs.marketpulse.data.remote.pojo.ScanResponse;
import com.horizonlabs.marketpulse.utils.Logger;
import com.horizonlabs.marketpulse.utils.Utility;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajeev Ranjan -  ABPB on 22-07-2019.
 */
public class ScanRepository {

    private ScanApi scanApi;
    private ScanDao scanDao;


    private LiveData<List<ScanEntity>> allScan;

    public ScanRepository(Application application) {
        scanDao = MarketPulseDatabase.getInstance(application).userDao();
        scanApi = RetrofitClent.getInstance().create(ScanApi.class);
        allScan = scanDao.getAllScanData();
    }

    public void insert(ScanEntity... userEntities) {
        new InsertUserAsyncTask(scanDao).execute(userEntities);
    }

    public void update(ScanEntity userEntity) {
        new UpdateUserAsyncTask(scanDao).execute(userEntity);
    }

    public LiveData<List<ScanEntity>> getAllScan() {

        getAllScanFromServer();
        return allScan;
    }

    public LiveData<ScanEntity> getScanEntity(int id) {
        return scanDao.getScanEntityFromId(id);
    }

    private static class InsertUserAsyncTask extends AsyncTask<ScanEntity, Void, Void> {

        private ScanDao scanDao;

        private InsertUserAsyncTask(ScanDao userDao) {
            this.scanDao = userDao;
        }

        @Override
        protected Void doInBackground(ScanEntity... scanEntities) {
            scanDao.saveScan(scanEntities);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<ScanEntity, Void, Void> {

        private ScanDao scanDao;

        private UpdateUserAsyncTask(ScanDao userDao) {
            this.scanDao = userDao;
        }

        @Override
        protected Void doInBackground(ScanEntity... scanEntities) {
            scanDao.updateScan(scanEntities[0]);
            return null;
        }
    }

    public LiveData<List<ScanEntity>> getAllScanFromServer() {

        scanApi.getUsers().enqueue(new Callback<List<ScanResponse>>() {
            @Override
            public void onResponse(Call<List<ScanResponse>> call, Response<List<ScanResponse>> response) {
                // if (response.isSuccessful() && allScan.getValue().size() == 0) {
                if (response.isSuccessful()) {
                    Logger.d("list fetch successful");
                    insert(Utility.getScanEntity(response.body()).toArray(new ScanEntity[response.body().size()]));
                }
            }

            @Override
            public void onFailure(Call<List<ScanResponse>> call, Throwable t) {
                // TODO: 20-07-2019 HAndle failure cases
                Logger.d("" + t.getMessage());
            }
        });

        return allScan;
    }
}
