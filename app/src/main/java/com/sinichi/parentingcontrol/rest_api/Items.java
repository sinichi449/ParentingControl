package com.sinichi.parentingcontrol.rest_api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Items {

    @SerializedName("items")
    List<Jadwal> items;

    public Items(List<Jadwal> items) {
        this.items = items;
    }

    public List<Jadwal> getItems() {
        return items;
    }

    public void setItems(List<Jadwal> items) {
        this.items = items;
    }
}
