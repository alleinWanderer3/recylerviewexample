package com.hack.fragmentsinteractiondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity implements RListFragment.ListItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    public void onListItemClicked(int position, User user) {
        Intent intent = new Intent(this,DetailActivity.class);

        startActivity(intent);
    }
}
