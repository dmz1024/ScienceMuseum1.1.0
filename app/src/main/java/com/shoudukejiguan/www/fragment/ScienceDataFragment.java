package com.shoudukejiguan.www.fragment;

import android.content.Context;

import com.shoudukejiguan.www.adapter.JuniorsAdapter;
import com.shoudukejiguan.www.adapter.ScienceAdapter;
import com.shoudukejiguan.www.entity.Juniors;
import com.shoudukejiguan.www.entity.Science;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class ScienceDataFragment extends ListDataBaseFragment<Science, Science.Data, ScienceAdapter> {
    @Override
    protected ScienceAdapter getAdapter(Context context, List<Science.Data> totalList) {
        return new ScienceAdapter(totalList);
    }

    @Override
    protected Class<Science> getTClass() {
        return Science.class;
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
