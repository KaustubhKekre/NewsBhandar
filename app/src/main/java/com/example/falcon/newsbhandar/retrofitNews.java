package com.example.falcon.newsbhandar;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface retrofitNews  {
    @GET("top-headlines")
    Call<News> headlines(@Query("country") String country,@Query("apiKey") String aKey);

    @GET("top-headlines")
    Call<News> others(@Query("country") String country,@Query("apiKey") String aKey,@Query("category") String category);
}
