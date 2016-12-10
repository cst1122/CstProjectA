package com.example.dllo.project_a_cst.adapter;

import android.content.Context;
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

public class RecommendTypeThreeAdapter extends RecyclerView.Adapter<RecommendTypeThreeAdapter.MyHolder>{
    private Context context;
    private ArrayList<RecommendBean> data;
    private int[] TYPE;

    public RecommendTypeThreeAdapter(Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_type_three_rv,parent,false);
        holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        int type = TYPE[position];
        switch (type){
            // 新碟上架
            case 1:
                Glide.with(context).load(data.get(0).getResult().getMix_1().getResult().get(position).getPic())
                        .into(holder.iv);
                holder.tvOne.setText(data.get(0).getResult().getMix_1().getResult().get(position).getTitle());
                holder.tvTwo.setText(data.get(0).getResult().getMix_1().getResult().get(position).getAuthor());
                break;
            // 最热MV推荐
            case 2:
                Glide.with(context).load(data.get(0).getResult().getMix_5().getResult().get(position).getPic())
                        .into(holder.iv);
                holder.tvOne.setText(data.get(0).getResult().getMix_5().getResult().get(position).getTitle());
                holder.tvTwo.setText(data.get(0).getResult().getMix_5().getResult().get(position).getAuthor());
                break;
            // 热销专辑
            case 3:
                Glide.with(context).load(data.get(0).getResult().getMix_22().getResult().get(position).getPic())
                        .into(holder.iv);
                holder.tvOne.setText(data.get(0).getResult().getMix_22().getResult().get(position).getTitle());
                holder.tvTwo.setText(data.get(0).getResult().getMix_22().getResult().get(position).getAuthor());
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
        private TextView tvOne,tvTwo;
        public MyHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_type_three_son);
            tvOne = (TextView) itemView.findViewById(R.id.tv_type_three_son_one);
            tvTwo = (TextView) itemView.findViewById(R.id.tv_type_three_son_two);
        }
    }
}
