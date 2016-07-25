package com.shoudukejiguan.www.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.News;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class NewsAdapter extends ListBaseAdapter<News.Data, NewsAdapter.NewsHolder> {

    public NewsAdapter(List<News.Data> list) {
        super(list);
    }

    @Override
    protected void bindHolder(NewsHolder holder, int position) {
        News.Data news = list.get(position);
        String[] times = news.addtime.split("-");
        holder.tv_day.setText(times[2]);
        holder.tv_year.setText(new StringBuilder().append(times[0]).append(".").append(times[1]));
        holder.tv_title.setText(news.title);
        holder.tv_content.setText(news.introduce);
    }

    @Override
    protected int getViewRid() {
        return R.layout.item_news;
    }

    @Override
    public NewsHolder getViewHolder(View view) {
        return new NewsHolder(view);
    }

    public class NewsHolder extends RecyclerView.ViewHolder {
        public TextView tv_day;
        public TextView tv_year;
        public TextView tv_title;
        public TextView tv_content;

        public NewsHolder(View itemView) {
            super(itemView);
            tv_day = (TextView) itemView.findViewById(R.id.tv_day);
            tv_year = (TextView) itemView.findViewById(R.id.tv_year);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
