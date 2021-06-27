package com.example.litenotebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;
    public DBManager(Context c) {
        context = c;
    }

    //open
    public DBManager open()  throws SQLException{
        dbHelper=new DatabaseHelper(context);
//        database=dbHelper.getWritableDatabase();
        return this;
    }
    //close
    public void close(){
         dbHelper.close();
    }
    //insert
    public void insert(String name ,String desc){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT,name);
        contentValues.put(DatabaseHelper.DESC,desc);
        database.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
    }

    //CURSOR
    public Cursor fetch(){

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String selection = "SELECT * FROM " + DatabaseHelper.TABLE_NAME;
        Cursor cursor1 = database.rawQuery(selection, null);

        if (cursor1 != null) {
            cursor1.moveToFirst();
        }

        return cursor1;
    }

    //updete
    public void update(String name,String desc){
          ContentValues contentValues=new ContentValues();
          contentValues.put(DatabaseHelper.SUBJECT,name);
          contentValues.put(DatabaseHelper.DESC,desc);

          database.update(DatabaseHelper.TABLE_NAME,contentValues,DatabaseHelper.ID + "=" ,null);
    }
    //delete
    public void delete(long id){
        database.delete(DatabaseHelper.TABLE_NAME,DatabaseHelper.ID+ "=",null);
    }

}
