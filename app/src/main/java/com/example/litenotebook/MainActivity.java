package com.example.litenotebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity /*implements MyAdapter.OnListnotebook*/ {
    DatabaseHelper databaseHelper;
     private MyAdapter myAdapter;
      ArrayList<note> list;
      RecyclerView recyclerView;
      TextView t1,d1;
      EditText evtitle1,evdecrip1;
     EditText searchtext;
     ImageView imageView1;
     int mode[]={R.drawable.dark,R.drawable.sun};
     int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //__________________dark mode
        imageView1=findViewById(R.id.modeimg);
        if(i==0)
        imageView1.setImageResource(mode[i]);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView1.setImageResource(mode[i]);
                if(i==0) {
                   i++;
               }
               else if(i==1){
                     i--;


                }
            }
        });
        //________________edittext to serch note__________________

        searchtext=findViewById(R.id.seredit);

        searchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());

            }

            private void filter(String toString) {
                ArrayList<note> filterdNames = new ArrayList<>();


                for (note s : list) {
                    //if the existing elements contains the search input
                    if (s.getDecript().toLowerCase().contains(toString.toLowerCase())||
                         s.getDecript().toUpperCase().contains(toString.toUpperCase())||
                          s.getTitle().toLowerCase().contains(toString.toLowerCase())||
                            s.getTitle().toUpperCase().contains(toString.toUpperCase())) {
                        //adding the element to filtered list
                        filterdNames.add(s);
                    }
                }

                //calling a method of the adapter class and passing the filtered list
                myAdapter.filterList(filterdNames);
            }
        });

        t1=findViewById(R.id.title1);
        d1=findViewById(R.id.descri1);

        evtitle1=findViewById(R.id.addtitle2);
        evdecrip1=findViewById(R.id.addnote2);

        recyclerView=findViewById(R.id.recyclerView);

//        final String[] from= new String[] {
//                DatabaseHelper.SUBJECT,DatabaseHelper.DESC
//        };
//        final  int [] to =new int[]{/*R.id.id,*/R.id.addnote1,R.id.descri1};
//        final String name=evtitle1.getText().toString();
//        final String desc=evdecrip1.getText().toString();
       list=new ArrayList<>();
//     list.add(new note("0","Facebook","Zuckerberg built a website called Facemash in 2003 while " ));
//     list.add(new note("1","WhatsApp","The WhatsApp brand is more than just a name."));
      databaseHelper=new DatabaseHelper(this);
      allNotefetch();
      myAdapter=new MyAdapter(list,this);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      recyclerView.setHasFixedSize(true);
      recyclerView.setAdapter(myAdapter);
      myAdapter.notifyDataSetChanged();

//      dbManager=new DBManager(this);
//      dbManager.open();
//      //Cursor cursor;
//      cursor=dbManager.fetch();
   //      dbManager.insert(DatabaseHelper.SUBJECT,DatabaseHelper.DESC);
      //add button
        FloatingActionButton plus=findViewById(R.id.addnote1);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"add note",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,UpdeteNoteActivity.class);
                startActivity(intent);

            }
        });

    }

    private void allNotefetch() {
        Cursor cursor=databaseHelper.readData();

        if(cursor.getCount()==0){
            Toast.makeText(this,"no data to show",Toast.LENGTH_SHORT).show();
        }

        else
            {
                while (cursor.moveToNext()) {

                    list.add( new note(cursor.getString(0), cursor.getString(1),cursor.getString(2)));
                }

                }
    }

//    @Override
//    public void OnListNoteClick(String atitle, String adcrip) {
//        Toast.makeText(this,atitle,Toast.LENGTH_SHORT).show();
//        Intent intent=new Intent(MainActivity.this,ModifyNoteActivity.class);
//        intent.putExtra("titleview",atitle);
//        intent.putExtra("decripview",adcrip);
//        startActivity(intent);
//
//    }
}
