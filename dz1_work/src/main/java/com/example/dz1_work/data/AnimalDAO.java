package com.example.dz1_work.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AnimalDAO {
    @Insert
    void insert(Animal animal);

    @Update
    void update(Animal animal);

    @Query("SELECT * FROM animal_table")
    LiveData<List<Animal>> getAll();

    @Delete
    void delete(Animal animal);

    @Query("delete from animal_table")
    void deleteAll();

}
