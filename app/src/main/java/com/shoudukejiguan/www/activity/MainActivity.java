package com.shoudukejiguan.www.activity;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.TabEntity;
import com.shoudukejiguan.www.fragment.MainBaseFragment;
import com.shoudukejiguan.www.fragment.MainIndexFragment;
import com.shoudukejiguan.www.fragment.MainOrderFragment;
import com.shoudukejiguan.www.fragment.MainPeosonalFragment;
import com.shoudukejiguan.www.fragment.MainVisitFragment;
import com.shoudukejiguan.www.view.NoScrollViewPager;
import com.shoudukejiguan.www.view.TextImage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private String[] mTitles = {"首页", "票务预订", "参观指引", "个人中心"};
    private int[] mIconUnselectIds = {
            R.mipmap.icon_main_index, R.mipmap.icon_main_passage_order,
            R.mipmap.icon_main_visit_guide, R.mipmap.icon_main_personal_center};
    private int[] mIconSelectIds = {
            R.mipmap.icon_main_index_selected, R.mipmap.icon_main_passage_order_selected,
            R.mipmap.icon_main_visit_guide_selected, R.mipmap.icon_main_personal_center_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private NoScrollViewPager vp_main;
    private List<MainBaseFragment> fragments;
    private CommonTabLayout tab_bottom;

    @Override
    protected int getRid() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setlLeftVisi(false);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new MainIndexFragment());
        fragments.add(new MainOrderFragment());
        fragments.add(new MainVisitFragment());
        fragments.add(new MainPeosonalFragment());
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tab_bottom.setTabData(mTabEntities);
        tab_bottom.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp_main.setCurrentItem(position, false);
                fragments.get(position).init();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        vp_main.setOffscreenPageLimit(3);
        vp_main.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }

    @Override
    protected void initView() {
        vp_main = getView(R.id.vp_main);
        tab_bottom = getView(R.id.tab_bottom);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishAll();
    }
}
