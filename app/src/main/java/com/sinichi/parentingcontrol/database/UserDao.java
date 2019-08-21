package com.sinichi.parentingcontrol.database;

import androidx.room.Query;

import java.util.List;

public interface UserDao {

    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    List<EntityModel> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE tanggal IN (:tanggal)")
    EntityModel getTanggal(String tanggal);

    @Query("SELECT * FROM users WHERE hari IN (:hari)")
    EntityModel getDay(String hari);

    @Query("SELECT * FROM users WHERE bulan IN (:bulan)")
    EntityModel getMonth(String bulan);

    @Query("SELECT * FROM users WHERE tahun IN (:tahun)")
    EntityModel getTahun(String tahun);


}
