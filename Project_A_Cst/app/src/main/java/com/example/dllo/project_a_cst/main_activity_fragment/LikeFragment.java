package com.example.dllo.project_a_cst.main_activity_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.adapter.LikeAdapter;
import com.example.dllo.project_a_cst.my_database.MyPerson;

import java.util.ArrayList;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by dllo on 16/12/14.
 */

public class LikeFragment extends SwipeBackFragment{
    private ArrayList<MyPerson> data;
    private RecyclerView recyclerView;

    public void setData(ArrayList<MyPerson> data) {
        this.data = data;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_like,container,false);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_like);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LikeAdapter adapter = new LikeAdapter(getActivity());
        adapter.setData(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
