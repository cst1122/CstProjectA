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
import com.example.dllo.project_a_cst.bean.SongListBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/26.
 */

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.MyHolder>{
    private Context context;
    private ArrayList<SongListBean>data;

    public SongListAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<SongListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.item_song_list_fragment,parent,false);
        holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Glide.with(context).load(data.get(0).getDiyInfo().get(position).getList_pic_large()).into(holder.iv);
        holder.tvNum.setText(data.get(0).getDiyInfo().get(position).getListen_num()+"");
        holder.tvOne.setText(data.get(0).getDiyInfo().get(position).getTitle());
        holder.tvTwo.setText("by  "+data.get(0).getDiyInfo().get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return data.get(0).getDiyInfo().size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView tvNum,tvOne,tvTwo;
        public MyHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_song_list_fragment);
            tvNum = (TextView) itemView.findViewById(R.id.tv_song_list_fragment_num);
            tvOne = (TextView) itemView.findViewById(R.id.tv_song_list_fragment_one);
            tvTwo = (TextView) itemView.findViewById(R.id.tv_song_list_fragment_two);
        }
    }
}
