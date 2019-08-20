package com.sinichi.parentingcontrol.model;

public class Model {

    String jumlahSholat;
    boolean membantuOrangTua;
    boolean sekolah;

    public Model(String jumlahSholat, boolean membantuOrangTua, boolean sekolah) {
        this.jumlahSholat = jumlahSholat;
        this.membantuOrangTua = membantuOrangTua;
        this.sekolah = sekolah;
    }

    public String getJumlahSholat() {
        return jumlahSholat;
    }

    public void setJumlahSholat(String jumlahSholat) {
        this.jumlahSholat = jumlahSholat;
    }

    public boolean isMembantuOrangTua() {
        return membantuOrangTua;
    }

    public void setMembantuOrangTua(boolean membantuOrangTua) {
        this.membantuOrangTua = membantuOrangTua;
    }

    public boolean isSekolah() {
        return sekolah;
    }

    public void setSekolah(boolean sekolah) {
        this.sekolah = sekolah;
    }
}
