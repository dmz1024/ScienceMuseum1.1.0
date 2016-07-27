package com.shoudukejiguan.www.fragment;

import android.content.Context;

import com.shoudukejiguan.www.adapter.VideoSignaAdapter;
import com.shoudukejiguan.www.entity.VideoSigna;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class VideoSignaDataFragment extends ListDataBaseFragment<VideoSigna,VideoSigna.Data,VideoSignaAdapter> {
    @Override
    protected VideoSignaAdapter getAdapter(Context context, List<VideoSigna.Data> totalList) {
        return new VideoSignaAdapter(totalList);
    }

    @Override
    protected Class<VideoSigna> getTClass() {
        return VideoSigna.class;
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
