package com.example.dllo.project_a_cst.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.project_a_cst.R;

/**
 * Created by dllo on 16/11/23.
 */

public class DrawerRecyclerAdapter extends RecyclerView.Adapter<DrawerRecyclerAdapter.Myholder>{
    private Context context;
    private String type;

    public DrawerRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        Myholder myholder = null;
        switch (type) {
            case "更多":
                View viewMore = LayoutInflater.from(context).inflate(R.layout.item_more_recyclerview_main, parent, false);
                myholder = new Myholder(viewMore);
                break;
            case "搜索":
                View viewSearch = LayoutInflater.from(context).inflate(R.layout.item_search_recyclerview_main,parent,false);
                myholder = new Myholder(viewSearch);
                break;

        }
        return myholder;
    }

    @Override
    public void onBindViewHolder(Myholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class Myholder extends RecyclerView.ViewHolder{

        public Myholder(View itemView) {
            super(itemView);
        }
    }
}
