package com.shoudukejiguan.www.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.News;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class NewsAdapter extends ListBaseAdapter<News.Data,NewsAdapter.NewsHolder> {

    public NewsAdapter(List<News.Data> list) {
        super(list);
    }

    @Override
    protected void bindHolder(NewsHolder holder, int position) {

    }

    @Override
    protected int getViewRid() {
        return R.layout.item_news;
    }

    @Override
    public NewsHolder getViewHolder(View view) {
        return new NewsHolder(view);
    }

    public class NewsHolder extends RecyclerView.ViewHolder{

        public NewsHolder(View itemView) {
            super(itemView);
        }
    }
}
