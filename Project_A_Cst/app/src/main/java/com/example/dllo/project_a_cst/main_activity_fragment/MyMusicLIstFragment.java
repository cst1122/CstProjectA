package com.example.dllo.project_a_cst.main_activity_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.adapter.MyMusicListAdapter;
import com.example.dllo.project_a_cst.bean.MyMusicListBean;
import com.example.dllo.project_a_cst.else_class.MyNetListener;
import com.example.dllo.project_a_cst.else_class.NetHelper;

import java.util.ArrayList;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by dllo on 16/12/1.
 */

public class MyMusicLIstFragment extends SwipeBackFragment {

    private ArrayList<MyMusicListBean> data;
    private RecyclerView recyclerView;
    private ImageView ivBig,ivReturn,ivFenxiang;
    private String url,imageUrl;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_music_list,container,false);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_my_music_list);
        ivBig = (ImageView) view.findViewById(R.id.fragment_menu_list_detail_top_bg);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Glide.with(getActivity()).load(imageUrl).into(ivBig);
        Log.d("MyMusicLIstFragment", "网址" + url);
        startVolley(url);
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("MyMusicLIstFragment", "onResume");

    }



    public void startVolley (String url) {
        NetHelper.myRequest(url, new MyNetListener<MyMusicListBean>() {
            @Override
            public void successListener(MyMusicListBean response) {
                data = new ArrayList<>();
                data.add(response);
                MyMusicListAdapter adapter = new MyMusicListAdapter(getActivity());
                adapter.setData(data);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        },MyMusicListBean.class);
    }

}
