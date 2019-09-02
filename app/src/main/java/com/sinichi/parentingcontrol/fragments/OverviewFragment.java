package com.sinichi.parentingcontrol.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sinichi.parentingcontrol.CurrentDimension;
import com.sinichi.parentingcontrol.R;
import com.sinichi.parentingcontrol.model.Model;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    private View view;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<Model, DataViewHolder> mFirebaseAdapter;
    private RecyclerView mRecyclerView;
    private DatabaseReference kegiatatanRef;

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        TextView tvTanggal;
        TextView tvHari;
        TextView tvBulan;
        TextView tvTahun;
        TextView tvJumlahSholat;
        CheckBox chkMembantuOrtu;
        CheckBox chkSekolah;

        DataViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvHari = itemView.findViewById(R.id.tv_hari);
            tvBulan = itemView.findViewById(R.id.tv_bulan);
            tvTahun = itemView.findViewById(R.id.tv_tahun);
            tvJumlahSholat = itemView.findViewById(R.id.tv_jumlahSholat);
            chkMembantuOrtu = itemView.findViewById(R.id.chkbx_membantuOrtu);
            chkSekolah = itemView.findViewById(R.id.chkbx_sekolah);
        }
    }

    public OverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        SnapshotParser<Model> parser = new SnapshotParser<Model>() {
            @NonNull
            @Override
            public Model parseSnapshot(@NonNull DataSnapshot snapshot) {
                Model model = snapshot.getValue(Model.class);
                if (model != null) {
                    model.setId(snapshot.getKey());
                }
                return model;
            }
        };

        kegiatatanRef = mFirebaseDatabaseReference
                .child(mFirebaseUser.getUid())
                .child("data_kegiatan");

        // TODO: Update recycler with FirebaseRecyclerAdapter
        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>().setQuery(kegiatatanRef,parser)
                .build();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Model, DataViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DataViewHolder dataViewHolder, int i, @NonNull Model model) {
                // TODO: Atur masing2 item pada layout_item
                dataViewHolder.tvTanggal.setText(model.getTanggal());
                dataViewHolder.tvHari.setText(model.getHari());
                dataViewHolder.tvBulan.setText(model.getBulan());
                dataViewHolder.tvTahun.setText(model.getTahun());
                dataViewHolder.tvJumlahSholat.setText(model.getJumlahSholat());
                dataViewHolder.chkMembantuOrtu.setChecked(model.isMembantuOrangTua());
                dataViewHolder.chkSekolah.setChecked(model.isSekolah());
                dataViewHolder.chkMembantuOrtu.setEnabled(false);
                dataViewHolder.chkSekolah.setEnabled(false);
            }

            @NonNull
            @Override
            public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater1 = LayoutInflater.from(parent.getContext());
                return new DataViewHolder(inflater1.inflate(
                        R.layout.layout_item, parent, false));
            }
        };
        mRecyclerView.setAdapter(mFirebaseAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, true));
        mFirebaseAdapter.startListening();

        newItemForNewDay();
        return view;
    }

    private void addModel(String tanggal, String hari, String bulan,
                          String tahun, String jumlahSholat,
                          boolean isMembantuOrtu, boolean isSekolah) {
        Model model = new Model(tanggal, hari, bulan, tahun, jumlahSholat,
                isMembantuOrtu, isSekolah);
        kegiatatanRef.push().setValue(model);

    }



    // TODO: Fix double add date
    private void newItemForNewDay() {
        CurrentDimension cd = new CurrentDimension();
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_item, null);

        // Mendapatkan item tanggal
        TextView tvTanggal = view.findViewById(R.id.tv_tanggal);
        TextView tvBulan = view.findViewById(R.id.tv_bulan);
        TextView tvTahun = view.findViewById(R.id.tv_tahun);

        String tanggal = tvTanggal.getText().toString();
        String tanggalSekarang = cd.getDate();
        String bulanSekarang = defineMonthFromNumber(cd.getMonth());
        String tahunSekarang = cd.getYear();

        // Jika hari ini tidak tersedia, buat database baru
        if (!tanggalSekarang.equals(tanggal) && !bulanSekarang.equals(tvBulan.getText().toString())
                && !tahunSekarang.equals(tvTahun.getText().toString())) {
            //TODO: Kirim data menuju firebase
            addModel(cd.getDate(), cd.getDays(), defineMonthFromNumber(cd.getMonth()), cd.getYear(), "0", false, false); // temp data
        }

    }

    private String defineMonthFromNumber(String number) {
        String bulan = "";
        switch (number) {
            case "0":
                bulan = "Januari";
                break;
            case "1":
                bulan = "Februari";
                break;
            case "2":
                bulan = "Maret";
                break;
            case "3":
                bulan = "April";
                break;
            case "4":
                bulan = "Mei";
                break;
            case "5":
                bulan = "Juni";
                break;
            case "6":
                bulan = "Juli";
                break;
            case "7":
                bulan = "Agustus";
                break;
            case "8":
                bulan = "September";
                break;
            case "9":
                bulan = "Oktober";
                break;
            case "10":
                bulan = "November";
                break;
            case "11":
                bulan = "Desember";
                break;
        }
        return bulan;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getContext(), "Google Play Service Error",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        mFirebaseAdapter.startListening();
    }

    @Override
    public void onPause() {
        super.onPause();
        mFirebaseAdapter.stopListening();
    }
}
