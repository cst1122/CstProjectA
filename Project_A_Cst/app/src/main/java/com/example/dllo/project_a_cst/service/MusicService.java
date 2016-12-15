package com.example.dllo.project_a_cst.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.android.volley.VolleyError;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.bean.SongUrlBean;
import com.example.dllo.project_a_cst.else_class.MyNetListener;
import com.example.dllo.project_a_cst.else_class.NetHelper;
import com.example.dllo.project_a_cst.my_class.Mp3Info;
import com.example.dllo.project_a_cst.my_class.MusicUtil;
import com.example.dllo.project_a_cst.my_database.DBTool;
import com.example.dllo.project_a_cst.my_database.MyMusicPerson;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_NAME;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PAUSE;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY_DANQU;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY_LAST;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY_NEXT;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY_SHUNXU;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY_SUIJI;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY_XUNHUAN;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PROGRESS;
import static com.example.dllo.project_a_cst.my_class.MyConstants.SINGER;

/**
 * Created by dllo on 16/11/24.
 */

public class MusicService extends Service implements MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    private MediaPlayer mediaPlayer;
    private static ArrayList<Mp3Info> musicData = new ArrayList<>();
    private NotificationManager mManager;
    private int mposition = 0;
    private int progress = 0;// 音乐播放进度
    private boolean isPlaying = false;
    private Intent mIntent;
    private boolean isFirst = true;
    private boolean Flag = true; // 用来控制线程
    private ArrayList<String> songUrl;
    private static Map imageMap, lrcMap;
    private MyNewMusicBR mMyNewMusicBR;
    private GetInternetMusicBR mGetInternetMusicBR;
    private MakeMusicToProgressBR mMakeMusicToProgressBR;
    private getListMusic mGetListMusic;
    private String type = "";
    private static int playMusicType = 1; // 播放模式  1代表顺序播放 2单曲循环 3列表循环 4随机播放
    private getMusicBR mGetMusicBR;
    public static final int CommandPlay = 1;
    public static final int CommandNext = 2;
    public static final int CommandLast = 3;
    public static final int CommandClose = 4;
    public static final int CommandPause = 5;

    @Override
    public void onCreate() {
        super.onCreate();
        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
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
        registerReceiver(mGetInternetMusicBR, intentFilter);

        mGetMusicBR = new getMusicBR();
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction("播放本地音乐");
        registerReceiver(mGetMusicBR, intentFilter1);

        mGetListMusic = new getListMusic();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("播放列表音乐");
        registerReceiver(mGetListMusic,intentFilter2);

        mMakeMusicToProgressBR = new MakeMusicToProgressBR();
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("拖拽");
        registerReceiver(mMakeMusicToProgressBR,intentFilter3);

    }

    private void StartNotification(int position, boolean isPlay, Bitmap bitmap) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplication());
        builder.setContentTitle(musicData.get(position).getTitle());
        builder.setContentText(musicData.get(position).getArtist());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(false);
        builder.setOngoing(true);
        builder.setShowWhen(false);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
        remoteViews.setTextViewText(R.id.tv_noti_singer, musicData.get(position).getArtist());
        remoteViews.setTextColor(R.id.tv_noti_singer, Color.BLACK);
        remoteViews.setTextViewText(R.id.tv_noti_song_name, musicData.get(position).getTitle());
        remoteViews.setImageViewBitmap(R.id.iv_noti_music_image, bitmap);

        if (isPlay) {
            remoteViews.setImageViewResource(R.id.iv_noti_play, R.mipmap.bt_notificationbar_pause);
        } else {
            remoteViews.setImageViewResource(R.id.iv_noti_play, R.mipmap.bt_notificationbar_play);
        }

//        if (!isPlay) {
//            Intent intent1 = new Intent(this, MusicService.class);
//            intent1.putExtra("command", CommandPlay);
//            PendingIntent PIntent1 = PendingIntent.getService(this, 5, intent1, 0);
//            remoteViews.setOnClickPendingIntent(R.id.iv_noti_play, PIntent1);
//        } else {
//            Intent intent2 = new Intent(this, MusicService.class);
//            intent2.putExtra("command", CommandPause);
//            PendingIntent PIntent2 = PendingIntent.getService(this, 6, intent2, 0);
//            remoteViews.setOnClickPendingIntent(R.id.iv_noti_play, PIntent2);
//        }
        Intent intent3 = new Intent(this, MusicService.class);
        intent3.putExtra("command", CommandNext);
        PendingIntent PIntent3 = PendingIntent.getService(this, 7, intent3, 0);
        remoteViews.setOnClickPendingIntent(R.id.iv_noti_next, PIntent3);

        Intent intent4 = new Intent(this, MusicService.class);
        intent4.putExtra("command", CommandLast);
        PendingIntent PIntent4 = PendingIntent.getService(this, 8, intent4, 0);
        remoteViews.setOnClickPendingIntent(R.id.iv_noti_last, PIntent4);

        Intent intent5 = new Intent(this, MusicService.class);
        intent5.putExtra("command", CommandClose);
        PendingIntent PIntent5 = PendingIntent.getService(this, 9, intent5, 0);
        remoteViews.setOnClickPendingIntent(R.id.iv_noti_finish, PIntent5);

        Notification notification = builder.build();
        notification.bigContentView = remoteViews;
        mManager.notify(100, notification);
        Log.d("MusicService", "开启通知");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (DBTool.getInstance().queryMusicAll() != null) {
            List<MyMusicPerson> data = DBTool.getInstance().queryMusicAll();
            for (int i = 0; i < data.size(); i++) {
                Mp3Info bean = new Mp3Info();
                bean.setAlbumId(0);
                bean.setAlbum("");
                bean.setSize(0);
                bean.setUrl(data.get(i).getUrl());
                bean.setArtist(data.get(i).getArtist());
                bean.setDuration(0);
                bean.setId(0);
                bean.setTitle(data.get(i).getTitle());
                musicData.add(bean);
            }
        } else {
            musicData = (ArrayList<Mp3Info>) MusicUtil.getMp3Infos(this);
        }

        if (intent!=null) {
            int command = intent.getIntExtra("command", 0);

            switch (command) {
                case CommandPlay:
                    mIntent = new Intent(MUSIC_PLAY);
                    sendBroadcast(mIntent);
                    break;
                case CommandPause:
                    mIntent = new Intent(MUSIC_PAUSE);
                    sendBroadcast(mIntent);
                    break;
                case CommandNext:
                    mIntent = new Intent(MUSIC_PLAY_NEXT);
                    sendBroadcast(mIntent);
                    break;
                case CommandLast:
                    mIntent = new Intent(MUSIC_PLAY_LAST);
                    sendBroadcast(mIntent);
                    break;
                case CommandClose:
                    mManager.cancelAll();
                    break;

            }
        }
        if (musicData != null && musicData.size() > 0) {
            StartNotification(mposition, isPlaying, MusicUtil.getArtwork(this, musicData.get(mposition).getId(), musicData.get(mposition).getAlbumId(), true, true));
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
        unregisterReceiver(mGetMusicBR);
        unregisterReceiver(mGetListMusic);
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
        Log.d("music", musicData.get(mposition).getArtist() + musicData.get(mposition).getTitle() + "$#$");
        isPlaying = true;
        SendMusicIsPlay();
        SendMusicInformation(max);
        SendMusicProgress();
        if (musicData != null && musicData.size() > 0) {
            if (DBTool.getInstance().queryMusicAll() != null) {
                DBTool.getInstance().deleteMusicAll();
            }
            for (int i = 0; i < musicData.size(); i++) {
                MyMusicPerson person = new MyMusicPerson(null, musicData.get(i).getId()
                        , musicData.get(i).getTitle(), musicData.get(i).getArtist()
                        , musicData.get(i).getAlbumId(), musicData.get(i).getUrl());
                DBTool.getInstance().insertPerson(person);
            }
        }
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
                    if (mediaPlayer.getCurrentPosition() > 500) {
                        mediaPlayer.start();
                    } else {
                        play(mposition);
                    }

                    break;
                case MUSIC_PAUSE:
                    pause();
                    isPlaying = false;
                    break;
                case MUSIC_PLAY_NEXT:
                    switch (playMusicType) {
                        case 1:
                            mposition++;
                            if (mposition > musicData.size() - 1) {
                                mposition = 0;
                            }
                            play(mposition);
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;

                    }
                    break;
                case MUSIC_PLAY_LAST:
                    mposition--;
                    if (mposition < 0) {
                        mposition = musicData.size() - 1;
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
        if (lrcMap != null) {
            mIntent.putExtra("歌词网址", lrcMap.get(mposition).toString());
        }

        sendBroadcast(mIntent);
    }

    private void SendMusicInformation(int max) {
        mIntent = new Intent("歌曲信息");
        mIntent.putExtra(MUSIC_NAME, musicData.get(mposition).getTitle());
        mIntent.putExtra(SINGER, musicData.get(mposition).getArtist());
        mIntent.putExtra("歌曲长度", max);
        if (musicData.get(mposition).getId() != 0 && musicData.get(mposition).getAlbumId() != 0) {
            mIntent.putExtra("歌曲图片", MusicUtil.getArtwork(this, musicData.get(mposition).getId(), musicData.get(mposition).getAlbumId(), true, true));
        } else {
            if (imageMap != null) {
                mIntent.putExtra("图片网址", imageMap.get(mposition).toString());
            }
        }
        mIntent.putExtra("歌词Url", musicData.get(mposition).getUrl());
        mIntent.putParcelableArrayListExtra("歌曲集合", musicData);
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
                                mIntent.putExtra(MUSIC_PROGRESS, progress);
                            }
                            mIntent.putExtra("播放状态", mediaPlayer.isPlaying());
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

    class GetInternetMusicBR extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            mposition = intent.getIntExtra("第几首歌", 0);
            if (!type.equals(intent.getStringExtra("歌单"))) {
                type = intent.getStringExtra("歌单");
                musicData = new ArrayList<>();
                imageMap = new HashMap();
                lrcMap = new HashMap();
                songUrl = new ArrayList<>();
                songUrl = intent.getStringArrayListExtra("歌曲地址");
                for (int i = 0; i < songUrl.size(); i++) {
                    musicData.add(startVolley(songUrl.get(i), new Mp3Info(), i));
                }
            }
            if (musicData.get(mposition).getUrl() != null) {
                play(mposition);

            }

        }
    }
    class MakeMusicToProgressBR extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (mediaPlayer!=null&&mediaPlayer.isPlaying()){
                mediaPlayer.seekTo(intent.getIntExtra(MUSIC_PROGRESS,progress));
            }
        }
    }

    class getMusicBR extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            musicData = new ArrayList<>();
            musicData = intent.getParcelableArrayListExtra("本地音乐");
            mposition = intent.getIntExtra("第几首", 0);
            play(mposition);
        }
    }
    class getListMusic extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            mposition = intent.getIntExtra("第几首",0);
            if (musicData!=null){
                play(mposition);
            }
        }
    }

    private Mp3Info startVolley(String url, final Mp3Info musicBean, final int mposition) {
        NetHelper.myRequest(url, new MyNetListener<String>() {
            @Override
            public void successListener(String response) {
                response = response.substring(1, response.length() - 2);
                Gson gson = new Gson();
                SongUrlBean bean = gson.fromJson(response, SongUrlBean.class);
                musicBean.setUrl(bean.getBitrate().getFile_link());
                musicBean.setSize(0);
                musicBean.setDuration(0);
                musicBean.setId(Integer.parseInt(bean.getSonginfo().getSong_id()));
                musicBean.setTitle(bean.getSonginfo().getTitle());
                musicBean.setArtist(bean.getSonginfo().getAuthor());
                musicBean.setAlbum("");
                musicBean.setAlbumId(0);
                imageMap.put(mposition, bean.getSonginfo().getPic_premium());
                lrcMap.put(mposition, bean.getSonginfo().getLrclink());
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
        return musicBean;
    }


}
