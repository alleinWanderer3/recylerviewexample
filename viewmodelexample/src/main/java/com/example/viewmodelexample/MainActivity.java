package com.example.viewmodelexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    MyViewModel viewModel;
    TextView tv_int, tv_str;
    Random rnd = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        tv_int = findViewById(R.id.tv_Int);
        tv_str = findViewById(R.id.tv_str);
     /*   tv_int.setText(""+viewModel.dataInt);
        tv_str.setText(viewModel.dataStr);*/
    }

    public void changeValues(View view) {
        viewModel.dataInt = viewModel.dataInt + 1;
        viewModel.dataStr = viewModel.dataStr + "A";
        tv_int.setText(""+viewModel.dataInt);
        tv_str.setText(viewModel.dataStr);
    }
}
