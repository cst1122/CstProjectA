package com.example.dllo.project_a_cst.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.my_database.MyPerson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/14.
 */

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.MyLikeHolder>{
    private ArrayList<MyPerson> data;
    private Context mContext;

    public LikeAdapter(Context context) {
        mContext = context;
    }

    public void setData(ArrayList<MyPerson> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyLikeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyLikeHolder holder = null;
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mine,parent,false);
        holder = new MyLikeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyLikeHolder holder, final int position) {
        holder.tvName.setText(data.get(position).getMusicName());
        holder.tvSinger.setText(data.get(position).getSinger());
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent("播放本地音乐");
//                ArrayList<Mp3Info> Musicdata = new ArrayList<>();
//                for (int i = 0; i < data.size(); i++) {
//                   Mp3Info bean = new Mp3Info();
//                    bean.setSize(0);
//                    bean.setDuration(0);
//                    bean.setAlbumId(0);
//                    bean.setAlbum("");
//                    bean.setTitle(data.get(i).getMusicName());
//                    bean.setId(0);
//                    bean.setArtist(data.get(i).getSinger());
//                    bean.setUrl(data.get(i).getMusicUrl());
//                    Musicdata.add(bean);
//                }
//                intent.putParcelableArrayListExtra("本地音乐",Musicdata);
//                intent.putExtra("第几首",position);
//                mContext.sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyLikeHolder extends RecyclerView.ViewHolder{
        private RelativeLayout rl;
        private TextView tvName,tvSinger;
        public MyLikeHolder(View itemView) {
            super(itemView);
            rl = (RelativeLayout) itemView.findViewById(R.id.rl_item_mine);
            tvName = (TextView) itemView.findViewById(R.id.tv_item_mine_name);
            tvSinger = (TextView) itemView.findViewById(R.id.tv_item_mine_singer);
        }
    }
}
