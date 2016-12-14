package com.example.dllo.project_a_cst.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.my_database.MyMusicPerson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/13.
 */

public class ListPopAdapter extends RecyclerView.Adapter<ListPopAdapter.MyPopHolder>{
    private Context mContext;
    private ArrayList<MyMusicPerson> data;

    public ListPopAdapter(Context context) {
        mContext = context;
    }

    public void setData(ArrayList<MyMusicPerson> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyPopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyPopHolder holder = null;
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_pop,parent,false);
        holder = new MyPopHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyPopHolder holder, final int position) {
        holder.tvName.setText(data.get(position).getTitle());
        holder.tvSinger.setText(data.get(position).getArtist());
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                removeItem(position);
            }
        });
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyPopHolder extends RecyclerView.ViewHolder{
        private TextView tvName,tvSinger;
        private ImageView mImageView;
        private RelativeLayout rl;



        public MyPopHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_list_pop_name);
            tvSinger = (TextView) itemView.findViewById(R.id.tv_list_pop_singer);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_list_pop_delete);
            rl = (RelativeLayout) itemView.findViewById(R.id.Rl_list_pop);
        }
    }

    public void removeItem(int position){
        data.remove(position);
        notifyDataSetChanged();
        notifyItemRemoved(position);
    }
}
