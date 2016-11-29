package com.example.dllo.project_a_cst.main_activity_fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.adapter.BearingAdapter;
import com.example.dllo.project_a_cst.bean.BearingBean;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/23.
 */

public class BearingFragment extends BaseFragment{
    private ArrayList<BearingBean> data;
    private RecyclerView recyclerView;
    private String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=wandoujia&operator=3&method=baidu.ting.ugcfriend.getList&format=json&param=MdQHiv%2F%2BBSYTl39VXcn8dESPvw4rM3naxQSdeb7JiGSv9pqRpzZMYcxA%2FqvopkR2NgdsRojMb6i2CxNCpB%2F98g%3D%3D&timestamp=1480142598&sign=462064952b8866d85f5c007d7a2cf674";
    @Override
    public int setlayout() {
        return R.layout.fragment_bearing;
    }

    @Override
    public void initView(View view) {
        recyclerView = bindView(R.id.recycler_view_bearing);
    }

    @Override
    public void initData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                data = new ArrayList<>();
                Gson gson = new Gson();
                BearingBean bean = gson.fromJson(response,BearingBean.class);
                data.add(bean);
                BearingAdapter adapter = new BearingAdapter(getActivity());
                adapter.setData(data);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
