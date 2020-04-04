package com.hack.fragmentsinteractiondemo;

import android.app.Application;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public class MyApp extends Application {
    public ArrayList<Pair> getPair(){
        String[] pairs = this.getResources().getStringArray(R.array.pairs_list);
        ArrayList<Pair> data = new ArrayList<>();
        Random rnd = new Random();
        for(int i=0;i<pairs.length;++i){
            int[] item;

        }
        return data;
    }
}
