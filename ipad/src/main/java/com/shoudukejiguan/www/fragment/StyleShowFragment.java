package com.shoudukejiguan.www.fragment;

import android.content.Context;

import com.shoudukejiguan.www.adapter.StyleShowAdapter;
import com.shoudukejiguan.www.adapter.VideoNoticeAdapter;
import com.shoudukejiguan.www.entity.StyleShow;
import com.shoudukejiguan.www.entity.VideoNotice;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class StyleShowFragment extends ListDataBaseFragment<StyleShow, StyleShow.Data, StyleShowAdapter> {
    @Override
    protected StyleShowAdapter getAdapter(Context context, List<StyleShow.Data> totalList) {
        return new StyleShowAdapter(totalList);
    }

    @Override
    protected Class<StyleShow> getTClass() {
        return StyleShow.class;
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
