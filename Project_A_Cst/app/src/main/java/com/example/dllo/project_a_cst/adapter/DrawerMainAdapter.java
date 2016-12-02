package com.example.dllo.project_a_cst.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.project_a_cst.R;

import java.util.ArrayList;

/** 用来加载不同布局的抽屉里的RecyclerView的适配器
 * Created by dllo on 16/11/30.
 */

public class DrawerMainAdapter<T> extends RecyclerView.Adapter{
    private Context context;
    private String type;
    private ArrayList<T> data;

    public DrawerMainAdapter(Context context, String type) {
        this.context = context;
        this.type = type;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (type){
            case "更多":
                View viewMore = LayoutInflater.from(context).inflate(R.layout.item_more_recyclerview_main, parent, false);
                holder = new MyFirstHolder(viewMore);
                break;
            case "搜索":
                View viewSearch = LayoutInflater.from(context).inflate(R.layout.item_search_recyclerview_main,parent,false);
                holder = new MySecondHolder(viewSearch);
            break;
            case "歌手":
                View viewSinger = LayoutInflater.from(context).inflate(R.layout.item_singer_recyclerview_main,parent,false);
                holder = new MyThirdHolder(viewSinger);
                break;
            case "歌曲分类":
                View viewSongFenLei = LayoutInflater.from(context).inflate(R.layout.item_songfenlei_recyclerview_main,parent,false);
                holder = new MyFourthHolder(viewSongFenLei);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (type){
            case "搜索":
                ((MySecondHolder)holder).textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "1122", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case "歌手":
                SingerRvAdapter adapter = new SingerRvAdapter(context);
                ((MyThirdHolder)holder).recyclerView.setAdapter(adapter);
                ((MyThirdHolder)holder).recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                break;
        }

    }

    @Override
    public int getItemCount() {
        int num = 1;
        switch (type){
            case "更多":
                num = 1;
                break;
            case "搜索":
                num = 1;
                break;
            case "歌手":
                num = 1;
                break;
            case "歌曲分类":
                num = 1;
                break;

        }
        return num;
    }

    class MyFirstHolder extends RecyclerView.ViewHolder{

        public MyFirstHolder(View itemView) {
            super(itemView);
        }
    }
    class MySecondHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MySecondHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.sousuo);
        }
    }
    class MyThirdHolder extends RecyclerView.ViewHolder{
        private RecyclerView recyclerView;
        public MyThirdHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_singer_recommend);
        }
    }
    class MyFourthHolder extends RecyclerView.ViewHolder{

        public MyFourthHolder(View itemView) {
            super(itemView);
        }
    }
}
