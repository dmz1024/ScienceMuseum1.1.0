package com.shoudukejiguan.www.activity;

import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.TabEntity;
import com.shoudukejiguan.www.fragment.ChildrenParkFragment;

import java.util.ArrayList;

public class ChildrenParkActivity extends BaseActivity {

    private CommonTabLayout tl_tab;

    @Override
    protected int getRid() {
        return R.layout.activity_children_park;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("儿童乐园");
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
        mTabEntities.add(new TabEntity("奇趣大自然", 0, 0));
        mTabEntities.add(new TabEntity("健康小主人", 0, 0));
        mTabEntities.add(new TabEntity("生活的奥妙", 0, 0));
        mTabEntities.add(new TabEntity("放飞希望", 0, 0));
        ArrayList<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new ChildrenParkFragment());
        mFragments.add(new ChildrenParkFragment());
        mFragments.add(new ChildrenParkFragment());
        mFragments.add(new ChildrenParkFragment());
        tl_tab.setTabData(mTabEntities, this, R.id.fg_chiledrenPark, mFragments);
    }
}
