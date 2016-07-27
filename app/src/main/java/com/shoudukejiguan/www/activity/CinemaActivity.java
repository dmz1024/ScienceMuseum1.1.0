package com.shoudukejiguan.www.activity;

import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.shoudukejiguan.www.entity.TabEntity;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.fragment.MapFragment;
import com.shoudukejiguan.www.fragment.VideoNoticeFragment;
import com.shoudukejiguan.www.fragment.VideoSignalFragment;

import java.util.ArrayList;

public class CinemaActivity extends BaseActivity {
    private CommonTabLayout tl_tab;

    @Override
    protected int getRid() {
        return R.layout.activity_cinema;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("特效影院");
    }

    @Override
    protected void initView() {
        tl_tab = getView(R.id.tl_tab);
    }

    @Override
    protected void initData() {
        initTab();
        initViewPager();
    }

    private void initViewPager() {

    }

    private void initTab() {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        mTabEntities.add(new TabEntity("影院介绍", 0, 0));
        mTabEntities.add(new TabEntity("影讯介绍", 0, 0));
        mTabEntities.add(new TabEntity("影片预告", 0, 0));
//        tl_tab.setTabData(mTabEntities);
//        mTabEntities = null;
        ArrayList<Fragment> mFragments = new ArrayList<>();
        mFragments.add(MapFragment.getInstance("http://keji.lovect.cn/file/upload/201607/01/154820861.jpg"));
        mFragments.add(new VideoSignalFragment());
        mFragments.add(new VideoNoticeFragment());
        tl_tab.setTabData(mTabEntities, this, R.id.fg_cinema, mFragments);
    }

}
