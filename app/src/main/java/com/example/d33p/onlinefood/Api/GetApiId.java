package com.example.d33p.onlinefood.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetApiId {
    String URL="http://demo2552687.mockable.io";
    @GET("grocery-item/{id}")
    Call<List<Retro>> getItemsId(@Path("id") String id);
}
