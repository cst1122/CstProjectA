package com.example.dllo.project_a_cst.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.project_a_cst.R;
import com.example.dllo.project_a_cst.bean.ListBean;

import java.util.ArrayList;

/** 榜单的适配器
 * Created by dllo on 16/11/28.
 */

public class ListAdapter extends BaseAdapter{
    private ArrayList<ListBean>data;
    private Context context;

    public ListAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<ListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.get(0).getContent().size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(0).getContent().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_fragment,parent,false);
            holder = new MyHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MyHolder) convertView.getTag();
        }
        Glide.with(context).load(data.get(0).getContent().get(position).getPic_s210()).into(holder.iv);
        holder.tvOne.setText(data.get(0).getContent().get(position).getName());
        holder.tvTwo.setText(data.get(0).getContent().get(position).getContent().get(0).getTitle()
                +"-"+data.get(0).getContent().get(position).getContent().get(0).getAuthor());
        holder.tvThree.setText(data.get(0).getContent().get(position).getContent().get(1).getTitle()
                +"-"+data.get(0).getContent().get(position).getContent().get(1).getAuthor());
        holder.tvFour.setText(data.get(0).getContent().get(position).getContent().get(2).getTitle()
                +"-"+data.get(0).getContent().get(position).getContent().get(2).getAuthor());
        return convertView;
    }
    class MyHolder {
        private ImageView iv;
        private TextView tvOne,tvTwo,tvThree,tvFour;
        public MyHolder (View view){
            super();
            iv = (ImageView) view.findViewById(R.id.iv_list_fragment_item);
            tvOne = (TextView) view.findViewById(R.id.tv_one_list_fragment_item);
            tvTwo = (TextView) view.findViewById(R.id.tv_two_list_fragment_item);
            tvThree = (TextView) view.findViewById(R.id.tv_three_list_fragment_item);
            tvFour = (TextView) view.findViewById(R.id.tv_four_list_fragment_item);
        }
    }
}
