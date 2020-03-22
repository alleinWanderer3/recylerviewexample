package com.hack.fragmentsinteractiondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }

        DetailFragmen detailFragmen = DetailFragmen.newInstance("Name",8,true);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content,detailFragmen)
                .commit();
    }
}
