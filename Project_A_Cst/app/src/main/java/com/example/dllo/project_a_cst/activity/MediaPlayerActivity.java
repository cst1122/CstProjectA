package com.example.dllo.project_a_cst.activity;

import android.content.Intent;
import android.content.ServiceConnection;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.adapter.SongFragmentAdapter;
import com.example.dllo.project_a_cst.main_activity_fragment.SongImageFragment;
import com.example.dllo.project_a_cst.main_activity_fragment.SongMsgFragment;
import com.example.dllo.project_a_cst.main_activity_fragment.lyricsFragment;
import com.example.dllo.project_a_cst.my_class.MyMusicPlayClass;
import com.example.dllo.project_a_cst.service.MusicService;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/26.
 */

public class MediaPlayerActivity extends BaseActivity implements View.OnClickListener{
    private ImageButton btnReturn;
    private ImageView ivPlayMode, ivPrevious, ivNext, ivPlayPause, ivMusicList;
    private TextView tvFirstTime, tvSecondTime;
    private SeekBar seekBar;
    private ViewPager viewPager;
    private ServiceConnection connection;
    private MyMusicPlayClass myMusicPlayClass;
    private Intent mIntent;
    private ArrayList<Fragment> fragmentData;

    @Override
    int setlaouyt() {
        return R.layout.activity_media_player;
    }

    @Override
    void initView() {
        btnReturn = bindView(R.id.btn_music_pop_return);
        ivPlayMode = bindView(R.id.iv_music_pop_play_mode);
        ivPrevious = bindView(R.id.iv_music_pop_previous);
        ivNext = bindView(R.id.iv_music_pop_next);
        ivPlayPause = bindView(R.id.iv_music_pop_play_pause);
        ivMusicList = bindView(R.id.iv_music_pop_music_list);
        tvFirstTime = bindView(R.id.tv_music_pop_first_time);
        tvSecondTime = bindView(R.id.tv_music_pop_second_time);
        seekBar = bindView(R.id.seek_bar_music_pop);
        viewPager = bindView(R.id.view_pager_music_pop);
    }

    @Override
    void initData() {
        btnReturn.setOnClickListener(this);
        ivPlayMode.setOnClickListener(this);
        ivPrevious.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivPlayPause.setOnClickListener(this);
        ivMusicList.setOnClickListener(this);
        tvFirstTime.setOnClickListener(this);
        tvSecondTime.setOnClickListener(this);
        fragmentData = new ArrayList<>();
        fragmentData.add(new SongMsgFragment());
        fragmentData.add(new SongImageFragment());
        fragmentData.add(new lyricsFragment());
        SongFragmentAdapter adapter = new SongFragmentAdapter(getSupportFragmentManager(), fragmentData);
        viewPager.setAdapter(adapter);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mIntent = new Intent(this, MusicService.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_music_pop_return:
                finish();
            break;
            case R.id.iv_music_pop_play_mode:
            break;
            case R.id.iv_music_pop_previous:

            break;
            case R.id.iv_music_pop_next:

            break;
            case R.id.iv_music_pop_play_pause:

            break;
            case R.id.iv_music_pop_music_list:

            break;
            case R.id.tv_music_pop_first_time:
            break;
            case R.id.tv_music_pop_second_time:
            break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MediaPlayerActivity", "销毁");
    }
}
