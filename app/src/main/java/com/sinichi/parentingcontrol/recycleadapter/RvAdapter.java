package com.sinichi.parentingcontrol.recycleadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sinichi.parentingcontrol.R;
import com.sinichi.parentingcontrol.model.Model;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    List<Model> dataList;

    public RvAdapter(List<Model> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model model = dataList.get(position);
        holder.tvJumlahSholat.setText(model.getJumlahSholat());
        holder.chkMembantuOrtu.setChecked(model.isMembantuOrangTua());
        holder.chkMembantuOrtu.setEnabled(false);
        holder.chkSekolah.setChecked(model.isSekolah());
        holder.chkSekolah.setEnabled(false);
        holder.tvTanggal.setText(model.getTanggal());
        holder.tvBulan.setText(model.getBulan());
        holder.tvHari.setText(model.getHari());
        holder.tvTahun.setText(model.getTahun());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvJumlahSholat;
        private CheckBox chkMembantuOrtu, chkSekolah;
        private TextView tvTanggal, tvHari, tvBulan, tvTahun;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJumlahSholat = itemView.findViewById(R.id.tv_jumlahSholat);
            chkMembantuOrtu = itemView.findViewById(R.id.chkbx_membantuOrtu);
            chkSekolah = itemView.findViewById(R.id.chkbx_sekolah);
            tvBulan = itemView.findViewById(R.id.tv_bulan);
            tvHari = itemView.findViewById(R.id.tv_hari);
            tvTahun = itemView.findViewById(R.id.tv_tahun);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
        }
    }
}
