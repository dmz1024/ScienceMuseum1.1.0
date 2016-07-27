package com.shoudukejiguan.www.activity;

import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.shoudukejiguan.www.entity.TabEntity;
import com.shoudukejiguan.www.fragment.JuniorsFragment;
import com.shoudukejiguan.www.fragment.ScienceFragment;
import com.shoudukejiguan.www.R;

import java.util.ArrayList;

public class ExhibitionActivity extends BaseActivity {

    private CommonTabLayout tl_tab;

    @Override
    protected int getRid() {
        return R.layout.activity_exhibition;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("常设展览");
    }

    @Override
    protected void initView() {
        tl_tab = getView(R.id.tl_tab);
    }

    @Override
    protected void initData() {
        initTab();
    }

    private void initTab() {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        mTabEntities.add(new TabEntity("三生主展", 0, 0));
        mTabEntities.add(new TabEntity("科学广场", 0, 0));
//        tl_tab.setTabData(mTabEntities);
//        mTabEntities = null;
        ArrayList<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new JuniorsFragment());
        mFragments.add(new ScienceFragment());
        tl_tab.setTabData(mTabEntities, this, R.id.fg_exhibtion, mFragments);
    }
}
