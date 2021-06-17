package com.example.litenotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ModifyNoteActivity extends AppCompatActivity {

    EditText evtitle,evdecrip;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_note);

        dbManager =new DBManager(this);
        dbManager.open();

        evtitle=findViewById(R.id.addtitle2);
        evdecrip=findViewById(R.id.addnote2);

        evtitle.setText(getIntent().getStringExtra("titleview"));
        evdecrip.setText(getIntent().getStringExtra("decripview"));


        FloatingActionButton savenote=findViewById(R.id.addbtn2);

        savenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name=evtitle.getText().toString();
                final String desc=evdecrip.getText().toString();

                dbManager.insert(name,desc);

                dbManager.update(name,desc);
                Intent main = new Intent(ModifyNoteActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);


            }
        });


    }
}