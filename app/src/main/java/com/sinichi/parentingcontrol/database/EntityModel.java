package com.sinichi.parentingcontrol.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class EntityModel {

    @Entity(tableName = "users")
    public class User {

        @PrimaryKey
        @ColumnInfo(name = "id")
        private String mId;

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

        @ColumnInfo(name = "membantuOrangTua")
        private boolean membantuOrangTua;

        @ColumnInfo(name = "sekolah")
        private boolean sekolah;

        public User(String mId, String tanggal, String hari, String bulan, String tahun, int jumlahSholat, boolean membantuOrangTua, boolean sekolah) {
            this.mId = mId;
            this.tanggal = tanggal;
            this.hari = hari;
            this.bulan = bulan;
            this.tahun = tahun;
            this.jumlahSholat = jumlahSholat;
            this.membantuOrangTua = membantuOrangTua;
            this.sekolah = sekolah;
        }
    }
}
