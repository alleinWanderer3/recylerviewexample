package com.hack.asyncexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static final String MARKETS_URL = "https://api.bittrex.com/api/v1.1/public/getmarkets";
    TextView tv_dataFromMarkets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_dataFromMarkets = findViewById(R.id.tv_data);
    }

    public String getData(String baseURL) throws IOException {
        URL url = new URL(baseURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = "";
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine())!=null){
            builder.append(line);
        }
        return builder.toString();
    }

    public void onClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = getData(MARKETS_URL);
                    Log.d("tag","data from server:" + s);
                   // tv_dataFromMarkets.setText(s);
                } catch (IOException e) {
                    e.printStackTrace();
                   // tv_dataFromMarkets.setText("error e"+ e.getMessage());
                }
            }
        }).start();
    }
}
