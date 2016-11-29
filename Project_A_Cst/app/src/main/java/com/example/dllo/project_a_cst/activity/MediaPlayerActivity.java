package com.example.dllo.project_a_cst.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.bean.MusicBean;
import com.example.dllo.project_a_cst.my_class.MyMusicPlayClass;
import com.example.dllo.project_a_cst.service.MusicService;

/**
 * Created by dllo on 16/11/26.
 */

public class MediaPlayerActivity extends BaseActivity implements View.OnClickListener{
    private ImageButton btnReturn;
    private ImageView ivPlayMode, ivPrevious, ivNext, ivPlayPause, ivMusicList;
    private TextView tvFirstTime, tvSecondTime;
    private SeekBar seekBar;
    private ViewPager viewPager;
    private MusicService.MyMusicBinder myMusicBinder;
    private ServiceConnection connection;
    private MyMusicPlayClass myMusicPlayClass;
    private Intent mIntent;

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
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myMusicBinder = (MusicService.MyMusicBinder) service;
                myMusicPlayClass = myMusicBinder.getMyMusicPlayClass();
                if (myMusicBinder!=null){
                    seekBar.setMax(myMusicPlayClass.max());
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(mIntent, connection, BIND_AUTO_CREATE);
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
                if (myMusicBinder!=null){
                    myMusicPlayClass.playLast();
                    Toast.makeText(context, "上一曲", Toast.LENGTH_SHORT).show();
                }
            break;
            case R.id.iv_music_pop_next:
                if (myMusicBinder!=null){
                    myMusicPlayClass.playNext();
                    Toast.makeText(context, "下一曲", Toast.LENGTH_SHORT).show();
                }
            break;
            case R.id.iv_music_pop_play_pause:
                if (myMusicBinder!=null){
                    if (myMusicPlayClass.musicIsPlay()){
                        myMusicPlayClass.pause();
                        Toast.makeText(context, "暂停", Toast.LENGTH_SHORT).show();
                    }else {
                        myMusicPlayClass.play();
                        Toast.makeText(context, "开始", Toast.LENGTH_SHORT).show();
                    }
                }
            break;
            case R.id.iv_music_pop_music_list:

            break;
            case R.id.tv_music_pop_first_time:
            break;
            case R.id.tv_music_pop_second_time:
            break;
        }
    }
    class MyMusicBR extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (myMusicBinder != null) {
                MusicBean bean = intent.getParcelableExtra("key");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MediaPlayerActivity", "销毁");
    }
}
