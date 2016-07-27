package com.shoudukejiguan.www.fragment;

import android.content.Context;

import com.shoudukejiguan.www.adapter.VideoNoticeAdapter;
import com.shoudukejiguan.www.entity.VideoNotice;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class VideoNoticeFragment extends ListDataBaseFragment<VideoNotice, VideoNotice.Data, VideoNoticeAdapter> {
    @Override
    protected VideoNoticeAdapter getAdapter(Context context, List<VideoNotice.Data> totalList) {
        return new VideoNoticeAdapter(totalList);
    }

    @Override
    protected Class<VideoNotice> getTClass() {
        return VideoNotice.class;
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
