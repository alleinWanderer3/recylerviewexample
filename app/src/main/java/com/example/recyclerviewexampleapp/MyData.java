package com.example.recyclerviewexampleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.recyclerviewexampleapp.db_utils.MyDBHelper;

import java.util.ArrayList;
import java.util.Random;

public class MyData implements Parcelable {
    public interface  OnOpenDataListener{
        void onOpenData(ArrayList<MyData> data);

    }
    public String name, surname;
    public int year;
    public int type;
    public int id;

    public MyData(String name, String surname, int year, int type) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.type = type;
        this.id = -1;
    }
    public MyData(String name, String surname, int year, int type, int id) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.type = type;
        this.id = id;
    }
    protected MyData(Parcel in) {
        name = in.readString();
        surname = in.readString();
        year = in.readInt();
        type = in.readInt();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeInt(year);
        dest.writeInt(type);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<MyData> CREATOR = new Creator<MyData>() {
        @Override
        public MyData createFromParcel(Parcel in) {
            return new MyData(in);
        }

        @Override
        public MyData[] newArray(int size) {
            return new MyData[size];
        }
    };

    /**
     * method for generate random MyData array from string resources
     * @param context - object for get resources
     * @param size - items count
     * @return
     */
    public static ArrayList<MyData> dataGenerator(Context context, int size){
        ArrayList<MyData> data = new ArrayList<>();
        String[] names =context.getResources().getStringArray(R.array.names);
        String[] surnames =context.getResources().getStringArray(R.array.surnames);
        Random rand = new Random();
        for(int i = 0;i < size; i++){
            MyData item = new MyData(names[rand.nextInt(names.length)],
                    surnames[rand.nextInt(surnames.length)],
                    1990+rand.nextInt(30),
                    rand.nextInt(4));
            data.add(item);
        }
        return data;
    }
    public static ArrayList<MyData> datafromDB(Context context){
        MyDBHelper myDBHelper = new MyDBHelper(context);
        SQLiteDatabase db;
        db = myDBHelper.getReadableDatabase();
        Cursor cursor = db.query(MyDBHelper.DB_TABLE,
                null,null,null,null,null,null);
        ArrayList<MyData> data = new ArrayList<>();
        int name_index = cursor.getColumnIndex(MyDBHelper.KEY_NAME);
        int surname_index = cursor.getColumnIndex(MyDBHelper.KEY_SURNAME);
        int name_type_index = cursor.getColumnIndex(MyDBHelper.KEY_TYPE);
        int id_index = cursor.getColumnIndex(MyDBHelper.KEY_ID);
        if(cursor.isBeforeFirst()){
            while (cursor.moveToNext()){
                MyData item = new MyData(cursor.getString(name_index),
                        cursor.getString(surname_index),1111,cursor.getInt(name_type_index)
                ,cursor.getInt(id_index));
                data.add(item);
            }
        }
        cursor.close();
        db.close();
        myDBHelper.close();
       return data;
    }
    public static void updateDB(Context context, ArrayList<MyData> data){
        MyDBHelper myDBHelper = new MyDBHelper(context);
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        db.delete(MyDBHelper.DB_TABLE,null,null);
        db.beginTransaction();
        try {
            for (MyData item : data) {
                ContentValues values = new ContentValues();
                values.put(MyDBHelper.KEY_NAME, item.name);
                values.put(MyDBHelper.KEY_SURNAME, item.surname);
                values.put(MyDBHelper.KEY_TYPE, item.type);
                db.insert(MyDBHelper.DB_TABLE,null,values);
            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
        db.close();
        myDBHelper.close();
    }
}