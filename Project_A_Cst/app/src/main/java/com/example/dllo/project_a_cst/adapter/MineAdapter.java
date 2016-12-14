package com.example.dllo.project_a_cst.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.my_class.Mp3Info;
import com.example.dllo.project_a_cst.my_class.MusicUtil;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/14.
 */

public class MineAdapter extends RecyclerView.Adapter<MineAdapter.MyNewHolder>{
    private ArrayList<Mp3Info> data;
    private Context mContext;

    public MineAdapter(Context context) {
        mContext = context;
    }

    public void setData(ArrayList<Mp3Info> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyNewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyNewHolder holder = null;
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mine,parent,false);
        holder = new MyNewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyNewHolder holder, final int position) {
        holder.tvName.setText(data.get(position).getTitle());
        holder.tvSinger.setText(data.get(position).getArtist());
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("播放本地音乐");
                ArrayList<Mp3Info> data = (ArrayList<Mp3Info>) MusicUtil.getMp3Infos(mContext);
                intent.putParcelableArrayListExtra("本地音乐",data);
                intent.putExtra("第几首",position);
                mContext.sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyNewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout rl;
        private TextView tvName,tvSinger;
        public MyNewHolder(View itemView) {
            super(itemView);
            rl = (RelativeLayout) itemView.findViewById(R.id.rl_item_mine);
            tvName = (TextView) itemView.findViewById(R.id.tv_item_mine_name);
            tvSinger = (TextView) itemView.findViewById(R.id.tv_item_mine_singer);
        }
    }
}
