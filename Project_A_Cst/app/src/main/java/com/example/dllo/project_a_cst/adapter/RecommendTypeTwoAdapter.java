package com.example.dllo.project_a_cst.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.bean.RecommendBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/25.
 */

public class RecommendTypeTwoAdapter extends RecyclerView.Adapter<RecommendTypeTwoAdapter.MyHolder> {
    private Context context;
    private ArrayList<RecommendBean> data;
    private int[] TYPE;

    public RecommendTypeTwoAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<RecommendBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setTYPE(int[] TYPE) {
        this.TYPE = TYPE;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.item_type_two_rv, parent, false);
        holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        int type = TYPE[position];
        switch (type) {

            // 歌单推荐
            case 1:
                Glide.with(context).load(data.get(0).getResult().getDiy().getResult().get(position).getPic())
                        .into(holder.iv);
                holder.tv.setText(data.get(0).getResult().getDiy().getResult().get(position).getTitle());

                holder.iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("歌单推荐");
                        context.sendBroadcast(intent);
                    }
                });
                break;
            // 乐播节目
            case 2:
                Glide.with(context).load(data.get(0).getResult().getRadio().getResult().get(position).getPic())
                        .into(holder.iv);
                holder.tv.setText(data.get(0).getResult().getRadio().getResult().get(position).getTitle());
                break;
            // 原创音乐
            case 3:
                Glide.with(context).load(data.get(0).getResult().getMix_9().getResult().get(position).getPic())
                        .into(holder.iv);
                holder.tv.setText(data.get(0).getResult().getMix_9().getResult().get(position).getTitle());
                break;
        }
    }

    @Override
    public int getItemCount() {
        int type = TYPE[0];
        if (type == 3){
            return 3;
        }else {
            return 6;
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_type_two_son);
            tv = (TextView) itemView.findViewById(R.id.tv_type_two_son);
        }
    }
}

//iv = (ImageView) itemView.findViewById(R.id.iv_type_two_son);
//        tv = (TextView) itemView.findViewById(R.id.tv_type_two_son);
//Picasso.with(context).load(data.get(0).getResult().getDiy().getResult().get(position).getPic())
//        .into(holder.iv);
//        holder.tv.setText(data.get(0).getResult().getDiy().getResult().get(position).getTitle());
