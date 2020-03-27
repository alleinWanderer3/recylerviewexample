package com.hack.fragmentsinteractiondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int id = intent.getIntExtra("id",0);
        boolean isMale = intent.getBooleanExtra("gender",true);
        DetailFragmen detailFragmen = DetailFragmen.newInstance(name,id,isMale);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content,detailFragmen)
                .commit();
    }
}
