package com.example.dllo.project_a_cst.main_activity_fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.my_class.Mp3Info;
import com.example.dllo.project_a_cst.my_class.MusicUtil;
import com.example.dllo.project_a_cst.my_database.DBTool;
import com.example.dllo.project_a_cst.my_database.MyPerson;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by dllo on 16/11/23.
 */

public class MineFragment  extends BaseFragment{
    private LinearLayout llBenD,llLike;
    private TextView tvBenD,tvXiHuan;
    private ArrayList<Mp3Info> musicData;
    private SupportActivity mSupportActivity;

    @Override
    public int setlayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(View view) {
        llBenD = bindView(R.id.ll_bendiyinyue);
        llLike = bindView(R.id.ll_xihuan);
        tvBenD = bindView(R.id.tv_bendi);
        tvXiHuan = bindView(R.id.tv_xihuan);
    }

    @Override
    public void initData() {
        mSupportActivity = (SupportActivity) getActivity();
        musicData = (ArrayList<Mp3Info>) MusicUtil.getMp3Infos(getActivity());
        tvBenD.setText(musicData.size()+"首");
        tvXiHuan.setText(DBTool.getInstance().queryAll().size()+"首");
        llBenD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BenDFragment fragment = new BenDFragment();
                fragment.setMusicData(musicData);
                mSupportActivity.start(fragment);
            }
        });
        llLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LikeFragment fragment = new LikeFragment();
                fragment.setData((ArrayList<MyPerson>) DBTool.getInstance().queryAll());
                mSupportActivity.start(fragment);
            }
        });
    }
}
