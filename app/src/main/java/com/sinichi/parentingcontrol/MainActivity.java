package com.sinichi.parentingcontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sinichi.parentingcontrol.model.Model;
import com.sinichi.parentingcontrol.recycleadapter.RvAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RvAdapter rvAdapter;
    private List<Model> dataList;

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
        rvAdapter = new RvAdapter(dataList);
        rv.setAdapter(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}

