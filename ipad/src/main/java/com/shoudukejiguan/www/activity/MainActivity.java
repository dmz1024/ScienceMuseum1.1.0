package com.shoudukejiguan.www.activity;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.TabEntity;
import com.shoudukejiguan.www.fragment.MainBaseFragment;
import com.shoudukejiguan.www.fragment.MainIndexFragment;
import com.shoudukejiguan.www.fragment.MainOrderFragment;
import com.shoudukejiguan.www.fragment.MainPeosonalFragment;
import com.shoudukejiguan.www.fragment.MainVisitFragment;
import com.shoudukejiguan.www.view.MyToast;
import com.shoudukejiguan.www.view.NoScrollViewPager;
import com.shoudukejiguan.www.view.TextImage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private TextImage tv_index;
    private TextImage tv_order;
    private TextImage tv_visit;
    private TextImage tv_personal;

    private NoScrollViewPager vp_main;
    private List<MainBaseFragment> fragments;

    @Override
    protected int getRid() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new MainIndexFragment());
        fragments.add(new MainOrderFragment());
        fragments.add(new MainVisitFragment());
        fragments.add(new MainPeosonalFragment());
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
        tv_index = getView(R.id.tv_index);
        tv_order = getView(R.id.tv_order);
        tv_visit = getView(R.id.tv_visit);
        tv_personal = getView(R.id.tv_personal);
        tv_index.setOnClickListener(this);
        tv_order.setOnClickListener(this);
        tv_visit.setOnClickListener(this);
        tv_personal.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_index:
                onTabSelect(0);
                break;
            case R.id.tv_order:
                onTabSelect(1);
                break;
            case R.id.tv_visit:
                onTabSelect(2);
                break;
            case R.id.tv_personal:
                onTabSelect(3);
                break;
        }
    }

    /**
     * 点击tab切换视图
     *
     * @param index
     */
    public void onTabSelect(int index) {
        int currentItem = vp_main.getCurrentItem();
        if (index == currentItem) {
            return;
        }

        tv_index.setDrawable(R.mipmap.icon_ipad_nav_index);
        tv_order.setDrawable(R.mipmap.icon_ipad_nav_book);
        tv_visit.setDrawable(R.mipmap.icon_ipad_nav_guide);
        tv_personal.setDrawable(R.mipmap.icon_ipad_nav_personal);

        tv_index.setTextColor(getResources().getColor(R.color.colore3e2e2));
        tv_order.setTextColor(getResources().getColor(R.color.colore3e2e2));
        tv_visit.setTextColor(getResources().getColor(R.color.colore3e2e2));
        tv_personal.setTextColor(getResources().getColor(R.color.colore3e2e2));


        switch (index) {
            case 0:
                tv_index.setDrawable(R.mipmap.icon_ipad_nav_index_cur);
                tv_index.setTextColor(getResources().getColor(R.color.color184064));
                break;
            case 1:
                tv_order.setDrawable(R.mipmap.icon_ipad_nav_book_cur);
                tv_order.setTextColor(getResources().getColor(R.color.color184064));
                break;
            case 2:
                tv_visit.setDrawable(R.mipmap.icon_ipad_nav_guide_cur);
                tv_visit.setTextColor(getResources().getColor(R.color.color184064));
                break;
            case 3:
                tv_personal.setDrawable(R.mipmap.icon_ipad_nav_personal_cur);
                tv_personal.setTextColor(getResources().getColor(R.color.color184064));
                break;
        }

        fragments.get(index).init();
        vp_main.setCurrentItem(index, false);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishAll();
    }

    @Override
    public void left() {
        fragments.get(0).left();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                String url = result.getContents();
                if (url.contains(".")) {
                    if (!url.startsWith("http")) {
                        url = "http://" + url;
                    }
                    Intent intent = new Intent(this, WebViewActivity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                } else {
                    MyToast.showToast("不可识别的网址：" + url);
                }

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
