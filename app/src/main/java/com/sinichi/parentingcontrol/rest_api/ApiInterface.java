package com.sinichi.parentingcontrol.rest_api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("Malang/{periode}/daily.json")
    Call<Items> getJadwalSholat(@Path("periode") String periode);
}
