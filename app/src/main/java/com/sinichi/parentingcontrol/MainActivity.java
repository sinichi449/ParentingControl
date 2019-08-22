package com.sinichi.parentingcontrol;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.sinichi.parentingcontrol.db.MyDatabase;
import com.sinichi.parentingcontrol.model.Model;
import com.sinichi.parentingcontrol.recycleadapter.RvAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyDatabase database;
    private View view;
    private RecyclerView rv;
    private RvAdapter rvAdapter;
    private List<Model> dataList = new ArrayList<>();
    private ImageView imgAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = Room.databaseBuilder(MainActivity.this, MyDatabase.class, "dataAnak").build();

        initComponents();

        // Ketika icon plus ditekan
        imgAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Buat AlertDialog Membantu Orang Tua
                makeMembantuOrtuDialog();
                //TODO: Buat AlertDialog Sekolah

            }
        });
    }

    private void initComponents() {
        imgAddItem = findViewById(R.id.img_addItems);
        rv = findViewById(R.id.rv_catatan);

        newItemForNewDay();
    }

    private void addNewModel(String tanggal, String hari, String bulan, String tahun, String jumlahSholat, boolean isMembantuOrangTua, boolean isSekolah) {
        Model model = new Model(tanggal, hari, bulan, tahun, jumlahSholat, isMembantuOrangTua, isSekolah);
        dataList.add(model);
        rvAdapter = new RvAdapter(dataList);
        rv.setAdapter(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, true));
    }

    private void makeMembantuOrtuDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.CustomAlertDialog);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_dialogbg, null);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        ImageView imgCeklis = dialogView.findViewById(R.id.img_ceklils);
        ImageView imgSilang = dialogView.findViewById(R.id.img_silang);

        imgCeklis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Edit datalist bagian membantu orang tua menjadi tercentang

                alertDialog.dismiss();
            }
        });

        imgSilang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Edit dataList bagian membantu orang tua menjadi tidak tercentang

                alertDialog.dismiss();
            }
        });
    }

    private void newItemForNewDay() {
        CurrentDimension cd = new CurrentDimension();
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_item, null);

        TextView tvTanggal = view.findViewById(R.id.tv_tanggal);

        String tanggal = tvTanggal.getText().toString();
        String tanggalSekarang = cd.getDate();

        // Jika hari ini tidak tersedia, buat database baru
        if (!tanggalSekarang.equals(tanggal)) {
            //TODO: Kirim data menuju firebase
            addNewModel(cd.getDate(), cd.getDays(), defineMonthFromNumber(cd.getMonth()), cd.getYear(), "0", false, false); // temp data
        }
    }

    private String defineMonthFromNumber(String number) {
        String bulan = "";
        switch (number) {
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