package com.example.dllo.project_a_cst.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/** 歌手抽屉中顶部ViewPager的适配器
 * Created by dllo on 16/11/30.
 */

public class SingerVpTypeOneAdapter extends PagerAdapter{
    private ArrayList<View> data;

    public void setData(ArrayList<View> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = data.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = data.get(position);
        container.removeView(view);
    }
}
