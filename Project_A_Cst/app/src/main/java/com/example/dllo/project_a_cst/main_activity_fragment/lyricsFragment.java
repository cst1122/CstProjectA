package com.example.dllo.project_a_cst.main_activity_fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;

import com.android.volley.VolleyError;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.else_class.MyNetListener;
import com.example.dllo.project_a_cst.else_class.NetHelper;
import com.example.dllo.project_a_cst.my_class.DefaultLrcBuilder;
import com.example.dllo.project_a_cst.my_class.ILrcBuilder;
import com.example.dllo.project_a_cst.my_class.LrcRow;
import com.example.dllo.project_a_cst.my_class.LrcView;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dllo on 16/11/30.
 */

public class lyricsFragment extends BaseFragment {
    private LrcView mLrcView;
    private static String lrc;
    private String lrcUrl;
    private static boolean isPlaying;
    private Timer mTimer;
    private TimerTask mTask;
    private static int progress;
    private getMusicIsPlay mGetMusicIsPlay;
    private getMusicProgressBR mGetMusicProgressBR;

    public void setLrcUrl(String lrcUrl) {
        this.lrcUrl = lrcUrl;
    }

    @Override
    public int setlayout() {
        return R.layout.fragment_lyrics;
    }

    @Override
    public void initView(View view) {
        mLrcView = bindView(R.id.lrc_view);
    }

    @Override
    public void initData() {

        if (lrcUrl!=null){
            Log.d("1123", lrcUrl);
            NetHelper.myRequest(lrcUrl, new MyNetListener<String>() {
                @Override
                public void successListener(String response) {
                    String praseResult = null;
                    try {
                        if (java.nio.charset.Charset.forName("ISO-8859-1").newEncoder().canEncode(response)) {
                            praseResult = new String(response.getBytes("ISO8859-1"), "utf-8"); // 解决中文乱码问题
                        } else {
                            praseResult = response;
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    lrc = praseResult;
                    ILrcBuilder builder = new DefaultLrcBuilder();
                    List<LrcRow> rows = builder.getLrcRows(lrc);
                    Log.d("1122", lrc);
                    if (rows.size() > 0) {
                        mLrcView.setLrc(rows);
//                        if (mTimer == null) {
//                            mTimer = new Timer();
//                            mTask = new LrcTask();
//                            mTimer.scheduleAtFixedRate(mTask, 0, 500);
//                        }
                    }
                }

                @Override
                public void errorListener(VolleyError error) {

                }
            });
        }
        mGetMusicIsPlay = new getMusicIsPlay();
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction("音乐是否播放");
        getActivity().registerReceiver(mGetMusicIsPlay, intentFilter1);

        mGetMusicProgressBR = new getMusicProgressBR();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("音乐播放进度");
        getActivity().registerReceiver(mGetMusicProgressBR, intentFilter2);


    }


    class getMusicIsPlay extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("1122", isPlaying + "@!$%!%@!");
            isPlaying = intent.getBooleanExtra("音乐是否播放", false);
            Log.d("1122", "歌词");
            String url = intent.getStringExtra("歌词网址");
            NetHelper.myRequest(url, new MyNetListener<String>() {
                @Override
                public void successListener(String response) {
                    String praseResult = null;
                    try {
                        if (java.nio.charset.Charset.forName("ISO-8859-1").newEncoder().canEncode(response)) {
                            praseResult = new String(response.getBytes("ISO8859-1"), "utf-8"); // 解决中文乱码问题
                        } else {
                            praseResult = response;
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    lrc = praseResult;
                    ILrcBuilder builder = new DefaultLrcBuilder();
                    List<LrcRow> rows = builder.getLrcRows(lrc);
                    Log.d("1122", lrc);
                    if (rows.size() > 0) {
                        mLrcView.setLrc(rows);
//                        if (mTimer == null) {
//                            mTimer = new Timer();
//                            mTask = new LrcTask();
//                            mTimer.scheduleAtFixedRate(mTask, 0, 500);
//                        }
                    }
                }

                @Override
                public void errorListener(VolleyError error) {

                }
            });
        }
    }

    class getMusicProgressBR extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            progress = intent.getIntExtra("进度", 0);
            mLrcView.seekLrcToTime(progress);
            isPlaying = intent.getBooleanExtra("播放状态", false);
        }
    }

    class LrcTask extends TimerTask {

        @Override
        public void run() {
            final long timePassed = progress;
            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getActivity().unregisterReceiver(mGetMusicProgressBR);
        getActivity().unregisterReceiver(mGetMusicIsPlay);
    }
}
