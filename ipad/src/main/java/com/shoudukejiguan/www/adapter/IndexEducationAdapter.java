package com.shoudukejiguan.www.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.util.Util;

import java.util.List;

public class IndexEducationAdapter extends RecyclerView.Adapter<IndexEducationAdapter.ViewHolder> {
    private List<String> urlList;
    public IndexEducationAdapter(List<String> urlList) {
        this.urlList = urlList;
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(Util.getApplication()).load(urlList.get(position)).into(holder.iv_img);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_img;
        public TextView tv_title;
        public TextView tv_count;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_count = (TextView) itemView.findViewById(R.id.tv_count);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(Util.getApplication(), R.layout.item_index_education, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
}

