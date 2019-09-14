package com.example.recyclerviewexampleapp.db_utils;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    public static final String AUTHORITY =
            "com.example.recyclerviewexampleapp.provider";
    //com.example.recyclerviewexampleapp.provider/table_1 => 101
    //com.example.recyclerviewexampleapp.provider/table_1/3 =>102


    private final static int WHOLE_TABLE = 101;
    private final static int ONE_ROW = 102;
    public final static UriMatcher matcher= new UriMatcher(UriMatcher.NO_MATCH);
    {
        matcher.addURI(AUTHORITY,MyDBHelper.DB_TABLE,WHOLE_TABLE);
        matcher.addURI(AUTHORITY,MyDBHelper.DB_TABLE+"/#",ONE_ROW);
    }

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count;
        switch (matcher.match(uri)){
            case WHOLE_TABLE:
                count = MyDBHelper.getInstance(getContext()).getWritableDatabase().delete(MyDBHelper.DB_TABLE,
                        selection,
                        selectionArgs);
                break;
            case ONE_ROW:
                if(selection == null && selectionArgs == null){
                    selection = MyDBHelper.KEY_ID+"=?";
                    selectionArgs = new String[]{uri.getLastPathSegment()};
                }
                count = MyDBHelper.getInstance(getContext()).
                        getWritableDatabase().
                        delete(
                                MyDBHelper.DB_TABLE,
                                selection,
                                selectionArgs);
                break;
                default:
                    throw new UnsupportedOperationException("Not yet implemented");
        }
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (matcher.match(uri)){
            case WHOLE_TABLE:
                return "vnd.android.cursor.dir/vnd."+AUTHORITY+"."+MyDBHelper.DB_TABLE;
            case ONE_ROW:
                return "vnd.android.cursor.item /vnd."+AUTHORITY+"."+MyDBHelper.DB_TABLE;
                default:
                    return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
       if(matcher.match(uri) == WHOLE_TABLE){//athority/table_1/2
           long id = MyDBHelper.getInstance(getContext()).getWritableDatabase().
                   insertOrThrow(MyDBHelper.DB_TABLE,null,values);
            return ContentUris.withAppendedId(uri,id);
       } else {
           throw new IllegalArgumentException("wrong uri");
       }
    }

    @Override
    public boolean onCreate() {
        return true;
    }
    //com.example.recyclerviewexampleapp.provider/table_1/1
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Cursor cursor;
        switch (matcher.match(uri)){
            case WHOLE_TABLE:
                cursor = MyDBHelper.getInstance(getContext()).getWritableDatabase().query(
                        MyDBHelper.DB_TABLE,
                        projection,selection,selectionArgs,null,null,null);
                return cursor;
            case ONE_ROW:
                if(selection == null){
                    selection = MyDBHelper.KEY_ID+"=?";
                    selectionArgs = new String[]{uri.getLastPathSegment()};
                } else {
                    throw  new IllegalArgumentException("selection must be null for row uri");
                }
                cursor = MyDBHelper.getInstance(getContext()).getWritableDatabase().query(
                        MyDBHelper.DB_TABLE,
                        projection,selection,selectionArgs,null,null,null);
                return cursor;
                default:
                    throw new IllegalArgumentException("wrong URI");

        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
