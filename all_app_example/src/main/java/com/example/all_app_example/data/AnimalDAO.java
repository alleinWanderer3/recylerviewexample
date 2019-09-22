package com.example.all_app_example.data;


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
    void insertAll(List<Animal> data);

    @Insert
    void insertAnimal(Animal animal);

    @Update
    void update(Animal item);

    @Delete
    void deleteAll(Animal item);

    @Query("delete from animal_table")
    void deleteAll();


    @Query("select * from animal_table")
    LiveData<List<Animal>> getAllAnimals();

}
