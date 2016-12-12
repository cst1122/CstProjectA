package com.example.dllo.project_a_cst.main_activity_fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.project_a_cst.R;

/**
 * Created by dllo on 16/11/30.
 */

public class SongMsgFragment extends BaseFragment{
    private ImageView iv;
    private TextView tvOne,tvTwo;
    private Bitmap mBitmap;
    private String singer,name;
    private getMusicInformationBR mGetMusicInformationBR;

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int setlayout() {
        return R.layout.fragment_song_msg;
    }

    @Override
    public void initView(View view) {
        iv = bindView(R.id.iv_fragment_msg);
        tvOne = bindView(R.id.tv_fragment_msg_singer);
        tvTwo = bindView(R.id.tv_fragment_msg_name);
    }

    @Override
    public void initData() {
        mGetMusicInformationBR = new getMusicInformationBR();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("歌曲信息");
        getActivity().registerReceiver(mGetMusicInformationBR,intentFilter);

        iv.setImageBitmap(mBitmap);
        tvOne.setText("歌手:  "+singer);
        tvTwo.setText("专辑:  "+name);
    }
    class getMusicInformationBR extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            tvTwo.setText("专辑:  "+intent.getStringExtra("歌曲名"));
            tvOne.setText("歌手:  "+intent.getStringExtra("歌手"));
            if (intent.getParcelableExtra("歌曲图片")!=null) {
                iv.setImageBitmap((Bitmap) intent.getParcelableExtra("歌曲图片"));
            }else {
                Glide.with(getActivity()).load(intent.getStringExtra("图片网址")).into(iv);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mGetMusicInformationBR);
    }
}
