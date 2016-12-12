package com.example.dllo.project_a_cst.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
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

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PAUSE;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY_LAST;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY_NEXT;

/**
 * Created by dllo on 16/11/26.
 */

public class MediaPlayerActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton btnReturn;
    private ImageView ivPlayMode, ivPrevious, ivNext, ivPlayPause, ivMusicList, ivShouCang, ivFenXiang;
    private TextView tvFirstTime, tvSecondTime;
    private SeekBar seekBar;
    private ViewPager viewPager;
    private Intent mIntent;
    private ArrayList<Fragment> fragmentData;
    private boolean isPlaying = false;
    private getMusicProgressBR mGetMusicProgressBR;
    private getMusicInformationBR mGetMusicInformationBR;

    @Override
    int setlaouyt() {
        return R.layout.activity_media_player;
    }

    @Override
    void initView() {
        btnReturn = bindView(R.id.btn_music_pop_return);
        ivShouCang = bindView(R.id.iv_music_main_shoucang);
        ivFenXiang = bindView(R.id.iv_music_main_fenxiang);
        ivPlayMode = bindView(R.id.iv_music_pop_play_mode);
        ivPrevious = bindView(R.id.iv_music_pop_previous);
        ivNext = bindView(R.id.iv_music_pop_next);
        ivPlayPause = bindView(R.id.iv_music_pop_play_pause);
        ivMusicList = bindView(R.id.iv_music_pop_music_list);
        tvFirstTime = bindView(R.id.tv_music_pop_first_time);
        tvSecondTime = bindView(R.id.tv_music_pop_second_time);
        seekBar = bindView(R.id.seek_bar_music_pop);
        viewPager = bindView(R.id.view_pager_music_pop);
        ShareSDK.initSDK(this);
    }

    @Override
    void initData() {
        btnReturn.setOnClickListener(this);
        ivPlayMode.setOnClickListener(this);
        ivPrevious.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivPlayPause.setOnClickListener(this);
        ivMusicList.setOnClickListener(this);
        ivShouCang.setOnClickListener(this);
        ivFenXiang.setOnClickListener(this);
        tvFirstTime.setOnClickListener(this);
        tvSecondTime.setOnClickListener(this);
        fragmentData = new ArrayList<>();
        Intent intent = getIntent();
        String singer = intent.getStringExtra("歌手");
        String songName = intent.getStringExtra("歌曲名");
        int max = intent.getIntExtra("时长",0);
        Bitmap bitmap = intent.getParcelableExtra("图片");

        SongMsgFragment songMsgFragment = new SongMsgFragment();
        songMsgFragment.setBitmap(bitmap);
        songMsgFragment.setSinger(singer);
        songMsgFragment.setName(songName);
        seekBar.setMax(max);
        SongImageFragment songImageFragment = new SongImageFragment();
        songImageFragment.setName(songName);
        songImageFragment.setSinger(singer);
        songImageFragment.setBitmap(bitmap);

        lyricsFragment mLyricsFragment = new lyricsFragment();

        fragmentData.add(songMsgFragment);
        fragmentData.add(songImageFragment);
        fragmentData.add(mLyricsFragment);
        SongFragmentAdapter adapter = new SongFragmentAdapter(getSupportFragmentManager(), fragmentData);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
        viewPager.setOffscreenPageLimit(2);

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
        mGetMusicProgressBR = new getMusicProgressBR();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("音乐播放进度");
        registerReceiver(mGetMusicProgressBR,intentFilter);

        mGetMusicInformationBR = new getMusicInformationBR();
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction("歌曲信息");
        registerReceiver(mGetMusicInformationBR,intentFilter1);
    }

    public void setPlayMusicIv(){
        if (isPlaying){
            ivPlayPause.setImageResource(R.mipmap.bt_playpage_button_pause_normal_new);
        }else {
            ivPlayPause.setImageResource(R.mipmap.bt_playpage_button_play_normal_new);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_music_pop_return:
                finish();
                break;
            case R.id.iv_music_pop_play_mode:
                break;
            case R.id.iv_music_pop_previous:
                mIntent = new Intent(MUSIC_PLAY_LAST);
                sendBroadcast(mIntent);
                break;
            case R.id.iv_music_pop_next:
                mIntent = new Intent(MUSIC_PLAY_NEXT);
                sendBroadcast(mIntent);
                break;
            case R.id.iv_music_pop_play_pause:
                if (!isPlaying) {
                    mIntent = new Intent(MUSIC_PLAY);
                }else {
                    mIntent = new Intent(MUSIC_PAUSE);
                }
                sendBroadcast(mIntent);
                break;
            case R.id.iv_music_pop_music_list:

                break;
            case R.id.tv_music_pop_first_time:
                break;
            case R.id.tv_music_pop_second_time:
                break;
            case R.id.iv_music_main_shoucang:
                break;
            case R.id.iv_music_main_fenxiang:

                break;
        }
    }

    class getMusicInformationBR extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            seekBar.setMax(intent.getIntExtra("歌曲长度",0));
        }
    }

    class getMusicProgressBR extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            seekBar.setProgress(intent.getIntExtra("进度",0));
            isPlaying = intent.getBooleanExtra("播放状态",false);
            setPlayMusicIv();
        }
    }



    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();

  // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
  // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
  // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
  // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
  //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
  // url仅在微信（包括好友和朋友圈）中使用
        //oks.setUrl("http://sharesdk.cn");
  // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
  // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
  // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
  // 启动分享GUI
        oks.show(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mGetMusicInformationBR);
        unregisterReceiver(mGetMusicProgressBR);
        Log.d("MediaPlayerActivity", "销毁");
    }
}
