package com.example.dllo.project_a_cst.else_class;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.adapter.ListPopAdapter;
import com.example.dllo.project_a_cst.my_database.MyMusicPerson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/24.
 */

public class MediaplayerListPop extends PopupWindow implements View.OnClickListener {

    private ImageView iv;
    private TextView tvOne,tvTwo;
    private View popView;
    private RecyclerView recyclerView;
    private ArrayList<MyMusicPerson> data;
    private Context mContext;

    public void setContext(Context context) {
        mContext = context;
    }

    public void setData(ArrayList<MyMusicPerson> data) {
        this.data = data;
        ListPopAdapter adapter = new ListPopAdapter(mContext);
        adapter.setData(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    public MediaplayerListPop(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popView = inflater.inflate(R.layout.media_player_list_pop, null);
        iv = (ImageView) popView.findViewById(R.id.iv_media_player_list_pop);
        tvOne = (TextView) popView.findViewById(R.id.tv_media_player_list_one);
        tvTwo = (TextView) popView.findViewById(R.id.tv_media_player_list_two);
        recyclerView = (RecyclerView) popView.findViewById(R.id.lv_media_player_list);

        tvOne.setOnClickListener(this);
        iv.setOnClickListener(this);
        tvTwo.setOnClickListener(this);
        this.setContentView(popView);

        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width,height;
        width = metrics.widthPixels;
        height = (int) (metrics.heightPixels*0.7);
        this.setWidth(width);
        this.setHeight(height);
        this.setFocusable(false);
        this.setAnimationStyle(R.style.popUpWindowAnimation);
        this.setBackgroundDrawable(null);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_media_player_list_one:
                dismiss();
                break;

        }
    }
}
