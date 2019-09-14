package com.example.app_morelivedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;


public class Main2Activity extends AppCompatActivity {
    MutableLiveData<String> live_string = new MutableLiveData<>();
    TextView tv_data;
    Random rnd = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv_data = findViewById(R.id.tv_live_data);

        live_string.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tv_data.setText(s);
            }
        });
        ((FloatingActionButton)findViewById(R.id.floatingActionButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this,Main3Activity.class));
            }
        });

    }

    public void changeLiveString(View view) {
        live_string.setValue(live_string.getValue()+ (char)(65+rnd.nextInt(191)));
    }

    public void delFragment(View view) {
        FragmentManager fm = getSupportFragmentManager();
        if(fm.findFragmentById(R.id.fl_f1)!= null) {
            fm.beginTransaction().remove(fm.findFragmentById(R.id.fl_f1)).commit();
        }
    }

    public void addFragment(View view) {
        BlankFragment fragment = BlankFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_f1,fragment).commit();
        live_string.observe(this,fragment);
    }
}
