package com.hack.fragmentsinteractiondemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity implements RListFragment.ListItemClickListener{
    int mSelectedPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        if(savedInstanceState !=null){
            mSelectedPosition = savedInstanceState.getInt("position",0);
        }

        if(mSelectedPosition == -1) return;
        if(findViewById(R.id.fl_details) != null){
            User user = ((MyApp)getApplicationContext()).getData().get(mSelectedPosition);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_details,DetailFragmen.newInstance(user.name,user.id,user.isMale))
                    .addToBackStack("detail")
                    .commit();
        } else {
            User user = ((MyApp)getApplicationContext()).getData().get(mSelectedPosition);
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("name", user.name);
            intent.putExtra("id", user.id);
            intent.putExtra("gender", user.isMale);
            startActivity(intent);
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position",mSelectedPosition);
    }


    @Override
    public void onListItemClicked(int position, User user) {
        mSelectedPosition = position;
        boolean isLand = findViewById(R.id.fl_details) != null;
        if(isLand){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_details,DetailFragmen.newInstance(user.name,user.id,user.isMale))
                    .addToBackStack("detail")
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("posistion", position);
            intent.putExtra("name", user.name);
            intent.putExtra("id", user.id);
            intent.putExtra("gender", user.isMale);
            startActivity(intent);
        }
    }
}
