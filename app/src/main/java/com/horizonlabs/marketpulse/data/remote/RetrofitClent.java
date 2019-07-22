package com.horizonlabs.marketpulse.data.remote;


import com.horizonlabs.marketpulse.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rajeev Ranjan -  ABPB on 22-07-2019.
 */
public abstract class RetrofitClent {

    private static volatile Retrofit retrofitClent;

    private RetrofitClent() {
        //Prevent form the reflection api.
        if (retrofitClent != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static Retrofit getInstance() {
        //Double check locking pattern
        if (retrofitClent == null) { //Check for the first time

            synchronized (RetrofitClent.class) {   //Check for the second time.

                //if there is no instance available... create new one
                if (retrofitClent == null) {

                    retrofitClent = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(new OkHttpClient.Builder()
                                    .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                                    .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
                                    .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
                                    .build())
                            .build();
                }
            }
        }

        return retrofitClent;
    }
}
