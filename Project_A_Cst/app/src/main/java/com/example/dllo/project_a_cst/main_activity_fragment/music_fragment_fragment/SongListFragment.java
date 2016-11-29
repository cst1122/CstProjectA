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
import com.example.dllo.project_a_cst.adapter.SongListAdapter;
import com.example.dllo.project_a_cst.bean.SongListBean;
import com.example.dllo.project_a_cst.main_activity_fragment.BaseFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/23.
 */

public class SongListFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ArrayList<SongListBean> data;
    private String HeatUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&method=baidu.ting.ugcdiy.getChanneldiy&param=%2BAwtWRF72CKYAtqDM2Nf8%2BnLj%2BSz8CUk%2BX2WKgMOmeMrHrs12iANZHUXkJ0fW6VTGoOS7HkbFcae4BQ%2Fa%2Fqhhj7j%2FzBRYFSZNHFxF%2F%2BX8egRYVMgBQJlUjefWo2mDG6l&timestamp=1480054307&sign=6e8763041d038759eb7fb750b4c92769";
    private String NewUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&method=baidu.ting.ugcdiy.getChanneldiy&param=EizM6Vat4Il0Cw3Ks69f0ZB7PduqSUmBTbbnWSfhRYZwU1Fv32cEck2NWBK60ourk1m2zM84o20UlSFkpICY%2BXh53eS%2FgAmRhOp2c44SjT3KQSduG5QZ8sv1Htdr6tVj&timestamp=1480076353&sign=d8c0485cdc629c15c9f2cffb3219e5b2";
    private TextView tvNew,tvHeat;
    private RequestQueue requestQueue;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_heat_song_list_fragment:
                recyclerView.destroyDrawingCache();
                startVolley(HeatUrl);
                tvHeat.setTextColor(getResources().getColor(R.color.mainBlue));
                tvNew.setTextColor(getResources().getColor(R.color.mainDark));
                break;
            case R.id.tv_new_song_list_fragment:
                recyclerView.destroyDrawingCache();
                startVolley(NewUrl);
                tvNew.setTextColor(getResources().getColor(R.color.mainBlue));
                tvHeat.setTextColor(getResources().getColor(R.color.mainDark));
                break;
        }
    }

    public void startVolley (String url){
        data = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                SongListBean bean = gson.fromJson(response,SongListBean.class);
                data.add(bean);
                SongListAdapter adapter = new SongListAdapter(getActivity());
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
}
