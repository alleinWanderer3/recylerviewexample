package com.example.recyclerviewexampleapp.db_utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "db1";
    public static final String DB_TABLE = "table_1";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_TYPE = "type";
    public static final String Q_CREATE_DB = "create table "+DB_TABLE +
            " ("+KEY_ID+" integer primary key autoincrement, "+
            KEY_NAME+" text not null, "+
            KEY_SURNAME + " text not null,"+
            KEY_TYPE + " integer);";

    public static final int DB_VERSION = 1;

    private static MyDBHelper instance;
    public static synchronized MyDBHelper getInstance(Context context){
        if(instance == null){
            instance = new MyDBHelper(context.getApplicationContext());
        }
        return instance;
    }

    private MyDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Q_CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+DB_TABLE);
        onCreate(sqLiteDatabase);
    }
}
