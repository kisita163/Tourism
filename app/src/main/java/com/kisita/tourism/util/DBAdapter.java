package com.kisita.tourism.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Hugues on 05/09/2016.
 */
public class DBAdapter {
    Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db; // It exposes methods to manage SQL database

    private String DATABASE_NAME = "data";
    private int DATABASE_VERSION = 1;
    String HOTELS = "hotels";
    String RESTAURANTS = "restaurants";
    String EVENTS = "events";



    public DBAdapter(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }
    /**
     * The helper class manages database creation and version management
     * It opens the database if it exists or create it if it does not
     */
    public class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE "+HOTELS+" (id integer primary key autoincrement, name text, latitude double,longitude double, province text, description text);";
            db.execSQL(query);
            query = "CREATE TABLE "+EVENTS+" (id integer primary key autoincrement, name text, latitude double,longitude double, province text, description text);";
            db.execSQL(query);
            query = "CREATE TABLE "+RESTAURANTS+" (id integer primary key autoincrement, name text, latitude double,longitude double, province text, description text);";
            db.execSQL(query);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query = "DROP TABLE IF EXISTS hotels;";
            db.execSQL(query);

            query = "DROP TABLE IF EXISTS restaurants;";
            db.execSQL(query);

            query = "DROP TABLE IF EXISTS events;";
            db.execSQL(query);

            onCreate(db);
        }
    }

    public void insertData(DBEntry entry,String tableName){
        ContentValues  cv = new ContentValues();
        cv.put("name",entry.getName());
        cv.put("latitude",entry.getLatitude());
        cv.put("longitude",entry.getLongitude());
        cv.put("province",entry.getProvince());
        cv.put("description",entry.getDescription());

        db.insert(tableName,null,cv);
    }

    public ArrayList<String> selectAllStudents(){
        ArrayList<String> allEntries = new ArrayList<>();
        Cursor cursor = db.query(HOTELS,null,
                null,
                null,
                null,
                null,
                null
        ); // The cursor provides random read and write access to the result set returned by the a database query
        if (cursor != null && cursor.moveToFirst())
        {
            do{
                allEntries.add(cursor.getString(1));
            }
            while(cursor.moveToNext());
        }
        System.out.println("----------------------------------> "+allEntries.get(0));
        return allEntries;
    }
}
