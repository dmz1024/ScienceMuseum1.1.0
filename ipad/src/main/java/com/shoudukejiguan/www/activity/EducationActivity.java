package com.shoudukejiguan.www.activity;

import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.TabEntity;
import com.shoudukejiguan.www.fragment.EducationFragment;
import com.shoudukejiguan.www.fragment.EducationOpinionFragment;
import com.shoudukejiguan.www.fragment.MapFragment;
import com.shoudukejiguan.www.fragment.StyleShowFragment;

import java.util.ArrayList;

public class EducationActivity extends BaseActivity {
    private CommonTabLayout tl_tab;

    @Override
    protected int getRid() {
        return R.layout.activity_education;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("教育专区");
    }

    @Override
    protected void initData() {
        initTab();
    }

    @Override
    protected void initView() {
        tl_tab = getView(R.id.tl_tab);
    }


    private void initTab() {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        mTabEntities.add(new TabEntity("基本情况", 0, 0));
        mTabEntities.add(new TabEntity("报名预约", 0, 0));
        mTabEntities.add(new TabEntity("风采展示", 0, 0));
        mTabEntities.add(new TabEntity("意见建议", 0, 0));
        ArrayList<Fragment> mFragments = new ArrayList<>();
        mFragments.add(MapFragment.getInstance("https://www.baidu.com"));
        mFragments.add(new EducationFragment());
        mFragments.add(new StyleShowFragment());
        mFragments.add(new EducationOpinionFragment());
        tl_tab.setTabData(mTabEntities, this, R.id.fg_education, mFragments);
    }
}
