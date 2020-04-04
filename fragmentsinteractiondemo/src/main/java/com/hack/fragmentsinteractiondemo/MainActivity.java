package com.hack.fragmentsinteractiondemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoginFragment.loginFragmentListener, RListFragment.ListItemClickListener {
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
                        .replace(R.id.fl_container,DetailFragmen.newInstance("",0,true))
                        .addToBackStack("list")
                        .commit();
                break;
            case R.id.menu_item_exit:
                this.finish();
                break;
        }
        return true;
    }
    public void dos(){

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

    @Override
    public void onListItemClicked(int position, User user) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container,DetailFragmen.newInstance(user.name,user.id,user.isMale))
                .addToBackStack("detail")
                .commit();
    }
}
