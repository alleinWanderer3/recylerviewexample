package com.hack.fragmentsinteractiondemo;

import android.app.Application;

import java.util.ArrayList;
import java.util.Random;

public class MyApp extends Application {
    public ArrayList<User> getData(){
        String[] names = this.getResources().getStringArray(R.array.data);
        ArrayList<User> data = new ArrayList<>();
        Random rnd = new Random();
        for(int i=0;i<names.length;++i){
            data.add(new User(names[i],i+1,rnd.nextBoolean()));
        }
        return data;
    }
}
