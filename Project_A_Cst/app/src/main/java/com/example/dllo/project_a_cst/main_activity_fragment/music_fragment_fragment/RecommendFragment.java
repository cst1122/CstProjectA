package com.example.dllo.project_a_cst.main_activity_fragment.music_fragment_fragment;

import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.VolleyError;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.adapter.RecommendAdapter;
import com.example.dllo.project_a_cst.bean.RecommendBean;
import com.example.dllo.project_a_cst.else_class.MyNetListener;
import com.example.dllo.project_a_cst.else_class.NetHelper;
import com.example.dllo.project_a_cst.main_activity_fragment.BaseFragment;

import java.util.ArrayList;

import static com.example.dllo.project_a_cst.R.id.recycler_view_recommend_fragment;
import static com.example.dllo.project_a_cst.R.id.swipe_rl_recommend;
import static com.example.dllo.project_a_cst.my_class.MyConstants.RECOMMEND_FRAGMENT_URL;

/**
 * Created by dllo on 16/11/23.
 */

public class RecommendFragment extends BaseFragment {
    private DrawerLayout singerDl;
    private RecyclerView recyclerView;
    private ArrayList<RecommendBean> data;
    private String url = RECOMMEND_FRAGMENT_URL;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public int setlayout() {
        return R.layout.music_fragment_recommend_fragment;
    }

    @Override
    public void initView(View view) {
        recyclerView = bindView(recycler_view_recommend_fragment);
        mSwipeRefreshLayout = bindView(swipe_rl_recommend);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.myBlue,
                R.color.myGreen,
                R.color.myRed,
                R.color.myYellow
        );
    }

    @Override
    public void initData() {

        startVolley(url);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.destroyDrawingCache();
                        startVolley(url);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });

    }

    public void startVolley (String myUrl) {
        NetHelper.myRequest(myUrl, new MyNetListener<RecommendBean>() {
            @Override
            public void successListener(RecommendBean response) {
                data = new ArrayList<>();
                data.add(response);
                RecommendAdapter adapter = new RecommendAdapter(getActivity());
                adapter.setData(data);
                int[] mainType = {1,2,2,3,4,3,3,2,5};
                adapter.setMainType(mainType);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        },RecommendBean.class);
    }
}
