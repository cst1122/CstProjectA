package com.example.dllo.project_a_cst.else_class;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by dllo on 16/11/26.
 */

public class MyViewHolder extends RecyclerView.ViewHolder{
    private static  View mView;
    private SparseArray<View> sparseArray;
    private Context mContext;
    public MyViewHolder(View itemView, Context context) {
        super(itemView);
        mView = itemView;
        mContext = context;
        sparseArray = new SparseArray<>();
    }

    public static MyViewHolder createViewHolder
            (Context context, ViewGroup parent,int layoutId){
        mView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(mView,context);
        return viewHolder;
    }
    public <T extends View> T getView (int layoutId) {
        View view = sparseArray.get(layoutId);
        if (view == null){
            view = mView.findViewById(layoutId);
            sparseArray.put(layoutId,view);
        }
        return (T) view;
    }
    public MyViewHolder setSongListText (int id,String s) {
        TextView tv = getView(id);
        tv.setText(s);
        return this;
    }
    public MyViewHolder setSongListImage (int id,String url) {
        ImageView imageView = getView(id);
        Glide.with(mContext).load(url).into(imageView);
        return this;
    }
}
