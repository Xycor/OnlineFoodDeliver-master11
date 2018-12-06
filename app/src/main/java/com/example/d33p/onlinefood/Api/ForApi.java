package com.example.d33p.onlinefood.Api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ForApi {
    String URL="http://demo2552687.mockable.io/";
    @GET("grocery-item")
    Call<List<Retro>> getItems();
    //Observable<List<Retro>> getItems();
}