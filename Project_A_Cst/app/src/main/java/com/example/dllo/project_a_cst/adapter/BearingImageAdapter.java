package com.example.dllo.project_a_cst.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.bean.BearingBean;

import java.util.ArrayList;

/** 动态界面中用来加载图片的适配器  moreType 代表图片数量
 * Created by dllo on 16/11/28.
 */

public class BearingImageAdapter extends RecyclerView.Adapter {
    private ArrayList<BearingBean> data;
    private Context context;
    private int moreType;
    private int pos;

    public BearingImageAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<BearingBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void setMoreType(int moreType) {
        this.moreType = moreType;
    }

    @Override
    public int getItemViewType(int position) {
        return moreType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == 3) {
            View viewThree = LayoutInflater.from(context).inflate(R.layout.item_bearing_image_three, parent, false);
            holder = new MyFirstHolder(viewThree);
        } else if (viewType == 2) {
            View viewTwo = LayoutInflater.from(context).inflate(R.layout.item_bearing_image_two, parent, false);
            holder = new MyThirdHolder(viewTwo);
        } else {
            View viewMore = LayoutInflater.from(context).inflate(R.layout.item_bearing_image_more, parent, false);
            holder = new MySecondHolder(viewMore);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (data.get(0).getMsg().get(pos).getPiclist() != null) {
            if (moreType == 3) {
                Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(0).getPic_360()).into(((MyFirstHolder) holder).ivFirst);
                Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(1).getPic_360()).into(((MyFirstHolder) holder).ivSecond);
                Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(2).getPic_360()).into(((MyFirstHolder) holder).ivThird);
            } else if (moreType == 2) {
                Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(0).getPic_360()).into(((MyThirdHolder)holder).ivOne);
                Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(1).getPic_360()).into(((MyThirdHolder)holder).ivTwo);
            } else {
                switch (moreType){
                    case 1:
                        Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(0).getPic_360()).into(((MySecondHolder)holder).ivOnly);
                        break;
                    case 4:
                        Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(0).getPic_360()).into(((MySecondHolder)holder).ivOne);
                        Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(1).getPic_360()).into(((MySecondHolder)holder).ivTwo);
                        Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(2).getPic_360()).into(((MySecondHolder)holder).ivFour);
                        Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(3).getPic_360()).into(((MySecondHolder)holder).ivFive);
                        break;
                    case 5:
                        Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(0).getPic_360()).into(((MySecondHolder)holder).ivOne);
                        Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(1).getPic_360()).into(((MySecondHolder)holder).ivTwo);
                        Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(2).getPic_360()).into(((MySecondHolder)holder).ivThree);
                        Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(3).getPic_360()).into(((MySecondHolder)holder).ivFour);
                        Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(4).getPic_360()).into(((MySecondHolder)holder).ivFive);
                        break;
                    case 6:
                        Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(0).getPic_360()).into(((MySecondHolder)holder).ivOne);
                        Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(1).getPic_360()).into(((MySecondHolder)holder).ivTwo);
                        Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(2).getPic_360()).into(((MySecondHolder)holder).ivThree);
                        Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(3).getPic_360()).into(((MySecondHolder)holder).ivFour);
                        Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(4).getPic_360()).into(((MySecondHolder)holder).ivFive);
                        Glide.with(context).load(data.get(0).getMsg().get(pos).getPiclist().get(5).getPic_360()).into(((MySecondHolder)holder).ivSix);
                        break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class MyFirstHolder extends RecyclerView.ViewHolder {
        private ImageView ivFirst, ivSecond, ivThird;

        public MyFirstHolder(View itemView) {
            super(itemView);
            ivFirst = (ImageView) itemView.findViewById(R.id.iv_bearing_image_three_fisrt);
            ivSecond = (ImageView) itemView.findViewById(R.id.iv_bearing_image_three_second);
            ivThird = (ImageView) itemView.findViewById(R.id.iv_bearing_image_three_third);
        }
    }

    class MySecondHolder extends RecyclerView.ViewHolder {
        private ImageView ivOne, ivTwo, ivThree, ivFour, ivFive, ivSix,ivOnly;

        public MySecondHolder(View itemView) {
            super(itemView);
            ivOne = (ImageView) itemView.findViewById(R.id.iv_bearing_image_more_one);
            ivTwo = (ImageView) itemView.findViewById(R.id.iv_bearing_image_more_two);
            ivThree = (ImageView) itemView.findViewById(R.id.iv_bearing_image_more_three);
            ivFour = (ImageView) itemView.findViewById(R.id.iv_bearing_image_more_four);
            ivFive = (ImageView) itemView.findViewById(R.id.iv_bearing_image_more_five);
            ivSix = (ImageView) itemView.findViewById(R.id.iv_bearing_image_more_six);
            ivOnly = (ImageView) itemView.findViewById(R.id.iv_bearing_image_more_only);
        }
    }

    class MyThirdHolder extends RecyclerView.ViewHolder {
        private ImageView ivOne, ivTwo;

        public MyThirdHolder(View itemView) {
            super(itemView);
            ivOne = (ImageView) itemView.findViewById(R.id.iv_bearing_image_two_first);
            ivTwo = (ImageView) itemView.findViewById(R.id.iv_bearing_image_two_second);
        }
    }
}
