package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APInterface {
    @GET("/v2/top-headlines/")
    Call<String> getNews(@Query("source") String sourceValue, @Query("apiKey") String apiKey);
}
