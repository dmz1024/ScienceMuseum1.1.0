package com.shoudukejiguan.www.activity;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.shoudukejiguan.www.view.PopMenu;
import com.shoudukejiguan.www.view.TextImage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private RelativeLayout rl_top;
    private TextImage tv_index;
    private TextImage tv_order;
    private TextImage tv_visit;
    private TextImage tv_personal;
    private ImageView iv_right;
    private TextView tv_title;
    private NoScrollViewPager vp_main;
    private List<MainBaseFragment> fragments;
    private String[] titles = {"首都科学技术馆", "票务预订", "参观指引", "个人中心", "设置"};

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
        vp_main.setOffscreenPageLimit(fragments.size());
        vp_main.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

        });
    }


    @Override
    protected void initView() {
        iv_right = getView(R.id.iv_right);
        rl_top = getView(R.id.rl_top);
        tv_title = getView(R.id.tv_title);
        vp_main = getView(R.id.vp_main);
        tv_index = getView(R.id.tv_index);
        tv_order = getView(R.id.tv_order);
        tv_visit = getView(R.id.tv_visit);
        tv_personal = getView(R.id.tv_personal);
        tv_index.setOnClickListener(this);
        tv_order.setOnClickListener(this);
        tv_visit.setOnClickListener(this);
        tv_personal.setOnClickListener(this);
        iv_right.setOnClickListener(this);
    }


    /**
     * 设置右侧按钮是否可点击
     *
     * @param visi
     */
    public void setIvRightVisi(int visi) {
        iv_right.setVisibility(visi);
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
            case R.id.iv_right:
                more();
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

        tv_title.setText(titles[index]);

        if (index == 2) {
            rl_top.setVisibility(View.GONE);
        } else {
            rl_top.setVisibility(View.VISIBLE);
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

        vp_main.setCurrentItem(index, false);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishAll();
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


    /**
     * 更多
     */
    private void more() {
        backgroundAlpha(0.5f);
        new PopMenu() {
            @Override
            public PopupWindow.OnDismissListener getDis() {
                return MainActivity.this;
            }
        }.initPop(this, rl_top);
    }

}
