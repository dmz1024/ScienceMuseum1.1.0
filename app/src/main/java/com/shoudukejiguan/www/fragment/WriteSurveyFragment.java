package com.shoudukejiguan.www.fragment;

import android.content.Context;

import com.shoudukejiguan.www.entity.Survey;
import com.shoudukejiguan.www.adapter.SurveyQAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class WriteSurveyFragment extends ListDataBaseFragment<Survey, Survey.Data, SurveyQAdapter> {
    private Map<Integer, Map<Integer, String>> mapAll = new HashMap<>();
    private Map<Integer, Integer> countMap = new HashMap<>();

    @Override
    protected SurveyQAdapter getAdapter(Context context, List<Survey.Data> totalList) {
        return new SurveyQAdapter(totalList, mapAll, countMap);
    }

    @Override
    protected Class<Survey> getTClass() {
        return Survey.class;
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
    public void onRefresh() {
        mapAll.clear();
        countMap.clear();
        super.onRefresh();
    }
}
