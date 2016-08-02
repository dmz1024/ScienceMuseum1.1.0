package com.shoudukejiguan.www.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.shoudukejiguan.www.constant.ApiConstant;
import com.shoudukejiguan.www.entity.ChildrenPark;
import com.shoudukejiguan.www.adapter.ChildrenParkAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class ChildrenParkFragment extends ListDataBaseFragment<ChildrenPark, ChildrenPark.Data, ChildrenParkAdapter> {
    public String catid;

    public static ChildrenParkFragment getInstance(String catid) {
        Bundle bundle = new Bundle();
        bundle.putString("catid", catid);
        ChildrenParkFragment childrenParkFragment = new ChildrenParkFragment();
        childrenParkFragment.setArguments(bundle);
        return childrenParkFragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            catid = bundle.getString("catid");
        }
    }

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
        return ApiConstant.LIST;
    }

    @Override
    protected Map<String, String> getMap(Map<String, String> map) {
        map.put("mid", "28");
        map.put("catid", catid);
        return map;
    }
}
