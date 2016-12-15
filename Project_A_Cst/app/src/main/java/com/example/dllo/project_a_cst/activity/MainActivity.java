package com.example.dllo.project_a_cst.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baidu.frontia.Frontia;
import com.bumptech.glide.Glide;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.else_class.MediaplayerListPop;
import com.example.dllo.project_a_cst.main_activity_fragment.MainFragment;
import com.example.dllo.project_a_cst.main_activity_fragment.MyMusicLIstFragment;
import com.example.dllo.project_a_cst.main_activity_fragment.RecommendMusicFragment;
import com.example.dllo.project_a_cst.my_class.Mp3Info;
import com.example.dllo.project_a_cst.my_database.DBTool;
import com.example.dllo.project_a_cst.my_database.MyMusicPerson;
import com.example.dllo.project_a_cst.service.MusicService;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

import static com.example.dllo.project_a_cst.my_class.MyConstants.IS_MUSIC_PLAYING;
import static com.example.dllo.project_a_cst.my_class.MyConstants.LRC_URL;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_DURATION;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_ID;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_LRC;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_NAME;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PAUSE;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PICTURE;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PLAY_NEXT;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_PROGRESS;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MUSIC_TIME;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MY_MUSIC_LIST_LEFT;
import static com.example.dllo.project_a_cst.my_class.MyConstants.MY_MUSIC_LIST_RIGHT;
import static com.example.dllo.project_a_cst.my_class.MyConstants.PICTURE;
import static com.example.dllo.project_a_cst.my_class.MyConstants.PICTURE_URL;
import static com.example.dllo.project_a_cst.my_class.MyConstants.SINGER;

public class MainActivity extends SupportActivity implements View.OnClickListener {
    private DrawerLayout mineDrawerLayout;
    private RecyclerView recyclerView;
    private ImageView ivPlayMusic, ivPlayNext, ivMusicMenu, ivMusicImage;
    private ProgressBar progressBar;
    private Intent mIntent;
    private TextView tvSongName, tvSinger;
    private LinearLayout linearLayoutDown;
    private MediaplayerListPop pop;
    private MySongListBR mMySongListBR;
    private getMusicInformationBR mGetMusicInformationBR;
    private getMusicProgressBR mGetMusicProgressBR;
    private getMusicIdBR mGetMusicIdBR;
    private getMusicLrcBR mGetMusicLrcBR;
    private MyGeDanBr mMyGeDanBr;
    private static ArrayList<Mp3Info> musicData = new ArrayList<>();
    private boolean isPlaying = false;
    private Intent mStartMService;
    private static String musicId;
    private static String musicLrc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // 第三方登录
        Frontia.init(this.getApplicationContext(),"AZYoLNqHix6EoGR0vyUq1ZHQ");


        //当Activity创建时 加载一个supportFragment
        if (savedInstanceState == null) {
            loadRootFragment(R.id.rl_fragment, new MainFragment());
        }
        this.setFragmentAnimator(new FragmentAnimator(R.anim.h_fragment_enter, R.anim.h_fragment_exit, R.anim.h_fragment_pop_enter, R.anim.h_fragment_pop_exit));

        initView();
        initData();

        ivPlayMusic.setOnClickListener(this);
        ivPlayNext.setOnClickListener(this);
        ivMusicMenu.setOnClickListener(this);
        linearLayoutDown.setOnClickListener(this);

        // 抽屉的监听事件
        mineDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mineDrawerLayout.setVisibility(View.GONE);
                recyclerView.destroyDrawingCache();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


    }

    private void initView() {
        mineDrawerLayout = (DrawerLayout) findViewById(R.id.dl_mine);
        recyclerView = (RecyclerView) findViewById(R.id.main_drawerlayout_recyvlerview);
        ivPlayMusic = (ImageView) findViewById(R.id.iv_main_play);
        ivPlayNext = (ImageView) findViewById(R.id.iv_main_play_next);
        ivMusicMenu = (ImageView) findViewById(R.id.iv_main_music_menu);
        tvSongName = (TextView) findViewById(R.id.tv_main_song_name);
        tvSinger = (TextView) findViewById(R.id.tv_main_singer);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_main);
        linearLayoutDown = (LinearLayout) findViewById(R.id.linearlayout_main_down);
        ivMusicImage = (ImageView) findViewById(R.id.iv_main_music_image);
    }

    private void initData() {
//        musicData = (ArrayList<Mp3Info>) MusicUtil.getMp3Infos(this);
        mStartMService = new Intent();
        mStartMService.setClass(getApplicationContext(), MusicService.class);
//        mStartMService.putParcelableArrayListExtra("音乐数据",musicData);
        startService(mStartMService);

        mMySongListBR = new MySongListBR();
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction("开启榜单");
        registerReceiver(mMySongListBR, intentFilter1);

        mMyGeDanBr = new MyGeDanBr();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("歌单推荐");
        registerReceiver(mMyGeDanBr, intentFilter2);

        mGetMusicInformationBR = new getMusicInformationBR();
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("歌曲信息");
        registerReceiver(mGetMusicInformationBR, intentFilter3);

        mGetMusicProgressBR = new getMusicProgressBR();
        IntentFilter intentFilter4 = new IntentFilter();
        intentFilter4.addAction("音乐播放进度");
        registerReceiver(mGetMusicProgressBR, intentFilter4);

        mGetMusicIdBR = new getMusicIdBR();
        IntentFilter intentFilter5 = new IntentFilter();
        intentFilter5.addAction("歌曲地址集合");
        registerReceiver(mGetMusicIdBR, intentFilter5);

        mGetMusicLrcBR = new getMusicLrcBR();
        IntentFilter intentFilter6 = new IntentFilter();
        intentFilter6.addAction(IS_MUSIC_PLAYING);
        registerReceiver(mGetMusicLrcBR, intentFilter6);

        View.OnClickListener itemsOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
        pop = new MediaplayerListPop(this, itemsOnClick);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_main_play:
                if (!isPlaying) {
                    mIntent = new Intent(MUSIC_PLAY);
                } else {
                    mIntent = new Intent(MUSIC_PAUSE);
                }
                sendBroadcast(mIntent);
                break;
            case R.id.iv_main_play_next:
                mIntent = new Intent(MUSIC_PLAY_NEXT);
                sendBroadcast(mIntent);
                break;
            // 播放列表
            case R.id.iv_main_music_menu:
                pop.setContext(this);
                List<MyMusicPerson> data;
                data = DBTool.getInstance().queryMusicAll();
                List<MyMusicPerson> musicData = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    MyMusicPerson bean = new MyMusicPerson();
                    bean.setTitle(data.get(i).getTitle());
                    bean.setArtist(data.get(i).getArtist());
                    if (data.get(i).getId() != null) {
                        bean.setMusicId(data.get(i).getId());
                    } else {
                        bean.setMusicId(0);
                    }
                    bean.setUrl(data.get(i).getUrl());
                    musicData.add(bean);
                }
                pop.setData((ArrayList<MyMusicPerson>) musicData);
                pop.showAsDropDown(linearLayoutDown);
                break;
            // mediaPlayerActivity
            case R.id.linearlayout_main_down:
                Intent intent = new Intent(MainActivity.this, MediaPlayerActivity.class);
                intent.putExtra(SINGER, tvSinger.getText().toString());
                intent.putExtra(MUSIC_NAME, tvSongName.getText().toString());
                intent.putExtra(MUSIC_TIME, progressBar.getMax());
                intent.putExtra(MUSIC_ID, musicId);
                if (musicLrc != null) {
                    intent.putExtra(MUSIC_LRC, musicLrc);
                }
                ivMusicImage.setDrawingCacheEnabled(true);
                Bitmap bitmap = ivMusicImage.getDrawingCache();
                intent.putExtra(PICTURE, bitmap);
                startActivity(intent);
                ivMusicImage.setDrawingCacheEnabled(false);
                break;
        }
    }

    // 设置底部播放按钮图片的方法
    public void setPlayMusicIv() {
        if (isPlaying) {
            ivPlayMusic.setImageResource(R.mipmap.bt_minibar_pause_normal);
        } else {
            ivPlayMusic.setImageResource(R.mipmap.bt_minibar_play_normal);
        }
    }

    // 广播接收

    // 榜单网络音乐播放
    class MySongListBR extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String url = MY_MUSIC_LIST_LEFT + intent.getIntExtra("type", 1) + MY_MUSIC_LIST_RIGHT;
            String imageUrl = intent.getStringExtra(PICTURE_URL);
            MyMusicLIstFragment myMusicLIstFragment = new MyMusicLIstFragment();
            myMusicLIstFragment.setUrl(url);
            myMusicLIstFragment.setImageUrl(imageUrl);
            start(myMusicLIstFragment);
        }
    }

    // 歌单
    class MyGeDanBr extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            RecommendMusicFragment recommendMusicFragment = new RecommendMusicFragment();
            start(recommendMusicFragment);
        }
    }

    // 获取音乐信息
    class getMusicInformationBR extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            tvSongName.setText(intent.getStringExtra(MUSIC_NAME));
            tvSinger.setText(intent.getStringExtra(SINGER));
            progressBar.setMax(intent.getIntExtra(MUSIC_DURATION, 0));
            if (intent.getParcelableExtra(MUSIC_PICTURE) != null) {
                ivMusicImage.setImageBitmap((Bitmap) intent.getParcelableExtra(MUSIC_PICTURE));
            } else {
                Glide.with(MainActivity.this).load(intent.getStringExtra(PICTURE_URL)).into(ivMusicImage);
            }
            musicData = intent.getParcelableArrayListExtra("歌曲集合");
        }
    }

    // 获取进度
    class getMusicProgressBR extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            progressBar.setProgress(intent.getIntExtra(MUSIC_PROGRESS, 0));
            isPlaying = intent.getBooleanExtra("播放状态", false);
            setPlayMusicIv();
        }
    }


    class getMusicIdBR extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            musicId = intent.getStringExtra(MUSIC_ID);
        }
    }

    //歌词
    class getMusicLrcBR extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            musicLrc = intent.getStringExtra(LRC_URL);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMySongListBR);
        unregisterReceiver(mMyGeDanBr);
        unregisterReceiver(mGetMusicInformationBR);
        unregisterReceiver(mGetMusicProgressBR);
        unregisterReceiver(mGetMusicIdBR);
        unregisterReceiver(mGetMusicLrcBR);
        stopService(mStartMService);
    }
}
