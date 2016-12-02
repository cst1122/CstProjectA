package com.example.dllo.project_a_cst.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/** 播放音乐Activity中的适配器
 * Created by dllo on 16/11/30.
 */

public class SongFragmentAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> data;
    public SongFragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    public SongFragmentAdapter(FragmentManager fm, ArrayList<Fragment> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
