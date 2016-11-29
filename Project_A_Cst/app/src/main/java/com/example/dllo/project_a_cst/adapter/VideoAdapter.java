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
import com.example.dllo.project_a_cst.bean.VideoBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/28.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyHolder>{
    private ArrayList<VideoBean> data;
    private Context context;

    public VideoAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<VideoBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.item_video_fragment,parent,false);
        holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Glide.with(context).load(data.get(0).getResult().getMv_list().get(position).getThumbnail2()).into(holder.iv);
        holder.tvOne.setText(data.get(0).getResult().getMv_list().get(position).getTitle());
        holder.tvTwo.setText(data.get(0).getResult().getMv_list().get(position).getArtist());
    }

    @Override
    public int getItemCount() {
        return data.get(0).getResult().getMv_list().size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView tvOne,tvTwo;
        public MyHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_video_item);
            tvOne = (TextView) itemView.findViewById(R.id.tv_one_video_item);
            tvTwo = (TextView) itemView.findViewById(R.id.tv_two_video_item);
        }
    }
}
