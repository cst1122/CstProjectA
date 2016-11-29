package com.example.dllo.project_a_cst.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.dllo.project_a_cst.my_class.MyMusicPlayClass;
import com.example.dllo.project_a_cst.bean.MusicBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/24.
 */

public class MusicService extends Service{
    private MediaPlayer mediaPlayer;
    private ArrayList<MusicBean> musicData;
    MyMusicBinder myMusicBinder = new MyMusicBinder();
    private MyMusicPlayClass myMusicPlayClass;


    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = new MediaPlayer();
        musicData = new ArrayList<>();
        myMusicPlayClass = new MyMusicPlayClass(this,0);
        myMusicPlayClass.setMediaPlayer(mediaPlayer);
        myMusicPlayClass.setMusicData(musicData);
        myMusicPlayClass.initialization("MUSIC_BR");
        myMusicPlayClass.getMusicData();
        myMusicPlayClass.setData();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MusicService", "绑定服务");
        return myMusicBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        myMusicPlayClass.stop();
        Log.d("MusicService", "解除绑定");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    public class MyMusicBinder extends Binder {

        public MyMusicPlayClass getMyMusicPlayClass() {
            return myMusicPlayClass;
        }

    }
}
