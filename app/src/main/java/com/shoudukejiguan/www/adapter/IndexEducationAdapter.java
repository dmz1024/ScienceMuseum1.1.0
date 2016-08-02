package com.shoudukejiguan.www.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.entity.Index;
import com.shoudukejiguan.www.util.Util;
import com.shoudukejiguan.www.R;

import java.util.List;

public class IndexEducationAdapter extends RecyclerView.Adapter<IndexEducationAdapter.ViewHolder> {
    private List<Index.JiaoYu> jyList;

    public IndexEducationAdapter(List<Index.JiaoYu> jyList) {
        this.jyList = jyList;
    }

    @Override
    public int getItemCount() {
        return jyList.size();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Index.JiaoYu jiaoYu = jyList.get(position);
        Glide.with(Util.getApplication()).load(jiaoYu.thumb).into(holder.iv_img);
        holder.tv_title.setText(jiaoYu.title);
        holder.tv_count.setText(jiaoYu.ybm + "人已报名");
        holder.tv_price.setText("￥" + jiaoYu.kcpj);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_img;
        public TextView tv_title;
        public TextView tv_count;
        public TextView tv_price;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_count = (TextView) itemView.findViewById(R.id.tv_count);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(Util.getApplication(), R.layout.item_index_education, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
}

