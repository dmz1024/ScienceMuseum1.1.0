package com.shoudukejiguan.www.fragment;

import android.content.Context;
import android.view.View;

import com.shoudukejiguan.www.adapter.JuniorsAdapter;
import com.shoudukejiguan.www.entity.Juniors;

import java.util.List;
import java.util.Map;

/**
 *
 */
public abstract class JuniorsDataFragment extends ListDataBaseFragment<Juniors, Juniors.Data, JuniorsAdapter> {
    private View.OnTouchListener listener;

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


    @Override
    protected boolean getRefresh() {
        return false;
    }

    @Override
    protected View.OnTouchListener getOnTouchListener() {
        return setListener();
    }


    public abstract View.OnTouchListener setListener();
}
