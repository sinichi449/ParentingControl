package com.sinichi.parentingcontrol.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Users.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract UsersDao usersDao();
}
