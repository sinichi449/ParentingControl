package com.sinichi.parentingcontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.Adapter;
import android.widget.TextView;

import com.sinichi.parentingcontrol.model.Model;
import com.sinichi.parentingcontrol.recycleadapter.RvAdapter;
import com.sinichi.parentingcontrol.rest_api.Jadwal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RvAdapter rvAdapter;
    private List<Model> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        initRecyclerView();

        AlarmShalat as = new AlarmShalat(this);
        as.getDataFromCloud();
    }

    private void initComponents() {
        rv = findViewById(R.id.rv_catatan);

    }

    private void initRecyclerView() {
//        dataList.add(addModel("5", true, true));
//        dataList.add(addModel("3", true, false));
//        dataList.add(addModel("5", false, false));
//        dataList.add(addModel("5", true, false));
        dataList.add(addModel("16", "Jum'at", "Maret", "2018", "5", true, true));
        dataList.add(addModel("15", "Kamis", "Maret", "2018", "4", false, true));
        rvAdapter = new RvAdapter(dataList);
        rv.setAdapter(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private Model addModel(String tanggal, String hari, String bulan, String tahun, String jumlahSholat, boolean isMembantuOrangTua, boolean isSekolah) {
        Model model = new Model(tanggal, hari, bulan, tahun, jumlahSholat, isMembantuOrangTua, isSekolah);
        return model;
    }

}

