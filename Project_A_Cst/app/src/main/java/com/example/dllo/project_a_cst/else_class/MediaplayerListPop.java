package com.example.dllo.project_a_cst.else_class;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.dllo.project_a_cst.R;

/**
 * Created by dllo on 16/11/24.
 */

public class MediaplayerListPop extends PopupWindow implements View.OnClickListener {
//    private ImageButton btnReturn;
//    private ImageView ivPlayMode, ivPrevious, ivNext, ivPlayPause, ivMusicList;
//    private TextView tvFirsttime, tvSecondtime;
//    private SeekBar seekBar;
//    private ViewPager viewPager;
//    private View mediaplayerPopView;
    private ImageView iv;
    private TextView tvOne,tvTwo;
    private View popView;
    private RecyclerView recyclerView;

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
//        btnReturn = (ImageButton) mediaplayerPopView.findViewById(R.id.btn_music_pop_return);
//        ivPlayMode = (ImageView) mediaplayerPopView.findViewById(R.id.iv_music_pop_play_mode);
//        ivPrevious = (ImageView) mediaplayerPopView.findViewById(R.id.iv_music_pop_previous);
//        ivNext = (ImageView) mediaplayerPopView.findViewById(R.id.iv_music_pop_next);
//        ivPlayPause = (ImageView) mediaplayerPopView.findViewById(R.id.iv_music_pop_play_pause);
//        ivMusicList = (ImageView) mediaplayerPopView.findViewById(R.id.iv_music_pop_music_list);
//        tvFirsttime = (TextView) mediaplayerPopView.findViewById(R.id.tv_music_pop_first_time);
//        tvSecondtime = (TextView) mediaplayerPopView.findViewById(R.id.tv_music_pop_second_time);
//        seekBar = (SeekBar) mediaplayerPopView.findViewById(R.id.seek_bar_music_pop);
//        viewPager = (ViewPager) mediaplayerPopView.findViewById(R.id.view_pager_music_pop);
//
//        btnReturn.setOnClickListener(this);
//        ivPlayMode.setOnClickListener(itemsOnClick);
//        ivPrevious.setOnClickListener(itemsOnClick);
//        ivNext.setOnClickListener(itemsOnClick);
//        ivPlayPause.setOnClickListener(itemsOnClick);
//        ivMusicList.setOnClickListener(itemsOnClick);
//        tvFirsttime.setOnClickListener(itemsOnClick);
//        tvSecondtime.setOnClickListener(itemsOnClick);
//
//        this.setContentView(mediaplayerPopView);
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
