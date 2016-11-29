package com.example.dllo.project_a_cst.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.bean.RecommendBean;
import com.example.dllo.project_a_cst.my_class.GlideImageloader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/25.
 */

public class RecommendAdapter extends RecyclerView.Adapter{
    private Context context;
    private ArrayList<RecommendBean> data;
    private int[] MainType,countType={1,2,3,4,5,6,7,8,9};
    private ArrayList<String> url;


    public RecommendAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<RecommendBean> data) {
        this.data = data;
        url = new ArrayList<>();
        url.add(data.get(0).getResult().getFocus().getResult().get(0).getRandpic());
        url.add(data.get(0).getResult().getFocus().getResult().get(1).getRandpic());
        url.add(data.get(0).getResult().getFocus().getResult().get(2).getRandpic());
        url.add(data.get(0).getResult().getFocus().getResult().get(3).getRandpic());
        url.add(data.get(0).getResult().getFocus().getResult().get(4).getRandpic());
        notifyDataSetChanged();
    }

    public void setMainType(int[] mainType) {
        MainType = mainType;
    }


    @Override
    public int getItemViewType(int position) {
        return MainType[position];
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case 1:
                View viewOne = LayoutInflater.from(context).inflate(R.layout.item_recommend_fragment_type_one,parent,false);
                holder = new MyFirstHolder(viewOne);
                break;
            case 2:
                View viewTwo = LayoutInflater.from(context).inflate(R.layout.item_recommend_fragment_type_two,parent,false);
                holder = new MySecondHolder(viewTwo);
                break;
            case 3:
                View viewThree = LayoutInflater.from(context).inflate(R.layout.item_recommend_fragment_type_three,parent,false);
                holder = new MyThirdHolder(viewThree);
                break;
            case 4:
                View viewFour = LayoutInflater.from(context).inflate(R.layout.item_recommend_fragment_type_four,parent,false);
                holder = new MyFourthHolder(viewFour);
                break;
            case 5:
                View viewFive = LayoutInflater.from(context).inflate(R.layout.item_recommend_fragment_type_five,parent,false);
                holder = new MyFifthHolder(viewFive);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = countType[position];
        switch (type){
            // 轮播图和四个图标
            case 1:
                ((MyFirstHolder)holder).banner.setImages(url)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImageLoader(new GlideImageloader())
                .setBannerAnimation(Transformer.DepthPage)
                .isAutoPlay(true)
                .setDelayTime(5000)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();
                Glide.with(context).load(data.get(0).getResult().getEntry().getResult().get(0).getIcon())
                .into(((MyFirstHolder)holder).ivOne);
                Glide.with(context).load(data.get(0).getResult().getEntry().getResult().get(1).getIcon())
                        .into(((MyFirstHolder)holder).ivTwo);
                Glide.with(context).load(data.get(0).getResult().getEntry().getResult().get(2).getIcon())
                        .into(((MyFirstHolder)holder).ivThree);
                Glide.with(context).load(data.get(0).getResult().getEntry().getResult().get(3).getIcon())
                        .into(((MyFirstHolder)holder).ivFour);
                Log.d("RecommendAdapter", "轮播图和四个图标");
                break;
            // 歌单推荐
            case 2:
                Glide.with(context).load(data.get(0).getModule().get(3).getPicurl()).into(((MySecondHolder)holder).iv);
                ((MySecondHolder)holder).tv.setText("歌单推荐");
                RecommendTypeTwoAdapter twoAdapter = new RecommendTypeTwoAdapter(context);
                twoAdapter.setData(data);
                int[] twoType = {1,1,1,1,1,1};
                twoAdapter.setTYPE(twoType);
                ((MySecondHolder)holder).rv.setAdapter(twoAdapter);
                ((MySecondHolder)holder).rv.setLayoutManager(new GridLayoutManager(context,3));
                Log.d("RecommendAdapter", "歌单推荐");
                break;
            // 新碟上架
            case 3:
                Glide.with(context).load(data.get(0).getModule().get(5).getPicurl()).into(((MySecondHolder)holder).iv);
                ((MySecondHolder)holder).tv.setText("新碟上架");
                RecommendTypeThreeAdapter threeAdapter = new RecommendTypeThreeAdapter(context);
                threeAdapter.setData(data);
                int[] threeType = {1,1,1,1,1,1};
                threeAdapter.setTYPE(threeType);
                ((MySecondHolder)holder).rv.setAdapter(threeAdapter);
                ((MySecondHolder)holder).rv.setLayoutManager(new GridLayoutManager(context,3));
                Log.d("RecommendAdapter", "新碟上架");
                break;
            // 热销专辑
            case 4:
                Glide.with(context).load(data.get(0).getModule().get(6).getPicurl()).into(((MyThirdHolder)holder).iv);
                ((MyThirdHolder)holder).tv.setText("热销专辑");
                RecommendTypeThreeAdapter fourAdapter = new RecommendTypeThreeAdapter(context);
                fourAdapter.setData(data);
                int[] fourType = {3,3,3};
                fourAdapter.setTYPE(fourType);
                ((MyThirdHolder)holder).rv.setAdapter(fourAdapter);
                ((MyThirdHolder)holder).rv.setLayoutManager(new GridLayoutManager(context,3));
                Log.d("RecommendAdapter", "热销专辑");
                break;
            // 广告
            case 5:
                Glide.with(context).load(data.get(0).getResult().getMod_26().getResult().get(0).getPic())
                        .into(((MyFourthHolder)holder).iv);
                Log.d("RecommendAdapter", "广告");
                break;
            // 原创音乐
            case 6:
                Glide.with(context).load(data.get(0).getModule().get(10).getPicurl()).into(((MyThirdHolder)holder).iv);
                ((MyThirdHolder)holder).tv.setText("原创音乐");
                RecommendTypeTwoAdapter sixAdapter = new RecommendTypeTwoAdapter(context);
                sixAdapter.setData(data);
                int[] sixType = {3,3,3};
                sixAdapter.setTYPE(sixType);
                ((MyThirdHolder)holder).rv.setAdapter(sixAdapter);
                ((MyThirdHolder)holder).rv.setLayoutManager(new GridLayoutManager(context,3));
                Log.d("RecommendAdapter", "原创音乐");
                break;
            // 最热MV推荐
            case 7:
                Glide.with(context).load(data.get(0).getModule().get(11).getPicurl()).into(((MyThirdHolder)holder).iv);
                ((MyThirdHolder)holder).tv.setText("最热MV推荐");
                RecommendTypeThreeAdapter sevenAdapter = new RecommendTypeThreeAdapter(context);
                sevenAdapter.setData(data);
                int[] sevenType = {2,2,2,2,2,2};
                sevenAdapter.setTYPE(sevenType);
                ((MyThirdHolder)holder).rv.setAdapter(sevenAdapter);
                ((MyThirdHolder)holder).rv.setLayoutManager(new GridLayoutManager(context,3));
                Log.d("RecommendAdapter", "最热MV推荐");
                break;
            // 乐播节目
            case 8:
                Glide.with(context).load(data.get(0).getModule().get(12).getPicurl()).into(((MySecondHolder)holder).iv);
                ((MySecondHolder)holder).tv.setText("乐播节目");
                RecommendTypeTwoAdapter eightAdapter = new RecommendTypeTwoAdapter(context);
                eightAdapter.setData(data);
                int[] eightType = {2,2,2,2,2,2};
                eightAdapter.setTYPE(eightType);
                ((MySecondHolder)holder).rv.setAdapter(eightAdapter);
                ((MySecondHolder)holder).rv.setLayoutManager(new GridLayoutManager(context,3));
                Log.d("RecommendAdapter", "乐播节目");
                break;
            // 专栏
            case 9:
                Glide.with(context).load(data.get(0).getModule().get(13).getPicurl()).into(((MyFifthHolder)holder).iv);
                ((MyFifthHolder)holder).tv.setText("专栏");
                RecommendTypeFiveAdapter nineAdapter = new RecommendTypeFiveAdapter(context);
                nineAdapter.setData(data);
                ((MyFifthHolder)holder).rv.setAdapter(nineAdapter);
                ((MyFifthHolder)holder).rv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                Log.d("RecommendAdapter", "专栏");
                break;

        }
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    class MyFirstHolder extends RecyclerView.ViewHolder {
        private ImageView ivOne,ivTwo,ivThree,ivFour;
        private Banner banner;
        public MyFirstHolder(View itemView) {
            super(itemView);
            ivOne = (ImageView) itemView.findViewById(R.id.iv_type_one_singer);
            ivTwo = (ImageView) itemView.findViewById(R.id.iv_type_one_music_fenlei);
            ivThree = (ImageView) itemView.findViewById(R.id.iv_type_one_diantai);
            ivFour = (ImageView) itemView.findViewById(R.id.iv_type_one_vip);
            banner = (Banner) itemView.findViewById(R.id.banner_type_one);
        }
    }
    class MySecondHolder extends RecyclerView.ViewHolder{
        private RecyclerView rv;
        private ImageView iv;
        private TextView tv;
        public MySecondHolder(View itemView) {
            super(itemView);
            rv = (RecyclerView) itemView.findViewById(R.id.recycler_view_type_two);
            iv = (ImageView) itemView.findViewById(R.id.iv_type_two);
            tv = (TextView) itemView.findViewById(R.id.tv_type_two);
        }
    }
    class MyThirdHolder extends RecyclerView.ViewHolder{
        private RecyclerView rv;
        private ImageView iv;
        private TextView tv;
        public MyThirdHolder(View itemView) {
            super(itemView);
            rv = (RecyclerView) itemView.findViewById(R.id.recycler_view_type_three);
            iv = (ImageView) itemView.findViewById(R.id.iv_type_three);
            tv = (TextView) itemView.findViewById(R.id.tv_type_three);
        }
    }
    class MyFourthHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        public MyFourthHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_type_four);
        }
    }
    class MyFifthHolder extends RecyclerView.ViewHolder{
        private RecyclerView rv;
        private ImageView iv;
        private TextView tv;
        public MyFifthHolder(View itemView) {
            super(itemView);
            rv = (RecyclerView) itemView.findViewById(R.id.recycler_view_type_five);
            iv = (ImageView) itemView.findViewById(R.id.iv_type_five);
            tv = (TextView) itemView.findViewById(R.id.tv_type_five);
        }
    }
}
