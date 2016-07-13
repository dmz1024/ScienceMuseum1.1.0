package com.shoudukejiguan.www.fragment;

import android.content.Context;

import com.shoudukejiguan.www.adapter.NewsAdapter;
import com.shoudukejiguan.www.entity.News;

import java.util.List;
import java.util.Map;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class NewsFragment extends ListDataBaseFragment<News,News.Data,NewsAdapter> {
    @Override
    protected NewsAdapter getAdapter(Context context, List<News.Data> totalList) {
        return new NewsAdapter(totalList);
    }

    @Override
    protected Class<News> getTClass() {
        return News.class;
    }

    @Override
    protected String getUrl() {
        return "";
    }

    @Override
    protected Map<String, String> getMap(Map<String, String> map) {
        return map;
    }
}
