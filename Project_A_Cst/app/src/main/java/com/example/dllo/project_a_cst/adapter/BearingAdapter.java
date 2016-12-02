package com.example.dllo.project_a_cst.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.bean.BearingBean;

import java.util.ArrayList;

import static com.example.dllo.project_a_cst.my_class.MyConstants.BEARING_ADAPTER_TYPE_TWO;

/** 动态界面的适配器  接口有问题
 * Created by dllo on 16/11/28.
 */

public class BearingAdapter extends RecyclerView.Adapter {
    private ArrayList<BearingBean> data;
    private Context context;
    private int myType;


    public BearingAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<BearingBean> data) {
        this.data = data;
        notifyDataSetChanged();

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            myType = 1;
        } else if (position == 2) {
            myType = 2;
        } else if (data.get(0).getMsg().get(position - 1).getContent() != null && data.get(0).getMsg().get(position - 1).getContent().getContent_type() == 0) {
            myType = 3;
        } else if (data.get(0).getMsg().get(position - 1).getContent() != null) {
            if (data.get(0).getMsg().get(position - 1).getContent().getContent_type() == 1
                    || data.get(0).getMsg().get(position - 1).getContent().getContent_type() == 3
                    || data.get(0).getMsg().get(position - 1).getContent().getContent_type() == 2) {
                myType = 4;
            }else {
                myType = 5;
            }
        } else {
            myType = 5;
        }
        return myType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == 1) {
            View viewFirst = LayoutInflater.from(context).inflate(R.layout.item_bearing_type_one, parent, false);
            holder = new MyFirstHolder(viewFirst);
        } else if (viewType == 2) {
            View viewSecond = LayoutInflater.from(context).inflate(R.layout.item_bearing_type_two, parent, false);
            holder = new MySecondHolder(viewSecond);
        } else {
            switch (viewType) {
                case 3:
                    View viewThird = LayoutInflater.from(context).inflate(R.layout.item_bearing_type_three, parent, false);
                    holder = new MyThirdHolder(viewThird);
                    break;
                case 4:
                    View viewFourth = LayoutInflater.from(context).inflate(R.layout.item_bearing_type_four, parent, false);
                    holder = new MyFourthHolder(viewFourth);
                    break;
                case 5:
                    break;
            }
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (myType) {
            case 1:
                Glide.with(context).load(data.get(0).getTopics().get(0).getPic_350x170()).into(((MyFirstHolder) holder).ivFirst);
                Glide.with(context).load(data.get(0).getTopics().get(1).getPic_350x170()).into(((MyFirstHolder) holder).ivSecond);
                Glide.with(context).load(data.get(0).getTopics().get(2).getPic_350x170()).into(((MyFirstHolder) holder).ivThird);
                ((MyFirstHolder) holder).tvFirst.setText(data.get(0).getTopics().get(0).getTopic_title());
                ((MyFirstHolder) holder).tvSecond.setText(data.get(0).getTopics().get(1).getTopic_title());
                ((MyFirstHolder) holder).tvThird.setText(data.get(0).getTopics().get(2).getTopic_title());
                break;
            case 2:
                Glide.with(context).load(BEARING_ADAPTER_TYPE_TWO)
                        .into(((MySecondHolder) holder).iv);
                ((MySecondHolder) holder).tvOne.setText("# 你最想看谁的演唱会?");
                ((MySecondHolder) holder).tvTwo.setText("已有110人参加");
                break;
            case 3:
                Glide.with(context).load(data.get(0).getMsg().get(position - 1).getAuthor().getUserpic()).into(((MyThirdHolder) holder).ivOne);
                Glide.with(context).load(data.get(0).getMsg().get(position - 1).getContent().getPic()).into(((MyThirdHolder) holder).ivTwo);
                ((MyThirdHolder) holder).tvOne.setText(data.get(0).getMsg().get(position - 1).getAuthor().getUsername());
                ((MyThirdHolder) holder).tvTwo.setText("05:05");
                ((MyThirdHolder) holder).tvThree.setText(data.get(0).getMsg().get(position - 1).getMsg());
                if (data.get(0).getMsg().get(position - 1).getTopic() != null && data.get(0).getMsg().get(position - 1).getTopic().getTopic_title() != null) {
                    ((MyThirdHolder) holder).tvFour.setText(data.get(0).getMsg().get(position - 1).getTopic().getTopic_title());
                }
                ((MyThirdHolder) holder).tvFive.setText(data.get(0).getMsg().get(position - 1).getContent().getTitle());
                if (data.get(0).getMsg().get(position - 1).getContent().getArtist_name() != null) {
                    ((MyThirdHolder) holder).tvSix.setText(data.get(0).getMsg().get(position - 1).getContent().getArtist_name());
                }
                if (data.get(0).getMsg().get(position - 1).getZan_num() != 0) {
                    ((MyThirdHolder) holder).tvSeven.setText(data.get(0).getMsg().get(position - 1).getZan_num() + "");
                }
                if (data.get(0).getMsg().get(position - 1).getComment_num() != 0) {
                    ((MyThirdHolder) holder).tvEight.setText(data.get(0).getMsg().get(position - 1).getComment_num() + "");
                }
                if (data.get(0).getMsg().get(position - 1).getShare_num() != 0) {
                    ((MyThirdHolder) holder).tvNine.setText(data.get(0).getMsg().get(position - 1).getShare_num() + "");
                }
                BearingImageAdapter adapter = new BearingImageAdapter(context);
                adapter.setData(data);
                adapter.setPos(position - 1);
                if (data.get(0).getMsg().get(position - 1).getPiclist() != null) {
                    switch (data.get(0).getMsg().get(position - 1).getPiclist().size()) {
                        case 1:
                            adapter.setMoreType(1);
                            ((MyThirdHolder) holder).recyclerView.setAdapter(adapter);
                            ((MyThirdHolder) holder).recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                            break;
                        case 2:
                            adapter.setMoreType(2);
                            ((MyThirdHolder) holder).recyclerView.setAdapter(adapter);
                            ((MyThirdHolder) holder).recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                            break;
                        case 3:
                            adapter.setMoreType(3);
                            ((MyThirdHolder) holder).recyclerView.setAdapter(adapter);
                            ((MyThirdHolder) holder).recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                            break;
                        case 4:
                            adapter.setMoreType(4);
                            ((MyThirdHolder) holder).recyclerView.setAdapter(adapter);
                            ((MyThirdHolder) holder).recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
                            break;
                        case 5:
                            adapter.setMoreType(5);
                            ((MyThirdHolder) holder).recyclerView.setAdapter(adapter);
                            ((MyThirdHolder) holder).recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                            break;
                        case 6:
                            adapter.setMoreType(6);
                            ((MyThirdHolder) holder).recyclerView.setAdapter(adapter);
                            ((MyThirdHolder) holder).recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                            break;
                    }
                }
                ((MyThirdHolder) holder).recyclerView.setAdapter(adapter);
                ((MyThirdHolder) holder).recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                break;
            case 4:
                Glide.with(context).load(data.get(0).getMsg().get(position - 1).getAuthor().getUserpic()).into(((MyFourthHolder) holder).ivOne);
                Glide.with(context).load(data.get(0).getMsg().get(position - 1).getContent().getPic()).into(((MyFourthHolder) holder).ivTwo);
                ((MyFourthHolder) holder).tvOne.setText(data.get(0).getMsg().get(position - 1).getAuthor().getUsername());
                ((MyFourthHolder) holder).tvTwo.setText("11:26");
                ((MyFourthHolder) holder).tvThree.setText(data.get(0).getMsg().get(position - 1).getMsg());
                if (data.get(0).getMsg().get(position - 1).getTopic() != null && data.get(0).getMsg().get(position - 1).getTopic().getTopic_title() != null) {
                    ((MyFourthHolder) holder).tvFour.setText(data.get(0).getMsg().get(position - 1).getTopic().getTopic_title());
                }
                ((MyFourthHolder) holder).tvFive.setText(data.get(0).getMsg().get(position - 1).getContent().getTitle());
                if (data.get(0).getMsg().get(position - 1).getZan_num() != 0) {
                    ((MyFourthHolder) holder).tvSix.setText(data.get(0).getMsg().get(position - 1).getZan_num() + "");
                }
                if (data.get(0).getMsg().get(position - 1).getComment_num() != 0) {
                    ((MyFourthHolder) holder).tvSeven.setText(data.get(0).getMsg().get(position - 1).getComment_num() + "");
                }
                if (data.get(0).getMsg().get(position - 1).getShare_num() != 0) {
                    ((MyFourthHolder) holder).tvEight.setText(data.get(0).getMsg().get(position - 1).getShare_num() + "");
                }
                BearingImageAdapter adapterFour = new BearingImageAdapter(context);
                adapterFour.setData(data);
                adapterFour.setPos(position - 1);
                if (data.get(0).getMsg().get(position - 1).getPiclist() != null) {
                    switch (data.get(0).getMsg().get(position - 1).getPiclist().size()) {
                        case 1:
                            adapterFour.setMoreType(1);
                            ((MyFourthHolder) holder).recyclerView.setAdapter(adapterFour);
                            ((MyFourthHolder) holder).recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                            break;
                        case 2:
                            adapterFour.setMoreType(2);
                            ((MyFourthHolder) holder).recyclerView.setAdapter(adapterFour);
                            ((MyFourthHolder) holder).recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayout.HORIZONTAL));
                            break;
                        case 3:
                            adapterFour.setMoreType(3);
                            ((MyFourthHolder) holder).recyclerView.setAdapter(adapterFour);
                            ((MyFourthHolder) holder).recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                            break;
                        case 4:
                            adapterFour.setMoreType(4);
                            ((MyFourthHolder) holder).recyclerView.setAdapter(adapterFour);
                            ((MyFourthHolder) holder).recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
                            break;
                        case 5:
                            adapterFour.setMoreType(5);
                            ((MyFourthHolder) holder).recyclerView.setAdapter(adapterFour);
                            ((MyFourthHolder) holder).recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                            break;
                        case 6:
                            adapterFour.setMoreType(6);
                            ((MyFourthHolder) holder).recyclerView.setAdapter(adapterFour);
                            ((MyFourthHolder) holder).recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                            break;
                    }
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        //return data.get(0).getMsg().size() + 1;
//        return myType.size();
        return 0;
    }

    class MyFirstHolder extends RecyclerView.ViewHolder {
        private ImageView ivBig, ivFirst, ivSecond, ivThird;
        private TextView tvFirst, tvSecond, tvThird;

        public MyFirstHolder(View itemView) {
            super(itemView);
            ivBig = (ImageView) itemView.findViewById(R.id.iv_bearing_type_one_big);
            ivFirst = (ImageView) itemView.findViewById(R.id.iv_bearing_type_one_first);
            ivSecond = (ImageView) itemView.findViewById(R.id.iv_bearing_type_one_second);
            ivThird = (ImageView) itemView.findViewById(R.id.iv_bearing_type_one_third);
            tvFirst = (TextView) itemView.findViewById(R.id.tv_bearing_type_one_first);
            tvSecond = (TextView) itemView.findViewById(R.id.tv_bearing_type_one_second);
            tvThird = (TextView) itemView.findViewById(R.id.tv_bearing_type_one_third);
        }
    }

    class MySecondHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tvOne, tvTwo;

        public MySecondHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_bearing_type_two);
            tvOne = (TextView) itemView.findViewById(R.id.tv_bearing_type_two_first);
            tvTwo = (TextView) itemView.findViewById(R.id.tv_bearing_type_two_second);
        }
    }

    class MyThirdHolder extends RecyclerView.ViewHolder {
        private ImageView ivOne, ivTwo;
        private TextView tvOne, tvTwo, tvThree, tvFour, tvFive, tvSix, tvSeven, tvEight, tvNine;
        private RecyclerView recyclerView;

        public MyThirdHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view_bearing_type_three);
            ivOne = (ImageView) itemView.findViewById(R.id.iv_bearing_type_three_title);
            ivTwo = (ImageView) itemView.findViewById(R.id.iv_item_bearing_b);
            tvOne = (TextView) itemView.findViewById(R.id.tv_bearing_type_three_title);
            tvTwo = (TextView) itemView.findViewById(R.id.tv_bearing_type_three_time);
            tvThree = (TextView) itemView.findViewById(R.id.tv_bearing_type_three_content);
            tvFour = (TextView) itemView.findViewById(R.id.tv_bearing_type_three_blue);
            tvFive = (TextView) itemView.findViewById(R.id.tv_bearing_type_three_song_name);
            tvSix = (TextView) itemView.findViewById(R.id.tv_bearing_type_three_singer);
            tvSeven = (TextView) itemView.findViewById(R.id.tv_bearing_type_three_dianzan);
            tvEight = (TextView) itemView.findViewById(R.id.tv_bearing_type_three_pinglun);
            tvNine = (TextView) itemView.findViewById(R.id.tv_bearing_type_three_zhuanfa);
        }
    }

    class MyFourthHolder extends RecyclerView.ViewHolder {
        private ImageView ivOne, ivTwo;
        private TextView tvOne, tvTwo, tvThree, tvFour, tvFive, tvSix, tvSeven, tvEight;
        private RecyclerView recyclerView;

        public MyFourthHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view_bearing_type_four);
            ivOne = (ImageView) itemView.findViewById(R.id.iv_bearing_type_four_title);
            ivTwo = (ImageView) itemView.findViewById(R.id.iv_bearing_type_four_song);
            tvOne = (TextView) itemView.findViewById(R.id.tv_bearing_type_four_title);
            tvTwo = (TextView) itemView.findViewById(R.id.tv_bearing_type_four_time);
            tvThree = (TextView) itemView.findViewById(R.id.tv_bearing_type_four_content);
            tvFour = (TextView) itemView.findViewById(R.id.tv_bearing_type_four_blue);
            tvFive = (TextView) itemView.findViewById(R.id.tv_bearing_type_four_song_name);
            tvSix = (TextView) itemView.findViewById(R.id.tv_bearing_type_four_dianzan);
            tvSeven = (TextView) itemView.findViewById(R.id.tv_bearing_type_four_pinglun);
            tvEight = (TextView) itemView.findViewById(R.id.tv_bearing_type_four_zhuanfa);
        }
    }
}
