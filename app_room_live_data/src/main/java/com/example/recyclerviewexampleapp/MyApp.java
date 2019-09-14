package com.example.recyclerviewexampleapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.room.Room;

import java.util.ArrayList;

public class MyApp extends Application {
    public static ArrayList<MyData> data;
    private static MyDataDataBase dataDataBase;
    public static MyDataDataBase getDataDataBase(Context context){
        if(dataDataBase == null){
            dataDataBase = Room
                    .databaseBuilder(context.getApplicationContext(),MyDataDataBase.class,"database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dataDataBase;
    }


    @Override
    public void onCreate() {
        super.onCreate();

    }


}
