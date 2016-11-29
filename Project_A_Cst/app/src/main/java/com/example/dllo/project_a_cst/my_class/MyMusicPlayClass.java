package com.example.dllo.project_a_cst.my_class;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.provider.MediaStore;

import com.example.dllo.project_a_cst.bean.MusicBean;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dllo on 16/11/25.
 */

public class MyMusicPlayClass {
    private Context context;
    private ArrayList<MusicBean> musicData;
    private MediaPlayer mediaPlayer;
    private Intent serviceIntent;
    private int position = 0;

    public MyMusicPlayClass(Context context, int position) {
        this.context = context;
        this.position = position;
    }

    public void setMusicData(ArrayList<MusicBean> musicData) {
        this.musicData = musicData;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void initialization(String key) {
        serviceIntent = new Intent(key);
    }

    public void setData(){
        try {
            mediaPlayer.setDataSource(musicData.get(position).getMusicUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 如果没有播放时播放音乐的方法
    public void play() {
        if (!mediaPlayer.isPlaying()) {
            try {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                mediaPlayer.setDataSource(musicData.get(position).getMusicUrl());
                mediaPlayer.prepareAsync();
                if (mediaPlayer != null) {
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }
                serviceIntent.putExtra("key", musicData.get(position));
                context.sendBroadcast(serviceIntent);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                mediaPlayer = null;
                mediaPlayer = new MediaPlayer();
            }
        }

    }

    // 播放进度
    public int progress() {
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        } else {
            return 0;
        }
    }

    // 获取歌曲总时长
    public int max() {
        return mediaPlayer.getDuration();
    }

    // 播放上一曲
    public void playLast() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        position--;
        if (position < 0) {
            position = musicData.size() - 1;
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
            play();
        } else {
            mediaPlayer.reset();
            play();
        }
    }

    // 播放下一曲
    public void playNext() {
        mediaPlayer.stop();
        position++;
        if (position > musicData.size() - 1) {
            position = 0;
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
            play();
        } else {
            mediaPlayer.reset();
            play();
        }
    }

    // 停止播放
    public void stop() {
        mediaPlayer.stop();
    }

    // 暂停播放
    public void pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }

    // 判断音乐是否播放
    public boolean musicIsPlay() {
        if (mediaPlayer.isPlaying()) {
            return true;
        } else {
            return false;
        }
    }

    // 获取本地音乐
    public void getMusicData() {
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                String musicName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String singerName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String musicUrl = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
                Long duringTime = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                if (isMusic != 0 && duringTime / 1000 > 30) {
                    // 获取时长大于30秒的歌曲
                    MusicBean bean = new MusicBean(musicName, singerName, musicUrl, duringTime);
                    musicData.add(bean);
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
    }

}
