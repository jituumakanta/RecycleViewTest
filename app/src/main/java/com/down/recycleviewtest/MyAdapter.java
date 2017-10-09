package com.down.recycleviewtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.down.recycleviewtest.R.id.progressBar;
import static java.lang.Thread.sleep;

/**
 * Created by Wrap me 09 on 08-08-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    LinkedList<bean> linkedList;
    RecyclerView recyclerView;

    public MyAdapter(Context context, LinkedList<bean> linkedList, RecyclerView recyclerView) {
        this.context = context;
        this.linkedList = linkedList;
        this.recyclerView = recyclerView;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View v;
        // Toast.makeText(context, "viewType: " + viewType, Toast.LENGTH_LONG).show();
        System.out.println("viewType: " + viewType);
        if (viewType == 0) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_items1, parent, false);
            viewHolder = new myView1(v);
        } else if (viewType == 1) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_items2, parent, false);
            viewHolder = new myView2(v);
        }
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof myView1) {
            bean bean = linkedList.get(position);
            ((myView1) holder).textView1.setText(bean.getText1());
            ((myView1) holder).textView2.setText(bean.getText2());
            Glide.with(context)
                    .load(linkedList.get(position).getImg())
                    .override(200, 100)
                    .into(((myView1) holder).imageView);
        } else if (holder instanceof myView2) {


            Thread myThread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {


                    }
                }
            };
            myThread.start();
        }
    }


    @Override
    public int getItemCount() {
        return linkedList == null ? 0 : linkedList.size();
    }

    @Override
    public int getItemViewType(int position) {

        return linkedList.get(position) == null ? 1 : 0;

    }


    public class myView1 extends RecyclerView.ViewHolder {
        ImageView imageView;
        public TextView textView1, textView2;

        public myView1(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView1 = (TextView) itemView.findViewById(R.id.textView1);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);
        }


    }

    public class myView2 extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public myView2(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }

    }


}
