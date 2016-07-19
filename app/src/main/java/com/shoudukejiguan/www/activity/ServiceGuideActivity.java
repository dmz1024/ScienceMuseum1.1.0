package com.shoudukejiguan.www.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.TabEntity;
import com.shoudukejiguan.www.fragment.MapFragment;
import com.shoudukejiguan.www.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class ServiceGuideActivity extends BaseActivity {
    private String[] titles = {"参观指引", "个人预订", "团体预订", "交通指南", "友情推荐", "游客服务"};
    private SlidingTabLayout tl_tab;
    private ViewPager vp_content;

    @Override
    protected int getRid() {
        return R.layout.activity_service_guide;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("服务指南");
    }

    @Override
    protected void initData() {
        final List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            fragments.add(new MapFragment());
        }

        vp_content.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 6;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });

        tl_tab.setViewPager(vp_content);

    }

    @Override
    protected void initView() {
        tl_tab = getView(R.id.tl_tab);
        vp_content = getView(R.id.vp_content);
    }
}
