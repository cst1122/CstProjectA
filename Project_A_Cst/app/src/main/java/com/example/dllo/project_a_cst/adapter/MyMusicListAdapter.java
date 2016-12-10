package com.example.dllo.project_a_cst.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import static com.example.dllo.project_a_cst.my_class.MyConstants.SONG_URL;

/**
 * Created by dllo on 16/12/2.
 */

public class MyMusicListAdapter extends RecyclerView.Adapter<MyMusicListAdapter.MyHolder>{
    private ArrayList<MyMusicListBean> data;
    private Context mContext;
    private ArrayList<String> songUrl = new ArrayList<>();

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
    public void onBindViewHolder(MyHolder holder, final int position) {
        Glide.with(mContext).load(data.get(0).getSong_list().get(position).getPic_big()).into(holder.iv);
        holder.tvOne.setText(data.get(0).getSong_list().get(position).getTitle());
        holder.tvTwo.setText(data.get(0).getSong_list().get(position).getAuthor());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < data.get(0).getSong_list().size(); i++) {
                    String url =  SONG_URL+data.get(0).getSong_list().get(i).getSong_id();
                    songUrl.add(url);
                }
                Log.d("MyMusicListAdapter", data.get(0).getBillboard().getBillboard_type()+"#####");
                Log.d("MyMusicListAdapter", songUrl.get(0)+"#%!$");
                Intent intent = new Intent("歌曲地址集合");
                intent.putStringArrayListExtra("歌曲地址",songUrl);
                intent.putExtra("第几首歌",position);
                intent.putExtra("歌单",data.get(0).getBillboard().getBillboard_type());
                mContext.sendBroadcast(intent);
//                RequestQueue requestQueue = Volley.newRequestQueue(mContext);
//                StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                       response =  response.substring(1,response.length()-2);
//                        songUrl = new ArrayList<>();
//                        Gson gson = new Gson();
//                        SongUrlBean bean = gson.fromJson(response,SongUrlBean.class);
//                        songUrl.add(bean);
//                        Intent intent = new Intent("歌曲地址");
//                        intent.putExtra("网址",songUrl.get(0).getBitrate().getFile_link());
//                        intent.putExtra("歌曲名",songUrl.get(0).getSonginfo().getTitle());
//                        intent.putExtra("歌手",songUrl.get(0).getSonginfo().getAuthor());
//                        intent.putExtra("歌曲时间",500000l);
//                        mContext.sendBroadcast(intent);
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//                requestQueue.add(stringRequest);
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
