package com.example.dllo.project_a_cst.main_activity_fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
    private String lrc;
    private boolean isPlaying;
    private Timer mTimer;
    private TimerTask mTask;
    private int progress ;
    private getLrcBR mGetLrcBR;
    private getMusicIsPlay mGetMusicIsPlay;
    private getMusicProgressBR mGetMusicProgressBR;

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
        mGetLrcBR = new getLrcBR();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("歌词");
        getActivity().registerReceiver(mGetLrcBR,intentFilter);

        mGetMusicIsPlay = new getMusicIsPlay();
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction("音乐是否播放");
        getActivity().registerReceiver(mGetMusicIsPlay,intentFilter1);

        mGetMusicProgressBR = new getMusicProgressBR();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("音乐播放进度");
        getActivity().registerReceiver(mGetMusicProgressBR,intentFilter2);
    }

    class getLrcBR extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
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
                }

                @Override
                public void errorListener(VolleyError error) {

                }
            });
        }
    }

    class getMusicIsPlay extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            isPlaying = intent.getBooleanExtra("音乐是否播放", false);
            if (isPlaying) {
                ILrcBuilder builder = new DefaultLrcBuilder();
                List<LrcRow> rows = builder.getLrcRows(lrc);
                if (rows.size() > 0) {
                    mLrcView.setLrc(rows);
                    if(mTimer == null){
                        mTimer = new Timer();
                        mTask = new LrcTask();
                        mTimer.scheduleAtFixedRate(mTask, 0, 500);
                    }
                }
            }
        }
    }
    class getMusicProgressBR extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            progress = intent.getIntExtra("进度",0);
            isPlaying = intent.getBooleanExtra("播放状态",false);
        }
    }

    class LrcTask extends TimerTask{

        @Override
        public void run() {
            final long timePassed = progress;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLrcView.seekLrcToTime(timePassed);
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mGetLrcBR);
        getActivity().unregisterReceiver(mGetMusicProgressBR);
        getActivity().unregisterReceiver(mGetMusicIsPlay);
    }
}
