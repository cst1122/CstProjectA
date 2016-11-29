package com.example.dllo.project_a_cst.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.dllo.project_a_cst.else_class.MyViewHolder;

import java.util.List;

/**
 * Created by dllo on 16/11/26.
 */

public abstract class MyAdapter<T> extends RecyclerView.Adapter<MyViewHolder>{
    private List<T> data;
    private Context context;
    private int layoutId;

    public MyAdapter(Context context, int layoutId) {
        this.context = context;
        this.layoutId = layoutId;
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = MyViewHolder.createViewHolder(context,parent,layoutId);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        onBindData(holder,data.get(position));
    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    public abstract MyViewHolder onBindData(MyViewHolder holder, T t);
}
