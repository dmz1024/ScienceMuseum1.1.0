package com.shoudukejiguan.www.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.util.Util;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.IndexNews;
import com.shoudukejiguan.www.view.TextImage;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class IndexNewsAdapter extends DefaultAdapter<IndexNews> {
    Context ctx;

    public IndexNewsAdapter(List<IndexNews> datas) {
        super(datas);
    }

    public IndexNewsAdapter(Context ctx, List<IndexNews> datas) {
        this(datas);
        this.ctx = ctx;
    }

    @Override
    protected BaseHolder<IndexNews> getHolder() {
        return new NewsHolder();
    }

     class NewsHolder extends BaseHolder<IndexNews> {
        private TextImage tv_title;
        private TextView tv_time;


        @Override
        protected void refreshView(IndexNews data) {
            tv_title.setText(data.title);
            tv_time.setText(data.time);
        }

        @Override
        protected View initView() {
            View view = View.inflate(ctx, R.layout.item_index_news, null);
            tv_title = (TextImage) view.findViewById(R.id.tv_title);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            return view;
        }
    }
}
