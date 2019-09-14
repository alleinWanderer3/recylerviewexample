package com.example.recyclerviewexampleapp;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MyDataDAO {
    @Insert
    void insertAll(List<MyData> items);

    @Delete
    void delete(MyData item);

    @Query("delete from mydata")
    void deleteAll();

    @Query("SELECT * FROM mydata")
    LiveData<List<MyData>> getAll();

}
