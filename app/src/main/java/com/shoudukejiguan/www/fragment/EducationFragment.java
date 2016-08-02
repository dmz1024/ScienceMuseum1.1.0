package com.shoudukejiguan.www.fragment;

import android.content.Context;

import com.shoudukejiguan.www.adapter.EducationAdapter;
import com.shoudukejiguan.www.constant.ApiConstant;
import com.shoudukejiguan.www.entity.Education;

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
        return ApiConstant.LIST;
    }

    @Override
    protected Map<String, String> getMap(Map<String, String> map) {
        map.put("mid","27");
        map.put("catid","108");
        return map;
    }
}
