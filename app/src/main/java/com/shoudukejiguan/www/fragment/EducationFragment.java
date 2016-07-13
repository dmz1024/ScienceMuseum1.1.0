package com.shoudukejiguan.www.fragment;

import android.content.Context;

import com.shoudukejiguan.www.adapter.EducationAdapter;
import com.shoudukejiguan.www.adapter.VideoNoticeAdapter;
import com.shoudukejiguan.www.entity.Education;
import com.shoudukejiguan.www.entity.VideoNotice;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class EducationFragment extends ListDataBaseFragment<Education, Education.Data, EducationAdapter> {
    @Override
    protected EducationAdapter getAdapter(Context context, List<Education.Data> totalList) {
        return new EducationAdapter(totalList);
    }

    @Override
    protected Class<Education> getTClass() {
        return Education.class;
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
