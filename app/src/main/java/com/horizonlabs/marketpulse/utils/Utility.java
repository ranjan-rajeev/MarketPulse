package com.horizonlabs.marketpulse.utils;

import com.google.gson.Gson;
import com.horizonlabs.marketpulse.data.local.model.ScanEntity;
import com.horizonlabs.marketpulse.data.remote.ScanResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajeev Ranjan -  ABPB on 22-07-2019.
 */
public class Utility {


    public static List<ScanEntity> getScanEntity(List<ScanResponse> scanResponseList) {
        Gson gson = new Gson();

        List<ScanEntity> scanEntities = new ArrayList<>();
        for (ScanResponse scanResponse : scanResponseList) {
            ScanEntity scanEntity = new ScanEntity();
            scanEntity.setId(scanResponse.getId());
            scanEntity.setColor(scanResponse.getColor());
            scanEntity.setName(scanResponse.getName());
            scanEntity.setTag(scanResponse.getTag());
            scanEntity.setCriteria(gson.toJson(scanResponse.getCriteria()));
            scanEntities.add(scanEntity);
        }
        return scanEntities;
    }
}
