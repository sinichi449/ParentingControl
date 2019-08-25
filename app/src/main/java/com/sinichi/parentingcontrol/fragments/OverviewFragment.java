package com.sinichi.parentingcontrol.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sinichi.parentingcontrol.CurrentDimension;
import com.sinichi.parentingcontrol.R;
import com.sinichi.parentingcontrol.model.Model;
import com.sinichi.parentingcontrol.adapters.RvAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends Fragment implements RvAdapter.OnRecyclerViewClickListener {

    private List<Model> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RvAdapter rvAdapter;
    private View view;

    public OverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        addModel("20", "Selasa", "Agustus", "2019", "3", false, true);
        addModel("21", "Rabu", "Agustus", "2019", "5", true, true);
        addModel("22", "Kamis", "Agustus", "2019", "4", false, true);
        addModel("23", "Jum'at", "Agustus", "2019", "5", true, true);
        addModel("24", "Sabtu", "Agustus", "2019", "4", true, false);
        newItemForNewDay();

        return view;
    }

    private void addModel(String tanggal, String hari, String bulan, String tahun, String jumlahSholat, boolean membantuOrtu, boolean sekolah) {
        Model model = new Model(tanggal, hari, bulan, tahun, jumlahSholat, membantuOrtu, sekolah);
        dataList.add(model);

        rvAdapter = new RvAdapter(dataList);
        rvAdapter.setListener(this);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, true));
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
            addModel(cd.getDate(), cd.getDays(), defineMonthFromNumber(cd.getMonth()), cd.getYear(), "0", false, false); // temp data
        }

    }

    @Override
    public void onClick(Model model) {
        // TODO: Masukkan action delete
        // Prototype menggunakan pengecekan getItemId menggunakan Toast
        Toast.makeText(getActivity(), "Punten slurr tasik dereng dados sedoyo", Toast.LENGTH_SHORT).show();
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
