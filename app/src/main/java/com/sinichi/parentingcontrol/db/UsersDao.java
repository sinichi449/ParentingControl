package com.sinichi.parentingcontrol.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsersDao {

    @Query("SELECT * FROM users")
    List<Users> getAll();

    @Insert
    void insertAll(List<Users> dataList);

    @Update
    void update(Users users);

    @Delete
    void delete(Users users);
}
