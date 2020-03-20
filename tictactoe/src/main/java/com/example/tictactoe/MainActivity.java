package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyView myView = findViewById(R.id.myView2);
        boolean isX = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("key_is_x_turn",true);
       // SharedPreferences pref = getSharedPreferences("mypref",MODE_PRIVATE);
        myView.startNewGame(isX);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_options){
            startActivity(new Intent(this,PrefActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
