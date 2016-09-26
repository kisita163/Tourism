package com.kisita.tourism.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Hugues on 05/09/2016.
 */
public class DBAdapter {
    Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db; // It exposes methods to manage SQL database

    private String DATABASE_NAME = "rdcongo";
    private int DATABASE_VERSION = 1;
    private final static String createDB= "CREATE TABLE "+FeedEntry.TABLE_NAME+
                                          " (id integer primary key autoincrement," +
                                          "type text, "+
                                          "name text, " +
                                          "latitude double," +
                                          "longitude double, " +
                                          "province text, " +
                                          "ville text, " +
                                          "commune text, " +
                                          "description text);";
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
            db.execSQL(createDB);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query = "DROP TABLE IF EXISTS "+FeedEntry.TABLE_NAME+" ;";
            db.execSQL(query);
            onCreate(db);
        }
    }

    public void insertData(DBEntry entry){
        ContentValues  cv = new ContentValues();
        cv.put("name",entry.getName());
        cv.put("type",entry.getType());
        cv.put("latitude",entry.getLatitude());
        cv.put("longitude",entry.getLongitude());
        cv.put("province",entry.getProvince());
        cv.put("description",entry.getDescription());

        db.insert(FeedEntry.TABLE_NAME,null,cv);
    }

    public void deleteAll(){
        db.delete(FeedEntry.TABLE_NAME, null, null);
    }

    public ArrayList<DBEntry> selectEntry(String type,String province){
        ArrayList<DBEntry> allEntries = new ArrayList<>();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedEntry.COLUMN_NAME_NAME,
                FeedEntry.COLUMN_NAME_LATITUDE,
                FeedEntry.COLUMN_NAME_LONGITUDE,
                FeedEntry.COLUMN_NAME_DESCRIPTION
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedEntry.COLUMN_NAME_TYPE + " = ? AND "+
                           FeedEntry.COLUMN_NAME_PROVINCE + " = ?";


        String[] selectionArgs = {type,province};

        Cursor cursor = db.query(FeedEntry.TABLE_NAME, // The table to query
                projection, // The column to return
                selection, // The columns for the WHERE clause
                selectionArgs, // The values for the WHERE clause
                null,
                null,
                null
        ); // The cursor provides random read and write access to the result set returned by the a database query
        if (cursor != null && cursor.moveToFirst())
        {
            do{
                allEntries.add(new DBEntry(cursor.getString(0), // NAME
                                           cursor.getDouble(1), // LATITUDE
                                           cursor.getDouble(2), // LONGITUDE
                                           cursor.getString(3))); // DESCRIPTION
                Log.i("DATABASE","Returned values are : " + cursor.getString(0)+
                                                     " " + cursor.getDouble(1)+
                                                     " " + cursor.getDouble(2)+
                                                     " " + cursor.getString(3));
            }
            while(cursor.moveToNext());
        }
        return allEntries;
    }

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "locations";
        public static final String COLUMN_NAME_NAME="name";
        public static final String COLUMN_NAME_TYPE="type";
        public static final String COLUMN_NAME_LATITUDE="latitude";
        public static final String COLUMN_NAME_LONGITUDE="longitude";
        public static final String COLUMN_NAME_PROVINCE="province";
        public static final String COLUMN_NAME_COMMUNE="province";
        public static final String COLUMN_NAME_DESCRIPTION="description";
    }
}
