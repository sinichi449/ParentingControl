package com.sinichi.parentingcontrol.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sinichi.parentingcontrol.CurrentDimension;
import com.sinichi.parentingcontrol.R;
import com.sinichi.parentingcontrol.model.Model;
import com.sinichi.parentingcontrol.recycleadapter.RvAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View view;
    private RecyclerView rv;
    private RvAdapter rvAdapter;
    private List<Model> dataList = new ArrayList<>();
    private ImageView imgAddItem;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        // Ketika icon plus ditekan
        imgAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Buat AlertDialog Membantu Orang Tua
                makeMembantuOrtuDialog();
                //TODO: Buat AlertDialog Sekolah
                makeSekolahDialog();
            }
        });
    }

    private void initComponents() {
        imgAddItem = findViewById(R.id.img_addItems);
        rv = findViewById(R.id.rv_catatan);

        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_item, null);

        final CheckBox chkMembantuOrtu = v.findViewById(R.id.chkbx_membantuOrtu);

//        databaseReference = FirebaseDatabase.getInstance().getReference().child("membantuOrtu");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                boolean membantuOrtu = dataSnapshot.getValue(boolean.class);
//                chkMembantuOrtu.setChecked(membantuOrtu);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        addNewModel("22", "Kamis", "Agustus", "2019", "4", true, true);
        addNewModel("21", "Rabu", "Agustus", "2019", "3", true, true);
        addNewModel("20", "Selasa", "Agustus", "2019", "4", false, true);
        addNewModel("19", "Senin", "Agustus", "2019", "5", true, true);
        newItemForNewDay();
    }

    private void addNewModel(String tanggal, String hari, String bulan, String tahun, String jumlahSholat, boolean isMembantuOrangTua, boolean isSekolah) {
        Model model = new Model(tanggal, hari, bulan, tahun, jumlahSholat, isMembantuOrangTua, isSekolah); // temp data
        dataList.add(model);
        rvAdapter = new RvAdapter(dataList);
        rv.setAdapter(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
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
                databaseReference.setValue(true);
                alertDialog.dismiss();
            }
        });

        imgSilang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Edit dataList bagian membantu orang tua menjadi tidak tercentang
                databaseReference.setValue(false);
                alertDialog.dismiss();
            }
        });
    }

    private void makeSekolahDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_dialogsklhbg, null);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        ImageView imgCeklisSklh = dialogView.findViewById(R.id.img_ceklilsSklh);
        ImageView imgSilangSklh = dialogView.findViewById(R.id.img_silangSklh);

        imgCeklisSklh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Aktifkan ceklis pada sekolah

            }
        });

        imgSilangSklh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Nonaktifkan ceklis pada sekolah

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