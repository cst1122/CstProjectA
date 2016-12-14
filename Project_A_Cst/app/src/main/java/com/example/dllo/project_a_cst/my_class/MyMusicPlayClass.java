package com.example.dllo.project_a_cst.my_class;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.example.dllo.project_a_cst.adapter.DrawerMainAdapter;

/**
 * Created by dllo on 16/11/25.
 */

public class MyMusicPlayClass {

    // 开启抽屉的方法
    public static void startDrawer (String type, RecyclerView recyclerView, DrawerLayout drawerLayout, Context context, LinearLayoutManager manager) {
        DrawerMainAdapter moreAdapter = new DrawerMainAdapter(context,type);
        recyclerView.setAdapter(moreAdapter);
        recyclerView.setLayoutManager(manager);
        drawerLayout.setVisibility(View.VISIBLE);
        drawerLayout.requestDisallowInterceptTouchEvent(false);
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

}
