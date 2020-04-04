package com.hack.fragmentsinteractiondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
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

public class MainActivity<line> extends AppCompatActivity implements LoginFragment.loginFragmentListener, RListFragment.ListItemClickListener {
    boolean isAuth = false;
    public static final String MARKETS_URL = "https://api.bittrex.com/api/v1.1/public/getmarketsummaries";
    TextView tv_dataFromMarkets;
    public final static String login1 = "user";
    public final static String psw1 = "pass";
    public String currentUser = "";

    public MainActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            if (isAuth) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_container, new MenuFragment())
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_container, new LoginFragment())
                        .commit();
            }
        }

    }

    public void setAuthed(boolean isAuth) {
        this.isAuth = isAuth;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu a) {
        getMenuInflater().inflate(R.menu.main_menu, a);
        return true;
    }


    public void dos() {

    }


    }

    @Override
    public void onUserLogin(String user) {

    }

    @Override
    public void onUserLoginDenied() {
        isAuth = false;
        Toast.makeText(this, "Wrond user data", Toast.LENGTH_SHORT).show();
    }


    public void onListItemClicked(int position, Pair pair) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container, MenuFragment.newInstance(pair))
                .addToBackStack("menu")
                .commit();
    }

    public String getData(String baseURL) throws IOException {
        URL url = new URL(null);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = "";
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final String s = getData(MARKETS_URL);
                        Log.d("tag", "data from server:" + s);
                        Handler handler = new Handler();
                        Message message = handler.obtainMessage();
                        JSONObject jsonObject = new JSONObject(s);
                        boolean success = jsonObject.getBoolean("success");
                        ArrayList<Pair_crypto> pairs = new ArrayList<>();
                        if (success) {
                            JSONArray jsonArray = jsonObject.getJSONArray("result");
                            for (int i = 0; i < jsonArray.length(); ++i) {
                                JSONObject pair = jsonArray.getJSONObject(i);
                                String title = pair.getString("MarketName");
                                double price = pair.getDouble("Last");
                                double volume = pair.getDouble("Volume");
                                pairs.
                                        add(new Pair_crypto(title, price, volume));
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


        class Pair {
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
