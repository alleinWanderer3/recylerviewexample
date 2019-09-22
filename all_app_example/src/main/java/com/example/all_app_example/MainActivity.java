package com.example.all_app_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.all_app_example.data.AnimalViewModel;
import com.example.all_app_example.fragments.DataListFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.fl_container)==null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_fragment1,new DataListFragment())
                    .commit();
        }
    }
}
