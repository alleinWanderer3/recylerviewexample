package com.example.recyclerviewexampleapp;

import android.app.Application;
import android.content.Intent;

import androidx.room.Room;

import java.util.ArrayList;

public class MyApp extends Application {
    public static ArrayList<MyData> data;
    public static MyDataDataBase dataDataBase;


    @Override
    public void onCreate() {
        super.onCreate();


    }


}
