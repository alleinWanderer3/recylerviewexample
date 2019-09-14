package com.example.recyclerviewexampleapp;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;

public class MyService extends IntentService {


    public MyService() {
        super("service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ArrayList<MyData> data = MyApp.data;
        MyData.updateDB(this,data);
        Log.d("data","data was saves");
    }
}
