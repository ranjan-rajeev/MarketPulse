package com.horizonlabs.marketpulse.data.local;


import android.content.Context;

import com.horizonlabs.marketpulse.data.local.dao.ScanDao;
import com.horizonlabs.marketpulse.data.local.model.ScanEntity;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Created by Rajeev Ranjan -  ABPB on 22-07-2019.
 */
@Database(entities = {ScanEntity.class}, version = 1, exportSchema = false)
public abstract class MarketPulseDatabase extends RoomDatabase {

    private static MarketPulseDatabase marketPulseDatabase;

    public abstract ScanDao userDao();

    public static synchronized MarketPulseDatabase getInstance(Context context) {
        if (marketPulseDatabase == null) {
            marketPulseDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    MarketPulseDatabase.class, "rebel_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return marketPulseDatabase;
    }
}
