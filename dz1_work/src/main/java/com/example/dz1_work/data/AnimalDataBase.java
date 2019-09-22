package com.example.dz1_work.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Animal.class},version = 1)
public abstract class AnimalDataBase extends RoomDatabase {
    abstract AnimalDAO getAnimalDAO();
    private static AnimalDataBase instance;
    public static AnimalDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AnimalDataBase.class,"db_animal").build();
        }
        return instance;
    }
}
