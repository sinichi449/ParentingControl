package com.sinichi.parentingcontrol;

import android.app.AlarmManager;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.sinichi.parentingcontrol.rest_api.ApiClient;
import com.sinichi.parentingcontrol.rest_api.ApiInterface;
import com.sinichi.parentingcontrol.rest_api.Items;
import com.sinichi.parentingcontrol.rest_api.Jadwal;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlarmShalat {

    private Context context;
    private Jadwal jadwal = new Jadwal();

    public AlarmShalat(Context context) {
        this.context = context;
    }

    public void getDataFromCloud() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Items> call = apiService.getJadwalSholat("Malang");
        call.enqueue(new Callback<Items>() {
            @Override
            public void onResponse(Call<Items> call, Response<Items> response) {
                List<Jadwal> jadwals = response.body().getItems();
                String subuh = "";
                for (Jadwal data : jadwals) {
                    subuh += data.getSubuh().toString();
                }
                Log.i("Result", subuh);
            }

            @Override
            public void onFailure(Call<Items> call, Throwable t) {

            }
        });
    }

    public void start() {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Date date = new Date();
        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();
        cal_now.setTime(date);
        cal_alarm.setTime(date);
    }
}
