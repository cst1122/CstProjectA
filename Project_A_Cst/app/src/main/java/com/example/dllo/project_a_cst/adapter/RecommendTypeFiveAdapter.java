package com.example.dllo.project_a_cst.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class RecommendTypeFiveAdapter extends RecyclerView.Adapter<RecommendTypeFiveAdapter.MyHolder>{
    private Context context;
    private ArrayList<RecommendBean> data;

    public RecommendTypeFiveAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<RecommendBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.item_type_five_rv,parent,false);
        holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        // 专栏
        Glide.with(context).load(data.get(0).getResult().getMod_7().getResult().get(position).getPic())
                .into(holder.iv);
        holder.tvOne.setText(data.get(0).getResult().getMod_7().getResult().get(position).getTitle());
        holder.tvTwo.setText(data.get(0).getResult().getMod_7().getResult().get(position).getDesc());
        Log.d("RecommendTypeFiveAdapte", "专栏");
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView tvOne,tvTwo;
        public MyHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_type_five_son);
            tvOne = (TextView) itemView.findViewById(R.id.tv_type_five_son_one);
            tvTwo = (TextView) itemView.findViewById(R.id.tv_type_five_son_two);
        }
    }
}
