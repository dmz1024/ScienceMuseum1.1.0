package com.shoudukejiguan.www.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.flyco.tablayout.SegmentTabLayout;
import com.shoudukejiguan.www.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainVisitFragment extends MainBaseFragment {
    private SegmentTabLayout tl_tab;
    private String[] mTitles = {"地图导航", "馆内导览"};

    @Override
    protected boolean isInit() {
        return false;
    }

    @Override
    protected void initData() {
        ArrayList<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new MapFragment());
        mFragments.add(new GuestsFragment());
        tl_tab.setTabData(mTitles, getActivity(), R.id.fg_visit, mFragments);
    }

    @Override
    protected void initView(View view) {
        tl_tab = (SegmentTabLayout) view.findViewById(R.id.tl_tab);

    }

    @Override
    protected int getRid() {
        return R.layout.fragment_main_visit;
    }

    @Override
    protected boolean isTitleBarShow() {
        return false;
    }
}
