package com.example.dllo.project_a_cst.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.VolleyError;
import com.example.dllo.project_a_cst.bean.SongUrlBean;
import com.example.dllo.project_a_cst.else_class.MyNetListener;
import com.example.dllo.project_a_cst.else_class.NetHelper;
import com.example.dllo.project_a_cst.my_class.Mp3Info;
import com.example.dllo.project_a_cst.my_class.MusicUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PAUSE;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY_DANQU;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY_LAST;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY_NEXT;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY_SHUNXU;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY_SUIJI;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY_XUNHUAN;

/**
 * Created by dllo on 16/11/24.
 */

public class MusicService extends Service implements MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    private MediaPlayer mediaPlayer;
    private static ArrayList<Mp3Info> musicData = new ArrayList<>();
    //private NotificationManager mManager;
    private MyNewMusicBR mMyNewMusicBR;
    private int mposition = 0;
    private int progress = 0;
    private boolean isPlaying = false;
    private Intent mIntent;
    private boolean isFirst = true;
    private boolean Flag = true;
    private ArrayList<String> songUrl;
    private Map imageMap;
    private GetInternetMusicBR mGetInternetMusicBR;
    private String type = "";


    @Override
    public void onCreate() {
        super.onCreate();
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);

        registerFilter();

        mGetInternetMusicBR = new GetInternetMusicBR();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("歌曲地址集合");
        registerReceiver(mGetInternetMusicBR,intentFilter);
        //mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplication());
//        builder.setContentTitle(musicData.get(myMusicPlayClass.getPosition()).getMusicName());
//        builder.setContentText(musicData.get(myMusicPlayClass.getPosition()).getSingerName());
//        builder.setSmallIcon(R.mipmap.ic_launcher);
//        builder.setAutoCancel(false);
//        builder.setOngoing(true);
//        builder.setShowWhen(false);
//        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
//        remoteViews.setTextViewText(R.id.tv_noti_singer, musicData.get(myMusicPlayClass.getPosition()).getSingerName());
//        remoteViews.setTextViewText(R.id.tv_noti_song_name, musicData.get(myMusicPlayClass.getPosition()).getMusicName());
//        remoteViews.setImageViewResource(R.id.iv_noti_music_image, R.mipmap.ic_launcher);
//        Notification notification = builder.build();
//        notification.bigContentView = remoteViews;
//        mManager.notify(100, notification);
//        Log.d("MusicService", "开启通知");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            musicData = intent.getParcelableArrayListExtra("音乐数据");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mMyNewMusicBR);
        unregisterReceiver(mGetInternetMusicBR);
        Flag = false;
        super.onDestroy();
    }

    private void registerFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MUSIC_PLAY);
        intentFilter.addAction(MUSIC_PAUSE);
        intentFilter.addAction(MUSIC_PLAY_NEXT);
        intentFilter.addAction(MUSIC_PLAY_LAST);
        intentFilter.addAction(MUSIC_PLAY_SHUNXU);
        intentFilter.addAction(MUSIC_PLAY_DANQU);
        intentFilter.addAction(MUSIC_PLAY_XUNHUAN);
        intentFilter.addAction(MUSIC_PLAY_SUIJI);
        intentFilter.setPriority(1000);
        if (mMyNewMusicBR == null) {
            mMyNewMusicBR = new MyNewMusicBR();
        }
        registerReceiver(mMyNewMusicBR, intentFilter);

    }

    private void play(int position) {

        if (mediaPlayer != null && musicData.size() > 0) {
            mediaPlayer.reset();
            try {
                mediaPlayer.setDataSource(musicData.get(position).getUrl());
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
        int max = mediaPlayer.getDuration();
        Log.d("music", musicData.get(mposition).getArtist()+musicData.get(mposition).getTitle() + "^*&#$^$#$");
        isPlaying = true;
        SendMusicIsPlay();
        SendMusicInformation(max);
        SendMusicProgress();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.d("music", "下一曲");
        mIntent = new Intent(MUSIC_PLAY_NEXT);
        try {
            Thread.sleep(2000);
            sendBroadcast(mIntent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class MyNewMusicBR extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case MUSIC_PLAY:
                    if (mediaPlayer.getCurrentPosition()>500){
                        mediaPlayer.start();
                    }else {
                        play(mposition);
                    }
                    break;
                case MUSIC_PAUSE:
                    pause();
                    isPlaying = false;
                    break;
                case MUSIC_PLAY_NEXT:
                    mposition++;
                    if (mposition>musicData.size()-1){
                        mposition = 0;
                    }
                    play(mposition);
                    break;
                case MUSIC_PLAY_LAST:
                    mposition--;
                    if (mposition<0){
                        mposition = musicData.size()-1;
                    }
                    play(mposition);
                    break;
                case MUSIC_PLAY_SHUNXU:
                    break;
                case MUSIC_PLAY_DANQU:
                    break;
                case MUSIC_PLAY_XUNHUAN:
                    break;
                case MUSIC_PLAY_SUIJI:
                    break;
            }
        }
    }

    private void SendMusicIsPlay() {
        mIntent = new Intent("音乐是否播放");
        if (mediaPlayer.isPlaying()) {
            isPlaying = true;
        } else {
            isPlaying = false;
        }
        mIntent.putExtra("是否播放", isPlaying);
        sendBroadcast(mIntent);
    }

    private void SendMusicInformation(int max) {
        mIntent = new Intent("歌曲信息");
        mIntent.putExtra("歌曲名", musicData.get(mposition).getTitle());
        mIntent.putExtra("歌手", musicData.get(mposition).getArtist());
        mIntent.putExtra("歌曲长度",max);
        if (musicData.get(mposition).getId()!=0&&musicData.get(mposition).getAlbumId()!=0){
            mIntent.putExtra("歌曲图片", MusicUtil.getArtwork(this, musicData.get(mposition).getId(), musicData.get(mposition).getAlbumId(), true, true));
        }else {
            mIntent.putExtra("图片网址",imageMap.get(mposition).toString());
        }
        sendBroadcast(mIntent);
    }

    private void SendMusicProgress() {
        mIntent = new Intent("音乐播放进度");
        if (mediaPlayer != null) {
            if (isFirst) {
                isFirst = false;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (Flag) {
                            if (mediaPlayer.isPlaying()) {
                                progress = mediaPlayer.getCurrentPosition();
                                mIntent.putExtra("进度", progress);
                            }
                            mIntent.putExtra("播放状态",mediaPlayer.isPlaying());
                            sendBroadcast(mIntent);
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        }
    }
    class GetInternetMusicBR extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            mposition = intent.getIntExtra("第几首歌", 0);
            if (!type.equals(intent.getStringExtra("歌单"))) {
                type = intent.getStringExtra("歌单");
                Log.d("music", "&%^&%#^#");
                musicData = new ArrayList<>();
                imageMap = new HashMap();
                songUrl = new ArrayList<>();
                songUrl = intent.getStringArrayListExtra("歌曲地址");
                for (int i = 0; i < songUrl.size(); i++) {
                    musicData.add(startVolley(songUrl.get(i), new Mp3Info(), i));
                }
            }
            if (musicData.get(mposition).getUrl()!=null){
                play(mposition);
            }

        }
    }
    private Mp3Info startVolley (String url, final Mp3Info musicBean, final int mposition) {
        NetHelper.myRequest(url, new MyNetListener<String>() {
            @Override
            public void successListener(String response) {
                response =  response.substring(1,response.length()-2);
                Gson gson = new Gson();
                SongUrlBean bean = gson.fromJson(response,SongUrlBean.class);
                musicBean.setUrl(bean.getBitrate().getFile_link());
                musicBean.setSize(0);
                musicBean.setDuration(0);
                musicBean.setId(0);
                musicBean.setTitle(bean.getSonginfo().getTitle());
                musicBean.setArtist(bean.getSonginfo().getAuthor());
                musicBean.setAlbum("");
                musicBean.setAlbumId(0);
                imageMap.put(mposition,bean.getSonginfo().getPic_premium());
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
        return musicBean;
    }


}
