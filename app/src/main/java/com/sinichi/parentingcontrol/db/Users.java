package com.sinichi.parentingcontrol.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Users {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "tanggal")
    private String tanggal;

    @ColumnInfo(name = "hari")
    private String hari;

    @ColumnInfo(name = "bulan")
    private String bulan;

    @ColumnInfo(name = "tahun")
    private String tahun;

    @ColumnInfo(name = "jumlahSholat")
    private int jumlahSholat;

    @ColumnInfo(name = "membantuOrtu")
    private boolean membantuOrtu;

    @ColumnInfo(name = "sekolah")
    private boolean sekolah;

    public Users(int uid, String tanggal, String hari, String bulan, String tahun, int jumlahSholat, boolean membantuOrtu, boolean sekolah) {
        this.uid = uid;
        this.tanggal = tanggal;
        this.hari = hari;
        this.bulan = bulan;
        this.tahun = tahun;
        this.jumlahSholat = jumlahSholat;
        this.membantuOrtu = membantuOrtu;
        this.sekolah = sekolah;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public int getJumlahSholat() {
        return jumlahSholat;
    }

    public void setJumlahSholat(int jumlahSholat) {
        this.jumlahSholat = jumlahSholat;
    }

    public boolean isMembantuOrtu() {
        return membantuOrtu;
    }

    public void setMembantuOrtu(boolean membantuOrtu) {
        this.membantuOrtu = membantuOrtu;
    }

    public boolean isSekolah() {
        return sekolah;
    }

    public void setSekolah(boolean sekolah) {
        this.sekolah = sekolah;
    }
}
