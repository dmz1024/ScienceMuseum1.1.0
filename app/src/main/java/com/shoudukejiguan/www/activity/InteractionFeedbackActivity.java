package com.shoudukejiguan.www.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.TabEntity;
import com.shoudukejiguan.www.fragment.EducationOpinionFragment;
import com.shoudukejiguan.www.fragment.JuniorsFragment;
import com.shoudukejiguan.www.fragment.PublicSurveyFragment;
import com.shoudukejiguan.www.fragment.ScienceFragment;

import java.util.ArrayList;

public class InteractionFeedbackActivity extends BaseActivity {
    private CommonTabLayout tl_tab;

    @Override
    protected int getRid() {
        return R.layout.activity_interaction_feedback;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("互动反馈");
    }

    @Override
    protected void initData() {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        mTabEntities.add(new TabEntity("公众调查", 0, 0));
        mTabEntities.add(new TabEntity("在线反馈", 0, 0));
        ArrayList<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new PublicSurveyFragment());
        mFragments.add(new EducationOpinionFragment());
        tl_tab.setTabData(mTabEntities, this, R.id.fg_interaction_feedback, mFragments);
    }

    @Override
    protected void initView() {
        tl_tab=getView(R.id.tl_tab);
    }
}
