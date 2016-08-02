package com.shoudukejiguan.www.fragment;

import android.content.Context;

import com.shoudukejiguan.www.adapter.PublicSurveyAdapter;
import com.shoudukejiguan.www.constant.ApiConstant;
import com.shoudukejiguan.www.entity.PublicSurvey;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class PublicSurveyFragment extends ListDataBaseFragment<PublicSurvey, PublicSurvey.Data, PublicSurveyAdapter> {
    @Override
    protected PublicSurveyAdapter getAdapter(Context context, List<PublicSurvey.Data> totalList) {
        return new PublicSurveyAdapter(getContext(),totalList);
    }

    @Override
    protected Class<PublicSurvey> getTClass() {
        return PublicSurvey.class;
    }

    @Override
    protected String getUrl() {
        return ApiConstant.FORMLIST;
    }

    @Override
    protected Map<String, String> getMap(Map<String, String> map) {
        return map;
    }
}
