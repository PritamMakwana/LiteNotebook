package com.example.litenotebook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private  ArrayList<note> list;
    private Context context;
    // OnListnotebook onListnotebook;
     MyAdapter myAdapter;
//     String[] from;
//     int [] to;



    public MyAdapter(ArrayList<note> list, Context context/*,OnListnotebook onListnotebook/*,String[] from, int[] to*/) {
        this.list = list;
        this.context = context;
      //  this.onListnotebook=onListnotebook;
//        this.from=from;
//        this.to=to;



    }

    public void filterList(ArrayList<note> filterdNames) {
        this.list = filterdNames;
        notifyDataSetChanged();
    }

//    public MyAdapter(ArrayList<note> list) {
//        this.list=list;
//    }


//    public interface OnListnotebook{
//       void OnListNoteClick(String atitle,String adcrip);
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View liteitem=layoutInflater.inflate(R.layout.list_itam,parent,false);
        ViewHolder viewHolder=new ViewHolder(liteitem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.decription.setText(list.get(position).getDecript());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,(position)+" this "+list.get(position).getTitle(),Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
      //          onListnotebook.OnListNoteClick(list.get(position).getTitle(),list.get(position).getDecript());

                Intent intent=new Intent(context,UpdeteNoteActivity.class);
                intent.putExtra("tv0",list.get(position).getTitle());
                intent.putExtra("dv0",list.get(position).getDecript());
                intent.putExtra("id0",list.get(position).get_id());
                context.startActivity(intent);

            }
        });

   }

    @Override
    public int getItemCount() {
        return list.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{
        public ConstraintLayout constraintLayout;
        public TextView title,decription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout=itemView.findViewById(R.id.listitam1);
            title=itemView.findViewById(R.id.title1);
            decription=itemView.findViewById(R.id.descri1);

        }
    }

//    public  void removelist(int position)
//    {
//
//        list.remove(position);
//  //      myAdapter.notifyDataSetChanged();
//      //  myAdapter.notifyItemChanged(position);
//    }
//
//
//
////    public void restoreItem(note item,int position){
////        list.add(position,item);
////        notifyItemChanged(position);
////    }

}
