package com.shoudukejiguan.www.activity;

import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.shoudukejiguan.www.entity.TabEntity;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.fragment.CollectionGalleryFragment;
import com.shoudukejiguan.www.fragment.VideoNoticeFragment;

import java.util.ArrayList;

public class MyCollectionActivity extends BaseActivity {

    private CommonTabLayout tl_tab;

    @Override
    protected int getRid() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("我的收藏");
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
        mTabEntities.add(new TabEntity("展厅", 0, 0));
        mTabEntities.add(new TabEntity("影片预告", 0, 0));
        ArrayList<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new CollectionGalleryFragment());
        mFragments.add(new VideoNoticeFragment());
        tl_tab.setTabData(mTabEntities, this, R.id.fg_collection, mFragments);
    }
}
