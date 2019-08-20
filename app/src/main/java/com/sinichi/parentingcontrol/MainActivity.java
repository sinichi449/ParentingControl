package com.sinichi.parentingcontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Display;

import com.sinichi.parentingcontrol.model.Model;
import com.sinichi.parentingcontrol.recycleadapter.RvAdapter;

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
    }

    private void initComponents() {
        rv = findViewById(R.id.rv_catatan);
    }

    private void initRecyclerView() {
        Model model = new Model("5", true, false);
        dataList.add(model);
        Model mode1 = new Model("4", false, true);
        dataList.add(mode1);
        rvAdapter = new RvAdapter(dataList);
        rv.setAdapter(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void addModel(List<Model> modelList, String jumlahSholat, boolean isMembantuOrangTua, boolean isSekolah) {
        Model model = new Model(jumlahSholat, isMembantuOrangTua, isSekolah);
        modelList.add(model);

    }


}

