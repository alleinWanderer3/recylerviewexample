package com.hack.asyncexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String MARKETS_URL = "https://api.bittrex.com/api/v1.1/public/getmarketsummaries";
    TextView tv_dataFromMarkets;
    Handler handler = new Handler(){
        @Override//метод, работающий в UI-потоке
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            ArrayList<Pair> pairs = (ArrayList<Pair>) msg.obj;

            if(tv_dataFromMarkets != null){
                tv_dataFromMarkets.setText("");
                for(int i=0;i < pairs.size();++i){
                    tv_dataFromMarkets.append(pairs.get(i).title+" last="+pairs.get(i).price+" v="+pairs.get(i).volume+"\n");
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_dataFromMarkets = findViewById(R.id.tv_data);
        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,"отложенный запуск",Toast.LENGTH_SHORT).show();
            }
        },7000);

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
                    final String s = getData(MARKETS_URL);
                    Log.d("tag","data from server:" + s);
                    Message message = handler.obtainMessage();
                    JSONObject jsonObject = new JSONObject(s);
                    boolean success = jsonObject.getBoolean("success");
                    ArrayList<Pair> pairs = new ArrayList<>();
/*
 "MarketName": "BTC-LTC",
      "High": 0.0135,
      "Low": 0.012,
      "Volume": 3833.97619253,
      "Last": 0.01349998,
      "BaseVolume": 47.03987026,
      "TimeStamp": "2014-07-09T07:22:16.72",
      "Bid": 0.01271001,
      "Ask": 0.012911,
      "OpenBuyOrders": 45,
      "OpenSellOrders": 45,
      "PrevDay": 0.01229501,
      "Created": "2014-02-13T00:00:00",
      "DisplayMarketName": "string"
 */
                    if(success){
                        JSONArray jsonArray = jsonObject.getJSONArray("result");
                        for(int i=0; i< jsonArray.length();++i){
                            JSONObject pair = jsonArray.getJSONObject(i);
                            String title = pair.getString("MarketName");
                            double last = pair.getDouble("Last");
                            double volume = pair.getDouble("Volume");
                            pairs.add(new Pair(title,last,volume));
                        }
                    } else {

                    }
                    message.obj = pairs;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                   // tv_dataFromMarkets.setText("error e"+ e.getMessage());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    class Pair{
        public String title;
        public double price;
        public double volume;

        public Pair(String title, double price, double volume) {
            this.title = title;
            this.price = price;
            this.volume = volume;
        }
    }

}
