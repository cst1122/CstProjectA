package com.example.dllo.project_a_cst.main_activity_fragment.music_fragment_fragment;

import android.view.View;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.adapter.ListAdapter;
import com.example.dllo.project_a_cst.bean.ListBean;
import com.example.dllo.project_a_cst.main_activity_fragment.BaseFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/23.
 */

public class ListFragment extends BaseFragment{
    private ListView listView;
    private ArrayList<ListBean>data;
    private String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&method=baidu.ting.billboard.billCategory&format=json&kflag=2";
    @Override
    public int setlayout() {
        return R.layout.music_fragment_list_fragment;
    }

    @Override
    public void initView(View view) {
        listView = bindView(R.id.list_view_list_fragment);
    }

    @Override
    public void initData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                data = new ArrayList<>();
                Gson gson = new Gson();
                ListBean bean = gson.fromJson(response,ListBean.class);
                data.add(bean);
                ListAdapter adapter = new ListAdapter(getActivity());
                adapter.setData(data);
                listView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
