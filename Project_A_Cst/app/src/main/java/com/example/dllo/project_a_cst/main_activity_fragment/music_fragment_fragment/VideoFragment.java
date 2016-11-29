package com.example.dllo.project_a_cst.main_activity_fragment.music_fragment_fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.adapter.VideoAdapter;
import com.example.dllo.project_a_cst.bean.VideoBean;
import com.example.dllo.project_a_cst.main_activity_fragment.BaseFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/23.
 */

public class VideoFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ArrayList<VideoBean> data;
    private TextView tvNew,tvHeat;
    private String urlNew = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=1&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8";
    private String urlHeat = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=0&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8";
    @Override
    public int setlayout() {
        return R.layout.music_fragment_video_fragment;
    }

    @Override
    public void initView(View view) {
        recyclerView = bindView(R.id.recycler_view_video_fragment);
        tvNew = bindView(R.id.tv_new_video_fragment);
        tvHeat = bindView(R.id.tv_heat_video_fragment);
    }

    @Override
    public void initData() {
        startVolley(urlNew);
        tvNew.setOnClickListener(this);
        tvHeat.setOnClickListener(this);
        tvNew.setTextColor(getResources().getColor(R.color.mainBlue));
    }

    private void startVolley (String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                data = new ArrayList<>();
                Gson gson = new Gson();
                VideoBean bean = gson.fromJson(response,VideoBean.class);
                data.add(bean);
                VideoAdapter adapter = new VideoAdapter(getActivity());
                adapter.setData(data);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_new_video_fragment:
                recyclerView.destroyDrawingCache();
                startVolley(urlNew);
                tvNew.setTextColor(getResources().getColor(R.color.mainBlue));
                tvHeat.setTextColor(getResources().getColor(R.color.mainDark));
                break;
            case R.id.tv_heat_video_fragment:
                recyclerView.destroyDrawingCache();
                startVolley(urlHeat);
                tvHeat.setTextColor(getResources().getColor(R.color.mainBlue));
                tvNew.setTextColor(getResources().getColor(R.color.mainDark));
                break;
        }
    }
}
