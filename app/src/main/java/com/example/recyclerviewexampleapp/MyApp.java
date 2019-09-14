package com.example.recyclerviewexampleapp;

import android.app.Application;

import com.example.recyclerviewexampleapp.db_utils.MyDBHelper;

import java.util.ArrayList;

public class MyApp extends Application {
    public static ArrayList<MyData> data;


    @Override
    public void onCreate() {
        super.onCreate();

    }


}
