package com.example.litenotebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    Context context;
    //table name
    public static final String TABLE_NAME = "NOTEBOOK";
    //Table colum
    public static final String ID = "_id";
    public static final String SUBJECT = "subject";
    public static final String DESC = "decription";
    //table database
    static final String DB_NAME = "DATA_OF_NOTEBOOK";
    //table version
    static final int DB_VERSION = 1;
    //creating table Query
//    private static final String CREATE_TABLE= "create table " + TABLE_NAME + "(" + ID
//            + "INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT + " TEXT, " + DESC + " TEXT); ";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //excuting the QUERY
        String query = "CREATE TABLE " + TABLE_NAME + " (" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT + " TEXT, " + DESC + " TEXT); ";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    //add
    public void insertadd(String name, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUBJECT, name);
        contentValues.put(DESC, desc);

        long resultValue = db.insert(TABLE_NAME, null, contentValues);

        if (resultValue == -1) {
            Toast.makeText(context, "data not added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Data added successfully", Toast.LENGTH_LONG).show();

        }
    }


    Cursor readData() {
        String queary = " SELECT * FRoM " + TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(queary, null);
        }
        return cursor;
    }

    //updete


    void upadeteNote(String name, String desc, String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUBJECT, name);
        contentValues.put(DESC, desc);
        long result = database.update(TABLE_NAME, contentValues, "_id=?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Failed to update", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, " Update done", Toast.LENGTH_LONG).show();
        }


    }


    public void deletedata(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        long result = database.delete(TABLE_NAME, "_id=?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "delete is FAiled", Toast.LENGTH_LONG).show();
        } else {
            // Toast.makeText(context,"delete is Done",Toast.LENGTH_LONG).show();
        }

    }
}