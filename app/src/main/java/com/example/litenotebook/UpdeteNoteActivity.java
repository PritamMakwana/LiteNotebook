package com.example.litenotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UpdeteNoteActivity extends AppCompatActivity {
    ImageView back1, delete1;
    EditText sub, des;
    String id;
    private int count = 0;
    ArrayList<note> list;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updete_note);

        db = new DatabaseHelper(UpdeteNoteActivity.this);

        back1 = findViewById(R.id.backimg2);

        back1.setOnClickListener(v -> {
            Intent back = new Intent();
            finish();
        });

        delete1 = findViewById(R.id.imgdelete2);


        delete1.setOnClickListener(v -> {
            DatabaseHelper db = new DatabaseHelper(UpdeteNoteActivity.this);
            db.deletedata(id);
            Intent intent = new Intent(UpdeteNoteActivity.this, MainActivity.class);
//            Toast.makeText(getApplicationContext(), "This note Delete", Toast.LENGTH_LONG).show();
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
            finish();

        });


        sub = findViewById(R.id.addtitle22);
        des = findViewById(R.id.addnote22);
        id = getIntent().getStringExtra("id0");
        sub.setText(getIntent().getStringExtra("tv0"));
        des.setText(getIntent().getStringExtra("dv0"));


        FloatingActionButton savenote1 = findViewById(R.id.addbtn22);
        savenote1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(sub.getText().toString()) && !TextUtils.isEmpty(des.getText().toString())) {
                    db.insertadd(sub.getText().toString(), des.getText().toString());
                    finish();
                } else if (!list.add(new note(sub.getText().toString(), des.getText().toString(), id)) &&
                        TextUtils.isEmpty(sub.getText().toString()) && TextUtils.isEmpty(des.getText().toString())) {
                    db.upadeteNote(sub.getText().toString(), des.getText().toString(), id);
                    finish();
                } else {
                    Toast.makeText(UpdeteNoteActivity.this, "B0th title and note Required", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

}