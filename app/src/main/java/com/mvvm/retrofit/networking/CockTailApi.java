package com.mvvm.retrofit.networking;

import com.mvvm.retrofit.model.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CockTailApi {
    @GET("filter.php")
    Call<ServerResponse> getCockTailList(@Query("a") String newsSource);

}
