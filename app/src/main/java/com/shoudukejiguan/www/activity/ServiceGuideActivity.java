package com.shoudukejiguan.www.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.api.ApiRequest;
import com.shoudukejiguan.www.constant.ApiConstant;
import com.shoudukejiguan.www.entity.GuideInfo;
import com.shoudukejiguan.www.fragment.MapFragment;
import com.shoudukejiguan.www.view.MyToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceGuideActivity extends BaseActivity {

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
        new ApiRequest<GuideInfo>(this, ApiConstant.LIST, GuideInfo.class) {
            @Override
            protected void success(GuideInfo guideInfo) {
                if (guideInfo.result == 0) {
                    final List<GuideInfo.Data> data = guideInfo.data;
                    final int count = data.size();
                    final List<Fragment> fragments = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        fragments.add(MapFragment.getInstance("https://www.baidu.com"));
                    }

                    vp_content.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                        @Override
                        public Fragment getItem(int position) {
                            return fragments.get(position);
                        }

                        @Override
                        public int getCount() {
                            return count;
                        }

                        @Override
                        public CharSequence getPageTitle(int position) {
                            return data.get(position).lanmumingcheng;
                        }
                    });

                    tl_tab.setViewPager(vp_content);
                } else {
                    MyToast.showToast(guideInfo.msg);
                }
            }

            @Override
            protected Map<String, String> map(Map<String, String> map) {
                map.put("mid", "30");
                map.put("catid", "73");
                map.put("size", Integer.MAX_VALUE + "");
                return map;
            }
        }.post("获取服务信息...");


    }

    @Override
    protected void initView() {
        tl_tab = getView(R.id.tl_tab);
        vp_content = getView(R.id.vp_content);
    }
}
