package com.example.dllo.project_a_cst.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.bean.MyMusicListBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/2.
 */

public class MyMusicListAdapter extends RecyclerView.Adapter<MyMusicListAdapter.MyHolder>{
    private ArrayList<MyMusicListBean> data;
    private Context mContext;

    public MyMusicListAdapter(Context context) {
        mContext = context;
    }

    public void setData(ArrayList<MyMusicListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = null;
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_my_musci_list_fragment,parent,false);
        holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Glide.with(mContext).load(data.get(0).getSong_list().get(position).getPic_big()).into(holder.iv);
        holder.tvOne.setText(data.get(0).getSong_list().get(position).getTitle());
        holder.tvTwo.setText(data.get(0).getSong_list().get(position).getAuthor());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.get(0).getSong_list().size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private LinearLayout ll;
        private ImageView iv;
        private TextView tvOne,tvTwo;
        public MyHolder(View itemView) {
            super(itemView);
            ll = (LinearLayout) itemView.findViewById(R.id.ll_my_music_list_item);
            iv = (ImageView) itemView.findViewById(R.id.iv_my_music_list_item);
            tvOne = (TextView) itemView.findViewById(R.id.tv_my_music_list_song_name);
            tvTwo = (TextView) itemView.findViewById(R.id.tv_my_music_list_singer);
        }
    }
}
