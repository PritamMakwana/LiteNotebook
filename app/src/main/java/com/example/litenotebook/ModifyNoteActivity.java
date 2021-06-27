package com.example.litenotebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ModifyNoteActivity extends AppCompatActivity {

    EditText evtitle,evdecrip;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_note);

        back=findViewById(R.id.backimg);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back=new Intent();
                finish();
            }
        });
//
//        delete=findViewById(R.id.imgdelete);
//
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"This note Delete",Toast.LENGTH_LONG).show();
//                DatabaseHelper db=new DatabaseHelper(ModifyNoteActivity.this);
//              //  db.deletedata(list.remove(getIntent()));
//            }
//        });

//        dbManager =new DBManager(this);
//        dbManager.open();
//        Cursor cursor=dbManager.fetch();

        evtitle=findViewById(R.id.addtitle2);
        evdecrip=findViewById(R.id.addnote2);

        FloatingActionButton savenote=findViewById(R.id.addbtn2);
        savenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(evtitle.getText().toString()) && !TextUtils.isEmpty(evdecrip.getText().toString()))
                {
                    DatabaseHelper db=new  DatabaseHelper(ModifyNoteActivity.this);
                    db.insertadd(evtitle.getText().toString(),evdecrip.getText().toString());
                    Intent intent= new Intent(ModifyNoteActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(ModifyNoteActivity.this,"BOth titile and note Required",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}