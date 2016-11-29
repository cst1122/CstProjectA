package com.example.dllo.project_a_cst.main_activity_fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.adapter.MusicFragmentTabAdapter;
import com.example.dllo.project_a_cst.main_activity_fragment.music_fragment_fragment.KsongFragment;
import com.example.dllo.project_a_cst.main_activity_fragment.music_fragment_fragment.ListFragment;
import com.example.dllo.project_a_cst.main_activity_fragment.music_fragment_fragment.RecommendFragment;
import com.example.dllo.project_a_cst.main_activity_fragment.music_fragment_fragment.SongListFragment;
import com.example.dllo.project_a_cst.main_activity_fragment.music_fragment_fragment.VideoFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/23.
 */

public class MusicFragment extends BaseFragment{

    private TabLayout musicFTab;
    private ViewPager musicFViewpager;
    private ArrayList<Fragment> data;

    @Override
    public int setlayout() {
        return R.layout.fragment_music;
    }

    @Override
    public void initView(View view) {
        musicFTab= (TabLayout) view.findViewById(R.id.tablayout_fragment_music);
        musicFViewpager = (ViewPager) view.findViewById(R.id.viewpager_fragment_music);
    }

    @Override
    public void initData() {
        data = new ArrayList<>();
        data.add(new RecommendFragment());
        data.add(new SongListFragment());
        data.add(new ListFragment());
        data.add(new VideoFragment());
        data.add(new KsongFragment());
        MusicFragmentTabAdapter adapter = new MusicFragmentTabAdapter(getChildFragmentManager(),data);
        musicFViewpager.setAdapter(adapter);
        musicFTab.setupWithViewPager(musicFViewpager);
        musicFTab.setTabTextColors(Color.BLACK,Color.rgb(0x00,0xb4,0xff));
        musicFTab.setSelectedTabIndicatorColor(Color.rgb(0x00,0xb4,0xff));
    }
}
