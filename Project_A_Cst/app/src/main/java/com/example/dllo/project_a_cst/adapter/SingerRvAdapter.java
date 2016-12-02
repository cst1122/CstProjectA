package com.example.dllo.project_a_cst.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.bean.SingerBean;
import com.example.dllo.project_a_cst.else_class.MyNetListener;
import com.example.dllo.project_a_cst.else_class.NetHelper;

import java.util.ArrayList;

import static com.example.dllo.project_a_cst.my_class.MyConstants.SINGER_URL;

/** 歌手抽屉的适配器
 * Created by dllo on 16/11/30.
 */

public class SingerRvAdapter extends RecyclerView.Adapter{
    private String[] data = {"华语男歌手","华语女歌手","华语组合","欧美男歌手","欧美女歌手","欧美组合","韩国男歌手","韩国女歌手","韩国组合","日本男歌手","日本女歌手","日本组合","其他歌手"};
    private Context context;
    private ArrayList<SingerBean> singerData;
    private ArrayList<View> viewData;
    private ImageView ivOne,ivTwo,ivThree;
    private TextView tvOne,tvTwo,tvThree;

    public SingerRvAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return 1;
        }else
        return 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case 1:
                View viewOne = LayoutInflater.from(context).inflate(R.layout.singer_item_type_one,parent,false);
                holder = new MyFirstHolder(viewOne);
                break;
            case 2:
                View viewTwo = LayoutInflater.from(context).inflate(R.layout.singer_item_type_two,parent,false);
                holder = new MySecondHolder(viewTwo);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {


        if (position == 0){
            String url = SINGER_URL;

            NetHelper.myRequest(url, new MyNetListener<SingerBean>() {
                @Override
                public void successListener(SingerBean response) {
                    singerData = new ArrayList<>();
                    viewData = new ArrayList<>();
                    singerData.add(response);
                    View viewOne = getSingerVpView(0,1,2);
                    View viewTwo = getSingerVpView(3,4,5);
                    View viewThree = getSingerVpView(6,7,8);
                    View viewFour = getSingerVpView(9,10,11);
                    viewData.add(viewOne);
                    viewData.add(viewTwo);
                    viewData.add(viewThree);
                    viewData.add(viewFour);
                    SingerVpTypeOneAdapter adapter = new SingerVpTypeOneAdapter();
                    adapter.setData(viewData);
                    ((MyFirstHolder)holder).mViewPager.setAdapter(adapter);
                    ((MyFirstHolder)holder).mViewPager.getParent().requestDisallowInterceptTouchEvent(false);
                    ((MyFirstHolder)holder).mTabLayout.setupWithViewPager(((MyFirstHolder)holder).mViewPager);
                }

                @Override
                public void errorListener(VolleyError error) {

                }
            },SingerBean.class);
        }else {
            ((MySecondHolder)holder).tv.setText(data[position-1]);
        }

    }

    @Override
    public int getItemCount() {
        return 14;
    }

    class MyFirstHolder extends RecyclerView.ViewHolder{
        private ViewPager mViewPager;
        private TabLayout mTabLayout;
        public MyFirstHolder(View itemView) {
            super(itemView);
            mViewPager = (ViewPager) itemView.findViewById(R.id.vp_singer_item_type_one);
            mTabLayout = (TabLayout) itemView.findViewById(R.id.tab_singer_item_type_one);
        }
    }
    class MySecondHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public MySecondHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_singer_item_type_two);
        }
    }

        public View getSingerVpView(int first,int second,int third) {
            View view = LayoutInflater.from(context).inflate(R.layout.singer_vp_type_one_view, null);
            ivOne = (ImageView) view.findViewById(R.id.iv_singer_vp_one);
            ivTwo = (ImageView) view.findViewById(R.id.iv_singer_vp_two);
            ivThree = (ImageView) view.findViewById(R.id.iv_singer_vp_three);
            tvOne = (TextView) view.findViewById(R.id.tv_singer_vp_one);
            tvTwo = (TextView) view.findViewById(R.id.tv_singer_vp_two);
            tvThree = (TextView) view.findViewById(R.id.tv_singer_vp_three);
            GlideImage(first,ivOne);
            GlideImage(second,ivTwo);
            GlideImage(third,ivThree);
            setText(first,tvOne);
            setText(second,tvTwo);
            setText(third,tvThree);
            return view;
        }



    public void GlideImage (int position,ImageView imageView){
        Glide.with(context).load(singerData.get(0).getArtist().get(position).getAvatar_middle()).into(imageView);
    }
    public void setText (int position,TextView textView){
        textView.setText(singerData.get(0).getArtist().get(position).getName());
    }

}
