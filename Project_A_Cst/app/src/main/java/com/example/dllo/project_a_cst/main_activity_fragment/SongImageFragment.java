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

public class SongImageFragment extends BaseFragment{
    private ImageView iv;
    private TextView tvOne,tvTwo;
    private String name,singer;
    private Bitmap mBitmap;
    private getMusicInformationBR mGetMusicInformationBR;

    public void setName(String name) {
        this.name = name;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    @Override
    public int setlayout() {
        return R.layout.fragment_song_image;
    }

    @Override
    public void initView(View view) {
        iv = bindView(R.id.iv_fragment_song_image);
        tvOne = bindView(R.id.tv_fragment_song_image_name);
        tvTwo = bindView(R.id.tv_fragment_song_image_singer);
    }

    @Override
    public void initData() {
        iv.setImageBitmap(mBitmap);
        tvOne.setText(name);
        tvTwo.setText(singer);
        mGetMusicInformationBR = new getMusicInformationBR();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("歌曲信息");
        getActivity().registerReceiver(mGetMusicInformationBR,intentFilter);

    }
    class getMusicInformationBR extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            tvOne.setText(intent.getStringExtra("歌曲名"));
            tvTwo.setText(intent.getStringExtra("歌手"));
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
