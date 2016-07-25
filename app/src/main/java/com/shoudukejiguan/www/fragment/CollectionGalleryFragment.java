package com.shoudukejiguan.www.fragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shoudukejiguan.www.adapter.GalleryAdapter;
import com.shoudukejiguan.www.adapter.NewsAdapter;
import com.shoudukejiguan.www.entity.Gallery;
import com.shoudukejiguan.www.entity.News;

import java.util.List;
import java.util.Map;

/**
 * Created by dengmingzhi on 16/7/12.
 * 我的收藏-展厅
 */
public class CollectionGalleryFragment extends ListDataBaseFragment<Gallery, Gallery.Data, GalleryAdapter> {
    @Override
    protected GalleryAdapter getAdapter(Context context, List<Gallery.Data> totalList) {
        return new GalleryAdapter(totalList);
    }

    @Override
    protected Class<Gallery> getTClass() {
        return Gallery.class;
    }

    @Override
    protected String getUrl() {
        return "";
    }

    @Override
    protected Map<String, String> getMap(Map<String, String> map) {
        return map;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        return manager;
    }
}
