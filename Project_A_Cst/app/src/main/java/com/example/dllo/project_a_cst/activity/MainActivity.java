package com.example.dllo.project_a_cst.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.adapter.DrawerRecyclerAdapter;
import com.example.dllo.project_a_cst.adapter.MainTabAdapter;
import com.example.dllo.project_a_cst.bean.MusicBean;
import com.example.dllo.project_a_cst.else_class.MediaplayerListPop;
import com.example.dllo.project_a_cst.main_activity_fragment.BearingFragment;
import com.example.dllo.project_a_cst.main_activity_fragment.LiveFragment;
import com.example.dllo.project_a_cst.main_activity_fragment.MineFragment;
import com.example.dllo.project_a_cst.main_activity_fragment.MusicFragment;
import com.example.dllo.project_a_cst.my_class.MyMusicPlayClass;
import com.example.dllo.project_a_cst.service.MusicService;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private DrawerLayout mineDrawerLayout;
    private ImageButton btnMore, btnSearch;
    private ArrayList<Fragment> data;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private RecyclerView recyclerView;
    private ImageView ivPlayMusic, ivPlayNext, ivMusicMenu;
    private LinearLayoutManager manager
            = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    private ProgressBar progressBar;
    private ServiceConnection connection;
    private Intent mIntent;
    private MusicService.MyMusicBinder myMusicBinder;
    private TextView tvSongName, tvSinger;
    private MyMusicBR myMusicBR;
    private boolean flag = true;
    private LinearLayout linearLayoutDown;
    private MyMusicPlayClass myMusicPlayClass;
    private MediaplayerListPop pop;

    @Override
    int setlaouyt() {
        return R.layout.activity_main;
    }

    @Override
    void initView() {
        mineDrawerLayout = bindView(R.id.dl_mine);
        btnMore = bindView(R.id.btn_more);
        viewPager = bindView(R.id.viewpager_main_activity);
        tabLayout = bindView(R.id.tablayout_main_activity);
        recyclerView = bindView(R.id.main_drawerlayout_recyvlerview);
        btnSearch = bindView(R.id.btn_search);
        ivPlayMusic = bindView(R.id.iv_main_play);
        ivPlayNext = bindView(R.id.iv_main_play_next);
        ivMusicMenu = bindView(R.id.iv_main_music_menu);
        tvSongName = bindView(R.id.tv_main_song_name);
        tvSinger = bindView(R.id.tv_main_singer);
        progressBar = bindView(R.id.progressbar_main);
        linearLayoutDown = bindView(R.id.linearlayout_main_down);

    }

    @Override
    void initData() {

        data = new ArrayList<>();
        data.add(new MineFragment());
        data.add(new MusicFragment());
        data.add(new BearingFragment());
        data.add(new LiveFragment());
        MainTabAdapter adapter = new MainTabAdapter(getSupportFragmentManager(), data);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.rgb(0x99, 0xe1, 0xff), Color.WHITE);
        btnMore.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
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



        // 底部音乐播放
        mIntent = new Intent(this, MusicService.class);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myMusicBinder = (MusicService.MyMusicBinder) service;
                myMusicPlayClass = myMusicBinder.getMyMusicPlayClass();
                if (myMusicBinder!=null){
                    progressBar.setMax(myMusicPlayClass.max());
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };


        myMusicBR = new MyMusicBR();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("MUSIC_BR");
        registerReceiver(myMusicBR, intentFilter);
        bindService(mIntent, connection, BIND_AUTO_CREATE);
        pop = new MediaplayerListPop(this,itemsOnClick);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_main_play:
                if (myMusicBinder!=null){
                    if (flag){
                        ivPlayMusic.setImageResource(R.mipmap.bt_minibar_pause_normal);
                        myMusicPlayClass.play();
                        Log.d("MainActivity", "play");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (true){
                                    progressBar.setProgress(myMusicPlayClass.progress());
                                }
                            }
                        }).start();
                        flag = false;
                    }else {
                        Log.d("MainActivity", "pause");
                        myMusicPlayClass.pause();
                        setPlayMusicIv();
                    }

                }

                break;
            case R.id.iv_main_play_next:
                if (myMusicBinder!=null){
                    myMusicPlayClass.playNext();
                    ivPlayMusic.setImageResource(R.mipmap.bt_minibar_pause_normal);
                }

                break;
            // 播放列表
            case R.id.iv_main_music_menu:
                pop.showAsDropDown(linearLayoutDown);
                break;
            // 更多按钮的抽屉
            case R.id.btn_more:
                startDrawer("更多");
                break;
            // 搜索按钮的抽屉
            case R.id.btn_search:
                startDrawer("搜索");
                break;
            // 弹出pop
            case R.id.linearlayout_main_down:
//                pop = new MediaplayerListPop(this,itemsOnClick);
//                pop.showAtLocation(this.findViewById(R.id.activity_main),Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
                Intent intent = new Intent(MainActivity.this,MediaPlayerActivity.class);
                startActivity(intent);
                break;
        }
    }
    // 设置底部播放按钮图片的方法
    public void setPlayMusicIv(){
        if (myMusicPlayClass.musicIsPlay()){
            ivPlayMusic.setImageResource(R.mipmap.bt_minibar_pause_normal);
        }else {
            ivPlayMusic.setImageResource(R.mipmap.bt_minibar_play_normal);
        }
    }
    // 开启抽屉的方法
    public void startDrawer (String type) {
        DrawerRecyclerAdapter moreAdapter = new DrawerRecyclerAdapter(context);
        moreAdapter.setType(type);
        recyclerView.setAdapter(moreAdapter);
        recyclerView.setLayoutManager(manager);
        mineDrawerLayout.setVisibility(View.VISIBLE);
        mineDrawerLayout.openDrawer(Gravity.RIGHT);
    }

    // pop的监听事件
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_music_pop_play_mode:

                    break;
                case R.id.iv_music_pop_previous:
                    if (myMusicBinder!=null){
                        Toast.makeText(context, "213", Toast.LENGTH_SHORT).show();
                        myMusicPlayClass.playLast();
                    }
                    break;
                case R.id.iv_music_pop_next:
                    if (myMusicBinder!=null){
                        myMusicPlayClass.playNext();
                    }
                    break;
                case R.id.iv_music_pop_play_pause:
                    if (myMusicBinder!=null){
                        if (flag){
                            myMusicPlayClass.play();
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    while (true){
                                        progressBar.setProgress(myMusicPlayClass.progress());
                                    }
                                }
                            }).start();
                            flag = false;
                        }else {
                            myMusicPlayClass.pause();
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
    };

    // 广播接收
    class MyMusicBR extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (myMusicBinder != null) {
                MusicBean bean = intent.getParcelableExtra("key");
                tvSongName.setText(bean.getMusicName());
                tvSinger.setText(bean.getSingerName());
                progressBar.setMax(myMusicPlayClass.max());
                Log.d("MainActivity", "progressBar.getMax():" + progressBar.getMax());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myMusicBR);
        unbindService(connection);
    }
}
