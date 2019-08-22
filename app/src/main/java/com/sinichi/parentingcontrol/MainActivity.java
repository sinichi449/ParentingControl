package com.sinichi.parentingcontrol;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.sinichi.parentingcontrol.db.MyDatabase;
import com.sinichi.parentingcontrol.db.Users;
import com.sinichi.parentingcontrol.model.Model;
import com.sinichi.parentingcontrol.recycleadapter.RvAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private MyDatabase database;
    private RecyclerView rv;
    private RvAdapter rvAdapter;
    private List<Model> dataList = new ArrayList<>();
    private ImageView imgAddItem;
    private EditText edtHari, edtTanggal, edtBulan, edtTahun;
    private CheckBox chkMembantuOrtu, chkSekolah, chkSubuh, chkDhuhr, chkAshr, chkMaghrib, chkIsya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Room.databaseBuilder(MainActivity.this, MyDatabase.class, "dataAnak").build();

        initComponents();
        initRecyclerView();

        // Ketika icon plus ditekan
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
        // Penambahan Data
        dataList.add(addModel("16", "Jum'at", "Maret", "2018", "5", true, true));
        dataList.add(addModel("15", "Kamis", "Maret", "2018", "4", false, true));
        dataList.add(addModel("14", "Rabu", "Maret", "2018", "5", true, true));
        dataList.add(addModel("13", "Selasa", "Maret", "2018", "4", true, true));


    }

    private Model addModel(String tanggal, String hari, String bulan, String tahun, String jumlahSholat, boolean isMembantuOrangTua, boolean isSekolah) {
        // Function untuk menambahkan data baru ke dalam RecycleView
        Model model = new Model(tanggal, hari, bulan, tahun, jumlahSholat, isMembantuOrangTua, isSekolah);
        rvAdapter = new RvAdapter(dataList);
        rv.setAdapter(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, true));
        return model;
    }

    private void makeDialog(View view) {
        // Membuat Custom AlertDialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        // Inflate layout custom dialog
        View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.custom_dialog, viewGroup, false);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.drawable.ic_logo);
        dialog.setTitle("Tambahkan Aktivitas");

        // Init components
        edtHari = dialogView.findViewById(R.id.edt_hari);
        edtTanggal = dialogView.findViewById(R.id.edt_tanggal);
        edtBulan = dialogView.findViewById(R.id.edt_bulan);
        edtTahun = dialogView.findViewById(R.id.edt_tahun);
        chkSubuh = dialogView.findViewById(R.id.chk_subuh);
        chkDhuhr = dialogView.findViewById(R.id.chk_dhuhr);
        chkAshr = dialogView.findViewById(R.id.chk_asr);
        chkMaghrib = dialogView.findViewById(R.id.chk_maghrib);
        chkIsya = dialogView.findViewById(R.id.chk_isya);
        chkMembantuOrtu = dialogView.findViewById(R.id.chkbx_addMembantuOrtu);
        chkSekolah = dialogView.findViewById(R.id.chk_addSekolah);

        // Auto complete untuk colom tanggal, hari, bulan, dsb pada EditText Custom Alert Dialog
        CurrentDimension currentDimension = new CurrentDimension();
        edtHari.setText(currentDimension.getDays());
        edtTanggal.setText(currentDimension.getDate());
        edtBulan.setText(currentDimension.getMonth());
        edtTahun.setText(currentDimension.getYear());

        // Klik ok pada custom AlertDialog
        dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                checkForBlankForm();
                String hari = edtHari.getText().toString();
                String tanggal = edtTanggal.getText().toString();
                String bulan = edtBulan.getText().toString();
                String bulanDefined = defineBulan(bulan);
                String tahun = edtTahun.getText().toString();
                String jumlahSholat = String.valueOf(hitungJumlahSholat(chkSubuh, chkDhuhr, chkAshr, chkMaghrib, chkIsya));
                boolean membantuOrtu = chkMembantuOrtu.isChecked();
                boolean sekolah = chkSekolah.isChecked();

                // Memasukkan data dari EditText menuju List<Model>
                Model m = addModel(tanggal, hari, bulanDefined, tahun, jumlahSholat, membantuOrtu, sekolah);
                dataList.add(m);
                List<Users> dataUser =new ArrayList<>();
                Random random = new Random();
                int number = random.nextInt();
                Users u = new Users(number, tanggal, hari, bulanDefined, tahun, Integer.parseInt(jumlahSholat), membantuOrtu, sekolah);
                dataUser.add(u);

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


    // Function untuk mendefinisikan nama bulan berdasarkan angka bulan
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

    private int hitungJumlahSholat(CheckBox subuh, CheckBox dhuhr, CheckBox ashr, CheckBox maghrib, CheckBox isya) {
        int counter = 0;
        if (subuh.isChecked()) {
            counter++;
        }
        if (dhuhr.isChecked()) {
            counter++;
        }
        if (ashr.isChecked()) {
            counter++;
        }
        if (maghrib.isChecked()) {
            counter++;
        }
        if (isya.isChecked()) {
            counter++;
        }
        return counter;
    }

    private void checkForBlankForm() {
        if (TextUtils.isEmpty(edtHari.getText().toString())) {
            edtHari.setError("Mohon isi kotak yang kosong");
        } else if (TextUtils.isEmpty(edtTanggal.getText().toString())) {
            edtTanggal.setError("Mohon isi kotak yang kosong");
        } else if (TextUtils.isEmpty(edtBulan.getText().toString())) {
            edtBulan.setError("Mohon isi kotak yang kosong");
        } else if (TextUtils.isEmpty(edtTahun.getText().toString())) {
            edtTahun.setError("Mohon isi kotak yang kosong");
        }
    }
}

