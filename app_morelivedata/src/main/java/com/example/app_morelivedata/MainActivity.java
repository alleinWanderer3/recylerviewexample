package com.example.app_morelivedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    MutableLiveData<String> live_string = new MutableLiveData<>();
    TextView tv_data;
    Random rnd = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_data = findViewById(R.id.tv_live_data);
        live_string.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tv_data.setText(s);
            }
        });
        live_string.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //inser
            }
        });
        ((FloatingActionButton)findViewById(R.id.floatingActionButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
    }

    public void changeLiveString(View view) {
        live_string.setValue(live_string.getValue()+ (char)(65+rnd.nextInt(191)));

    }

}
