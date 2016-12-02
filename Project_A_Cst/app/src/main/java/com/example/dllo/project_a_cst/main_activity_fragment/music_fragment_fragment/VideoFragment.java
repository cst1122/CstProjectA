package com.example.dllo.project_a_cst.main_activity_fragment.music_fragment_fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.adapter.VideoAdapter;
import com.example.dllo.project_a_cst.bean.VideoBean;
import com.example.dllo.project_a_cst.else_class.MyNetListener;
import com.example.dllo.project_a_cst.else_class.NetHelper;
import com.example.dllo.project_a_cst.main_activity_fragment.BaseFragment;

import java.util.ArrayList;

import static com.example.dllo.project_a_cst.my_class.MyConstants.VIDEO_FRAGMENT_HEAT_URL;
import static com.example.dllo.project_a_cst.my_class.MyConstants.VIDEO_FRAGMENT_NEW_URL;

/**
 * Created by dllo on 16/11/23.
 */

public class VideoFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ArrayList<VideoBean> data;
    private TextView tvNew,tvHeat;
    private String urlNew = VIDEO_FRAGMENT_NEW_URL;
    private String urlHeat = VIDEO_FRAGMENT_HEAT_URL;
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

        NetHelper.myRequest(url, new MyNetListener<VideoBean>() {
            @Override
            public void successListener(VideoBean response) {
                data = new ArrayList<VideoBean>();
                data.add(response);
                VideoAdapter adapter = new VideoAdapter(getActivity());
                adapter.setData(data);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        },VideoBean.class);
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
