package com.example.recyclerviewexampleapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MyData.class},version = 2)
public abstract class MyDataDataBase extends RoomDatabase {
    public abstract MyDataDAO getMyDataDAO();


}
