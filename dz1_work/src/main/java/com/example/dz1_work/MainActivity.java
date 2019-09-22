package com.example.dz1_work;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.dz1_work.data.AnimalViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //AnimalViewModel animalViewModel = ViewModelProviders.of(this).get(AnimalViewModel.class);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_portrait,new DataListFragment())
                .commit();

    }
}
