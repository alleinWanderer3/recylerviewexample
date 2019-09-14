package com.example.userforprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final static String DATA_URI = "content://com.example.recyclerviewexampleapp.provider/table_1";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_TYPE = "type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cursor cursor = getContentResolver().query(Uri.parse(DATA_URI),
                null,null,null,null);
        if(cursor == null){
            Toast.makeText(this,"cursor is null",Toast.LENGTH_SHORT).show();
        }
        String[] from = {KEY_NAME,KEY_SURNAME};
        int[] to = {R.id.tv_name,R.id.tv_surname};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.list_item,cursor,from,to);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
