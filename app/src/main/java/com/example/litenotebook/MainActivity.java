package com.example.litenotebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnListnotebook {

    private DBManager dbManager;
  //  private Cursor cursor;


     private MyAdapter myAdapter;
      ArrayList<note> list;
      RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView=findViewById(R.id.recyclerView);

        final String[] from= new String[] {
                DatabaseHelper.ID,DatabaseHelper.SUBJECT,DatabaseHelper.DESC
        };
        final  int [] to =new int[]{/*R.id.id,*/R.id.addnote1,R.id.descri1};

       list=new ArrayList<>();
      list.add(new note("project","my homework im not working,my homework im not working,my homework im not workingmy homework im not workingmy homework im not workingmy homework im not workingmy homework im not workingmy homework im not workingmy homework im not workingmy homework im not working"));
      list.add(new note("project","my homework im not working,my homework im not working,my homework im not workingmy homework im not workingmy homework im not workingmy homework im not workingmy homework im not workingmy homework im not workingmy homework im not workingmy homework im not working"));
      list.add(new note("project","my homework im not working,my homework im not working,my homework im not workingmy homework im not workingmy homework im not workingmy homework im not workingmy homework im not workingmy homework im not workingmy homework im not workingmy homework im not working"));
      list.add(new note("project","my homework im not working,my homework im not working,my homework im not workingmy homework im not workingmy homework im not workingmy homework im not workingmy homework im not workingmy homework im not workingmy homework im not workingmy homework im not working"));

        myAdapter=new MyAdapter(list,this, this,from,to);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      recyclerView.setHasFixedSize(true);
      recyclerView.setAdapter(myAdapter);
      myAdapter.notifyDataSetChanged();


      dbManager=new DBManager(this);
      dbManager.open();
      //Cursor cursor;
      Cursor cursor=dbManager.fetch();


      //add button
        FloatingActionButton plus=findViewById(R.id.addnote1);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"add note",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,ModifyNoteActivity.class);
                intent.setFlags(intent.getFlags());
                startActivity(intent);
            }
        });

    }

    @Override
    public void OnListNoteClick(String atitle, String adcrip) {
        Toast.makeText(this,atitle,Toast.LENGTH_SHORT).show();

//        TextView TitleText=findViewById(R.id.title1);
//        TextView DecriptText=findViewById(R.id.descri1);
//
//        String title1=TitleText.getText().toString();
//        String decrip1=DecriptText.getText().toString();

        Intent intent=new Intent(MainActivity.this,ModifyNoteActivity.class);
        intent.putExtra("titleview",atitle);
        intent.putExtra("decripview",adcrip);
        startActivity(intent);

    }
}
