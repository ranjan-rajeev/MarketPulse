package com.horizonlabs.marketpulse.data.local.dao;


import com.horizonlabs.marketpulse.data.local.model.ScanEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Created by Rajeev Ranjan -  ABPB on 22-07-2019.
 */
@Dao
public interface ScanDao {
    @Query("SELECT * FROM ScanEntity")
    LiveData<List<ScanEntity>> getAllScanData();

    @Query("SELECT * FROM ScanEntity where  id = :itemId")
    LiveData<ScanEntity> getScanEntityFromId(int itemId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveScan(ScanEntity... scanEntities);

    @Update
    void updateScan(ScanEntity scanEntity);
}
