package com.shoudukejiguan.www.fragment;

import android.content.Context;

import com.shoudukejiguan.www.adapter.PublicSurveyAdapter;
import com.shoudukejiguan.www.adapter.SurveyQAdapter;
import com.shoudukejiguan.www.entity.PublicSurvey;
import com.shoudukejiguan.www.entity.Survey;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class WriteSurveyFragment extends ListDataBaseFragment<Survey, Survey.Data, SurveyQAdapter> {
    @Override
    protected SurveyQAdapter getAdapter(Context context, List<Survey.Data> totalList) {
        return new SurveyQAdapter(totalList);
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
}
