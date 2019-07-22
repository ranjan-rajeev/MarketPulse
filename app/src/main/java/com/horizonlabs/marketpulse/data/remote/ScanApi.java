package com.horizonlabs.marketpulse.data.remote;


import com.horizonlabs.marketpulse.data.local.model.ScanEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Rajeev Ranjan -  ABPB on 22-07-2019.
 */
public interface ScanApi {

    @GET("/data")
    Call<List<ScanResponse>> getUsers();
}
