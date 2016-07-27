package com.shoudukejiguan.www.fragment;

import android.content.Context;

import com.shoudukejiguan.www.entity.ChildrenPark;
import com.shoudukejiguan.www.adapter.ChildrenParkAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class ChildrenParkFragment extends ListDataBaseFragment<ChildrenPark, ChildrenPark.Data, ChildrenParkAdapter> {
    @Override
    protected ChildrenParkAdapter getAdapter(Context context, List<ChildrenPark.Data> totalList) {
        return new ChildrenParkAdapter(totalList);
    }

    @Override
    protected Class<ChildrenPark> getTClass() {
        return ChildrenPark.class;
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
