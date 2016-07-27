package com.shoudukejiguan.www.activity;


import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.shoudukejiguan.www.entity.TabEntity;
import com.shoudukejiguan.www.fragment.TeamRegFragment;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.fragment.RegFragment;

import java.util.ArrayList;

public class RegActivity extends BaseActivity {

    private CommonTabLayout tl_tab;

    @Override
    protected int getRid() {
        return R.layout.activity_reg;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("注册");
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
        mTabEntities.add(new TabEntity("个人注册", 0, 0));
        mTabEntities.add(new TabEntity("团体注册", 0, 0));
        ArrayList<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new RegFragment());
        mFragments.add(new TeamRegFragment());
        tl_tab.setTabData(mTabEntities, this, R.id.fg_reg, mFragments);
    }
}
