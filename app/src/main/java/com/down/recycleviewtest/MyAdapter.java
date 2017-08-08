package com.down.recycleviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.LinkedList;

import static com.down.recycleviewtest.R.id.textView1;

/**
 * Created by Wrap me 09 on 08-08-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myView1> {
    Context context;
    LinkedList<bean> linkedList;

    public MyAdapter(Context context, LinkedList<bean> linkedList) {
        this.context = context;
        this.linkedList = linkedList;
    }

    @Override
    public myView1 onCreateViewHolder(ViewGroup parent, int viewType) {
       // View view1=View.inflate(context,R.layout.recycleview_items, parent);
       // myView1 myView1=;
      //  myView2 myView2=new myView2();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_items, parent, false);
        return new myView1(itemView);
    }


    @Override
    public void onBindViewHolder(MyAdapter.myView1 holder, int position) {

        holder.textView1.setText(linkedList.get(position).getText1());
        holder.textView2.setText(linkedList.get(position).getText2());
        Glide.with(context)
                .load(linkedList.get(position).getImg())
                .override(200,100)
                .into(holder.imageView);
    }

    /*@Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

holder.textView1.

    }*/

    @Override
    public int getItemCount() {
        return linkedList.size();
    }



public class myView1 extends RecyclerView.ViewHolder{
    ImageView imageView;
    public TextView textView1,textView2;
    public myView1(View itemView) {
        super(itemView);
        imageView=(ImageView)itemView.findViewById(R.id.imageView);
        textView1=(TextView)itemView.findViewById(R.id.textView1);
        textView2=(TextView)itemView.findViewById(R.id.textView2);
    }


}

    public class myView2 extends RecyclerView.ViewHolder{

        public myView2(View itemView) {
            super(itemView);
        }


    }








}
