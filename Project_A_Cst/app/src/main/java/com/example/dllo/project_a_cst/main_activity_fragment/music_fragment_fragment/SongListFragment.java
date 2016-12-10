package com.example.dllo.project_a_cst.main_activity_fragment.music_fragment_fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.adapter.SongListAdapter;
import com.example.dllo.project_a_cst.bean.SongListBean;
import com.example.dllo.project_a_cst.else_class.MyNetListener;
import com.example.dllo.project_a_cst.else_class.NetHelper;
import com.example.dllo.project_a_cst.main_activity_fragment.BaseFragment;

import java.util.ArrayList;

import static com.example.dllo.project_a_cst.my_class.MyConstants.SONG_LIST_FRAGMENT_HEAT_URL;
import static com.example.dllo.project_a_cst.my_class.MyConstants.SONG_LIST_FRAGMENT_NEW_URL;

/**
 * Created by dllo on 16/11/23.
 */

public class SongListFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ArrayList<SongListBean> data;
    private String HeatUrl = SONG_LIST_FRAGMENT_HEAT_URL;
    private String NewUrl = SONG_LIST_FRAGMENT_NEW_URL;
    private TextView tvNew,tvHeat;
    private int type = 1;


    @Override
    public int setlayout() {
        return R.layout.music_fragment_songlist_fragment;
    }

    @Override
    public void initView(View view) {
        recyclerView = bindView(R.id.recycler_view_song_list_fragment);
        tvNew = bindView(R.id.tv_new_song_list_fragment);
        tvHeat = bindView(R.id.tv_heat_song_list_fragment);
    }

    @Override
    public void initData() {
        tvHeat.setOnClickListener(this);
        tvNew.setOnClickListener(this);
        tvHeat.setTextColor(getResources().getColor(R.color.mainBlue));
        startVolley(HeatUrl);
        type = 1;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                switch (type) {
//                    case 1:
////                        int i = 2;
////                        String NewUrl = SONG_LIST_NEW_HEAT_URL_LEFT + i++ + SONG_LIST_NEW_HEAT_URL_RIGHT;
////                        recyclerView.destroyDrawingCache();
////                        startVolley(NewUrl);
//                        break;
//                    case 2:
//                        break;
             //   }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_heat_song_list_fragment:
                recyclerView.destroyDrawingCache();
                startVolley(HeatUrl);
                tvHeat.setTextColor(getResources().getColor(R.color.mainBlue));
                tvNew.setTextColor(getResources().getColor(R.color.mainDark));
                type = 1;
                break;
            case R.id.tv_new_song_list_fragment:
                recyclerView.destroyDrawingCache();
                startVolley(NewUrl);
                tvNew.setTextColor(getResources().getColor(R.color.mainBlue));
                tvHeat.setTextColor(getResources().getColor(R.color.mainDark));
                type = 2;
                break;
        }
    }

    public void startVolley (String url){

        NetHelper.myRequest(url, new MyNetListener<SongListBean>() {
            @Override
            public void successListener(SongListBean response) {
                data = new ArrayList<>();
                data.add(response);
                SongListAdapter adapter = new SongListAdapter(getActivity());
                adapter.setData(data);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        },SongListBean.class);
    }
}
