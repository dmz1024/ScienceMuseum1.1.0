package com.shoudukejiguan.www.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.flyco.tablayout.SegmentTabLayout;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.manager.MapManager;
import com.shoudukejiguan.www.view.MyToast;

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
        MapManager manager = new MapManager(getContext());
        manager.setOnLocationListener(new MapManager.OnLocationListener() {
            @Override
            public void location(double latitude, double longitude) {
                ArrayList<Fragment> mFragments = new ArrayList<>();
                mFragments.add(MapFragment.getInstance("http://m.amap.com/navi/?start=" + longitude + "," + latitude + "&dest=116.393275,39.970341&destName=首都科学技术馆&key=d3f5d8b3b05231fa6a11375492310e3a"));
                mFragments.add(new GuestsFragment());
                tl_tab.setTabData(mTitles, getActivity(), R.id.fg_visit, mFragments);
            }

            @Override
            public void locationErr(String errInfo) {
                MyToast.showToast("位置信息获取失败：" + errInfo);
                ArrayList<Fragment> mFragments = new ArrayList<>();
                mFragments.add(MapFragment.getInstance("http://m.amap.com/navi/?start=116.393275,39.970341&dest=116.393275,39.970341&destName=首都科学技术馆&key=d3f5d8b3b05231fa6a11375492310e3a"));
                mFragments.add(new GuestsFragment());
                tl_tab.setTabData(mTitles, getActivity(), R.id.fg_visit, mFragments);
            }
        });
    }

    @Override
    protected void initView(View view) {
        tl_tab = (SegmentTabLayout) view.findViewById(R.id.tl_tab);

    }

    @Override
    protected int getRid() {
        return R.layout.fragment_main_visit;
    }


}
