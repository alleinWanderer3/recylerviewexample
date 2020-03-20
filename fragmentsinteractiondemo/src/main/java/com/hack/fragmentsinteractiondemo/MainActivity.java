package com.hack.fragmentsinteractiondemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoginFragment.loginFragmentListener{
    boolean isAuth = false;

    public final static String login1 = "user";
    public final static String psw1 = "pass";
    public String currentUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
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
    public void setAuthed(boolean isAuth){
        this.isAuth = isAuth;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu a) {
        getMenuInflater().inflate(R.menu.main_menu,a);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_show_list:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_container,new RListFragment())
                        .addToBackStack("list")
                        .commit();
                break;
            case R.id.menu_item_show_details:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_container,DetailFragmen.newInstance("",""))
                        .addToBackStack("list")
                        .commit();
                break;
            case R.id.menu_item_exit:
                this.finish();
                break;
        }
        return true;
    }

    @Override
    public void onUserLogin(String user) {
        isAuth = true;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container,MenuFragment.newInstance(user))
                .addToBackStack("menu")
                .commit();
    }

    @Override
    public void onUserLoginDenied() {
        isAuth = false;
        Toast.makeText(this,"Wrond user data",Toast.LENGTH_SHORT).show();
    }
}
