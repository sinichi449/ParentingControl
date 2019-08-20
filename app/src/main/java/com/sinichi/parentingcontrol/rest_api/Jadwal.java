package com.sinichi.parentingcontrol.rest_api;

import com.google.gson.annotations.SerializedName;

public class Jadwal {
    @SerializedName("fajr")
    String subuh;

    @SerializedName("dhuhr")
    String dhuhr;

    @SerializedName("asr")
    String ashar;

    @SerializedName("maghrib")
    String maghrib;

    @SerializedName("isha")
    String isya;

    public String getSubuh() {
        return subuh;
    }

    public void setSubuh(String subuh) {
        this.subuh = subuh;
    }

    public String getDhuhr() {
        return dhuhr;
    }

    public void setDhuhr(String dhuhr) {
        this.dhuhr = dhuhr;
    }

    public String getAshar() {
        return ashar;
    }

    public void setAshar(String ashar) {
        this.ashar = ashar;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(String maghrib) {
        this.maghrib = maghrib;
    }

    public String getIsya() {
        return isya;
    }

    public void setIsya(String isya) {
        this.isya = isya;
    }
}
