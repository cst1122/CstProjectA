package com.example.dllo.project_a_cst.main_activity_fragment.music_fragment_fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.adapter.ListAdapter;
import com.example.dllo.project_a_cst.bean.ListBean;
import com.example.dllo.project_a_cst.else_class.MyNetListener;
import com.example.dllo.project_a_cst.else_class.NetHelper;
import com.example.dllo.project_a_cst.main_activity_fragment.BaseFragment;

import java.util.ArrayList;

import static com.example.dllo.project_a_cst.my_class.MyConstants.LIST_FRAGMENT_URL;

/**
 * Created by dllo on 16/11/23.
 */

public class ListFragment extends BaseFragment {

    private ListView listView;
    private ArrayList<ListBean> data;
    private String url = LIST_FRAGMENT_URL;

    @Override
    public int setlayout() {
        return R.layout.music_fragment_list_fragment;
    }

    @Override
    public void initView(View view) {
        listView = (ListView) view.findViewById(R.id.list_view_list_fragment);

    }

    @Override
    public void initData() {
        NetHelper.myRequest(url, new MyNetListener<ListBean>() {
            @Override
            public void successListener(ListBean response) {
                data = new ArrayList<>();
                data.add(response);
                ListAdapter adapter = new ListAdapter(getActivity());
                adapter.setData(data);
                listView.setAdapter(adapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        },ListBean.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent("开启榜单");
                intent.putExtra("图片网址",data.get(0).getContent().get(position).getPic_s210());
                intent.putExtra("type",data.get(0).getContent().get(position).getType());
                getActivity().sendBroadcast(intent);
            }
        });
    }

}
