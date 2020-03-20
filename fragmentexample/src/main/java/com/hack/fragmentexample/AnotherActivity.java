package com.hack.fragmentexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

public class AnotherActivity extends AppCompatActivity {
    FirstFragment firstFragment;
    SecondFragment secondFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_a:
                getSupportFragmentManager()
                        .beginTransaction()
                        //.replace(R.id.fl_container,firstFragment)
                        .add(R.id.fl_container,new FirstFragment())
                        .addToBackStack("a")
                        .commit();
                break;
            case R.id.btn_b:
                getSupportFragmentManager()
                        .beginTransaction()
                        //.replace(R.id.fl_container,secondFragment)
                        .add(R.id.fl_container,new SecondFragment())
                        .addToBackStack("b")
                        .commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if(fm.getBackStackEntryCount() > 0){
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
