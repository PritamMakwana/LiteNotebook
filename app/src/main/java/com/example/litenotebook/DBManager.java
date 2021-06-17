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
        database=dbHelper.getWritableDatabase();
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


        String[] columns={DatabaseHelper.ID,DatabaseHelper.SUBJECT,DatabaseHelper.DESC};

        SQLiteDatabase database=dbHelper.getReadableDatabase();
     //   String select=" SELECT * FROM " + DatabaseHelper.TABLE_NAME;
      //  String sort=DatabaseHelper.ID+DatabaseHelper.SUBJECT;
        Cursor cursor=database.query(DatabaseHelper.TABLE_NAME,columns,
                null,null,null,null,null);
        // Cursor cursor=database.rawQuery(DatabaseHelper.DB_NAME,columns);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
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
