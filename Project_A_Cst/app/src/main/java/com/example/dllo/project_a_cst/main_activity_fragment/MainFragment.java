package com.example.dllo.project_a_cst.main_activity_fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.adapter.MainTabAdapter;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by dllo on 16/12/2.
 */

public class MainFragment extends SupportFragment{
    private ImageButton btnMore, btnSearch;
    private ArrayList<Fragment> data;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnMore = (ImageButton) view.findViewById(R.id.btn_more_fragment);
        btnSearch = (ImageButton) view.findViewById(R.id.btn_search_fragment);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_main_fragment);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout_main_fragment);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        data = new ArrayList<>();
        data.add(new MineFragment());
        data.add(new MusicFragment());
        data.add(new BearingFragment());
        data.add(new LiveFragment());
        MainTabAdapter adapter = new MainTabAdapter(getChildFragmentManager(), data);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.rgb(0x99, 0xe1, 0xff), Color.WHITE);
    }
}
