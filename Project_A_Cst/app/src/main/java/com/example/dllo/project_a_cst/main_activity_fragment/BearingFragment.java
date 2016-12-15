package com.example.dllo.project_a_cst.main_activity_fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.VolleyError;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.adapter.BearingAdapter;
import com.example.dllo.project_a_cst.bean.BearingBean;
import com.example.dllo.project_a_cst.else_class.MyNetListener;
import com.example.dllo.project_a_cst.else_class.NetHelper;

import java.util.ArrayList;

import static com.example.dllo.project_a_cst.my_class.MyConstants.BEARING_URL;

/**
 * Created by dllo on 16/11/23.
 */

public class BearingFragment extends BaseFragment{
    private ArrayList<BearingBean> data;
    private RecyclerView recyclerView;
    private String url = BEARING_URL;
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

        NetHelper.myRequest(url, new MyNetListener<BearingBean>() {
            @Override
            public void successListener(BearingBean response) {
                data = new ArrayList<>();
                data.add(response);
                BearingAdapter adapter = new BearingAdapter(getActivity());
                adapter.setData(data);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        },BearingBean.class);
    }
}
