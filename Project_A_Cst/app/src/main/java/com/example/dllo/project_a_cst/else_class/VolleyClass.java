package com.example.dllo.project_a_cst.else_class;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.project_a_cst.adapter.SongListAdapter;
import com.example.dllo.project_a_cst.bean.SongListBean;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/26.
 */

public abstract class VolleyClass<T> {
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private ArrayList<SongListBean> data;
    private  T bean;
    private T type;
    private Class<T> tClass;
    private Context context;
    private String url;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private SongListAdapter adapter;

    public VolleyClass(Context context, String url, RecyclerView recyclerView, LinearLayoutManager manager, SongListAdapter adapter) {
        this.context = context;
        this.url = url;
        this.recyclerView = recyclerView;
        this.manager = manager;
        this.adapter = adapter;
    }

    public void startVolley () {
        requestQueue = Volley.newRequestQueue(context);
        stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                data = new ArrayList<>();
                Gson gson = new Gson();
                bean = gson.fromJson(response, (Class<T>) type);
               // data.add(bean);
                adapter.setData(data);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(manager);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }


}
