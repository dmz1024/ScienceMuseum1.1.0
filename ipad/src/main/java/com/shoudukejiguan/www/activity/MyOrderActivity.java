package com.shoudukejiguan.www.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.fragment.MyOrderFragment;

import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends BaseActivity {
    private String[] titles = {"全部", "待付款", "待参观", "已参观", "已取消"};
    private SlidingTabLayout tl_tab;
    private ViewPager vp_content;
    @Override
    protected int getRid() {
        return R.layout.activity_my_order;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("我的订单");
    }

    @Override
    protected void initData() {
        final List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            fragments.add(new MyOrderFragment());
        }

        vp_content.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 5;
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
