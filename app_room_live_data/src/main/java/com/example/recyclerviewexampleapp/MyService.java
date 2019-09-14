package com.example.recyclerviewexampleapp;

import android.app.IntentService;
import android.content.Intent;

import java.util.Arrays;

public class MyService extends IntentService {
    public MyService() {
        super("service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        MyApp.getDataDataBase(this).getMyDataDAO().deleteAll();
        //MyData[] arr = MyApp.data.toArray(new MyData[]{});
        MyApp.getDataDataBase(this).getMyDataDAO().insertAll(MyApp.data);

    }
}
