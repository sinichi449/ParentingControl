package com.sinichi.parentingcontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinichi.parentingcontrol.model.Model;
import com.sinichi.parentingcontrol.recycleadapter.RvAdapter;
import com.sinichi.parentingcontrol.rest_api.Jadwal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RvAdapter rvAdapter;
    private List<Model> dataList = new ArrayList<>();
    private ImageView imgAddItem;
    private EditText edtHari, edtTanggal, edtBulan, edtTahun, edtJumlahSholat;
    private CheckBox chkMembantuOrtu, chkSekolah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        initRecyclerView();

        imgAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Do when the add button clicked
                makeDialog(imgAddItem);
            }
        });
    }

    private void initComponents() {
        rv = findViewById(R.id.rv_catatan);
        imgAddItem = findViewById(R.id.img_addItems);
    }

    private void initRecyclerView() {
//        dataList.add(addModel("5", true, true));
//        dataList.add(addModel("3", true, false));
//        dataList.add(addModel("5", false, false));
//        dataList.add(addModel("5", true, false));
        dataList.add(addModel("16", "Jum'at", "Maret", "2018", "5", true, true));
        dataList.add(addModel("15", "Kamis", "Maret", "2018", "4", false, true));
        dataList.add(addModel("14", "Rabu", "Maret", "2018", "5", true, true));
        dataList.add(addModel("13", "Selasa", "Maret", "2018", "4", true, true));
//        rvAdapter = new RvAdapter(dataList);
//        rv.setAdapter(rvAdapter);
//        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private Model addModel(String tanggal, String hari, String bulan, String tahun, String jumlahSholat, boolean isMembantuOrangTua, boolean isSekolah) {
        Model model = new Model(tanggal, hari, bulan, tahun, jumlahSholat, isMembantuOrangTua, isSekolah);
        rvAdapter = new RvAdapter(dataList);
        rv.setAdapter(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, true));
        return model;
    }

    private void makeDialog(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.custom_dialog, viewGroup, false);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.drawable.ic_logo);
        dialog.setTitle("Tambahkan Aktivitas");

        edtHari = dialogView.findViewById(R.id.edt_hari);
        edtTanggal = dialogView.findViewById(R.id.edt_tanggal);
        edtBulan = dialogView.findViewById(R.id.edt_bulan);
        edtTahun = dialogView.findViewById(R.id.edt_tahun);
        edtJumlahSholat = dialogView.findViewById(R.id.edt_jumlahSholat);
        chkMembantuOrtu = dialogView.findViewById(R.id.chkbx_addMembantuOrtu);
        chkSekolah = dialogView.findViewById(R.id.chk_addSekolah);

        CurrentDimension currentDimension = new CurrentDimension();
        edtHari.setText(currentDimension.getDays());
        edtTanggal.setText(currentDimension.getDate());
        edtBulan.setText(currentDimension.getMonth());
        edtTahun.setText(currentDimension.getYear());


        dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String hari = edtHari.getText().toString();
                String tanggal = edtTanggal.getText().toString();
                String bulan = edtBulan.getText().toString();
                String bulanDefined = defineBulan(bulan);
                String tahun = edtTahun.getText().toString();
                String jumlahSholat = edtJumlahSholat.getText().toString();
                boolean membantuOrtu = chkMembantuOrtu.isChecked();
                boolean sekolah = chkSekolah.isChecked();

                dataList.add(addModel(tanggal, hari, bulanDefined, tahun, jumlahSholat, membantuOrtu, sekolah));
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    private String defineBulan(String bulan) {
        switch (bulan) {
            case "1":
                bulan = "Januari";
                break;
            case "2":
                bulan = "Februari";
                break;
            case "3":
                bulan = "Maret";
                break;
            case "4":
                bulan = "April";
                break;
            case "5":
                bulan = "Mei";
                break;
            case "6":
                bulan = "Juni";
                break;
            case "7":
                bulan = "Juli";
                break;
            case "8":
                bulan = "Agustus";
                break;
            case "9":
                bulan = "September";
                break;
            case "10":
                bulan = "Oktober";
                break;
            case "11":
                bulan = "November";
                break;
            case "12":
                bulan = "Desember";
                break;
        }
        return bulan;
    }


}

