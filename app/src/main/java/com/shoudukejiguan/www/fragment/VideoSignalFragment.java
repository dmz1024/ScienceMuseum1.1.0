package com.shoudukejiguan.www.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.TabEntity;

import java.util.ArrayList;

/**
 *
 */
public class VideoSignalFragment extends BaseFragment {
    private CommonTabLayout tl_tab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_video_signal, null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        initTab();

    }

    private void initView(View view) {
        tl_tab = (CommonTabLayout) view.findViewById(R.id.tl_tab);
    }

    private void initTab() {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        mTabEntities.add(new TabEntity("全部", 0, 0));
        mTabEntities.add(new TabEntity("4D", 0, 0));
        mTabEntities.add(new TabEntity("巨幕", 0, 0));
        mTabEntities.add(new TabEntity("球幕", 0, 0));
        ArrayList<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new VideoSignaDataFragment());
        mFragments.add(new VideoSignaDataFragment());
        mFragments.add(new VideoSignaDataFragment());
        mFragments.add(new VideoSignaDataFragment());
        tl_tab.setTabData(mTabEntities, getActivity(), R.id.fg_video_signa, mFragments);
    }
}
