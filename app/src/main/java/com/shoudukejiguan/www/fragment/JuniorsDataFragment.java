package com.shoudukejiguan.www.fragment;

import android.content.Context;

import com.shoudukejiguan.www.adapter.JuniorsAdapter;
import com.shoudukejiguan.www.adapter.StyleShowAdapter;
import com.shoudukejiguan.www.entity.Juniors;
import com.shoudukejiguan.www.entity.StyleShow;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class JuniorsDataFragment extends ListDataBaseFragment<Juniors, Juniors.Data, JuniorsAdapter> {
    @Override
    protected JuniorsAdapter getAdapter(Context context, List<Juniors.Data> totalList) {
        return new JuniorsAdapter(totalList);
    }

    @Override
    protected Class<Juniors> getTClass() {
        return Juniors.class;
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
