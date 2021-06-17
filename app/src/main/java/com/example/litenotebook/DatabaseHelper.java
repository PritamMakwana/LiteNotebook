package com.example.litenotebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    //table name
    public static final  String TABLE_NAME="NOTEBOOK";
    //Table colum
    public static final String ID="_id";
    public static final String SUBJECT="subject";
    public static final String DESC="decription";
    //table database
     static final String DB_NAME="DATA_OF_NOTEBOOK";
     //table version
    static final int DB_VERSION=1;
    //creating table Query
    private static final String CREATE_TABLE= "create table " + TABLE_NAME + "(" + ID
            + "INTEGER PRIMARY KEY , " + SUBJECT + " TEXT NOT NULL, "
            + DESC + "TEXT);";

    public DatabaseHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //excuting the QUERY
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
