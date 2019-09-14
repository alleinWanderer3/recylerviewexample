package com.example.recyclerviewexampleapp;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import androidx.room.Room;

import java.util.ArrayList;

import static com.example.recyclerviewexampleapp.MyApp.dataDataBase;

public class LoadDataService extends IntentService {

    public LoadDataService() {
        super("load service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        MyApp.dataDataBase = Room
                .databaseBuilder(this,MyDataDataBase.class,"database")
                .fallbackToDestructiveMigration()
                .build();
        MyDataDAO dataDAO = MyApp.dataDataBase.getMyDataDAO();
        MyApp.data = new ArrayList<>(dataDAO.getAll());
        startActivity(new Intent(this,List2Activity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .setAction("data loaded"));
    }
}
