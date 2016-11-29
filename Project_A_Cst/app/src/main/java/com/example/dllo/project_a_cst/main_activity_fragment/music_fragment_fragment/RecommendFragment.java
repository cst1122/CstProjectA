package com.example.dllo.project_a_cst.main_activity_fragment.music_fragment_fragment;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.adapter.RecommendAdapter;
import com.example.dllo.project_a_cst.bean.RecommendBean;
import com.example.dllo.project_a_cst.main_activity_fragment.BaseFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.example.dllo.project_a_cst.R.id.recycler_view_recommend_fragment;

/**
 * Created by dllo on 16/11/23.
 */

public class RecommendFragment extends BaseFragment{
    private DrawerLayout dlTest;
    private RecyclerView rvTest;

    private RecyclerView recyclerView;
    private ArrayList<RecommendBean> data;
    private String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=musicsybutton&operator=3&method=baidu.ting.plaza.index&cuid=1D999D9D7637E5FD3633492941572AE7";

    @Override
    public int setlayout() {
        return R.layout.music_fragment_recommend_fragment;
    }

    @Override
    public void initView(View view) {
        recyclerView = bindView(recycler_view_recommend_fragment);
//        btnTest = bindView(R.id.btn_test);
//        dlTest= (DrawerLayout) getActivity().findViewById(R.id.dl_mine);
//        rvTest = (RecyclerView) getActivity().findViewById(R.id.main_drawerlayout_recyvlerview);
    }

    @Override
    public void initData() {

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                data = new ArrayList<>();
                Gson gson = new Gson();
                RecommendBean bean = gson.fromJson(response,RecommendBean.class);
                data.add(bean);
                RecommendAdapter adapter = new RecommendAdapter(getActivity());
                adapter.setData(data);
                int[] mainType = {1,2,2,3,4,3,3,2,5};
                adapter.setMainType(mainType);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
//        btnTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                btnTest.setClickable(false);
//                DrawerRecyclerAdapter drawerRecyclerAdapter = new DrawerRecyclerAdapter(getActivity());
//                drawerRecyclerAdapter.setType("搜索");
//                rvTest.setAdapter(drawerRecyclerAdapter);
//                LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
//                rvTest.setLayoutManager(manager);
//                dlTest.setVisibility(View.VISIBLE);
//                dlTest.openDrawer(Gravity.RIGHT);
//            }
//        });
//        dlTest.setDrawerListener(new DrawerLayout.DrawerListener() {
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                dlTest.setVisibility(View.GONE);
//                btnTest.setClickable(true);
//                rvTest.destroyDrawingCache();
//            }
//
//            @Override
//            public void onDrawerStateChanged(int newState) {
//
//            }
//        });
    }
}
