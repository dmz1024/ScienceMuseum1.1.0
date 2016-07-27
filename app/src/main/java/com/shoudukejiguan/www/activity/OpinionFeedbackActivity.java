package com.shoudukejiguan.www.activity;

import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.fragment.EducationOpinionFragment;

public class OpinionFeedbackActivity extends FragmentBaseActivity {


    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("意见反馈");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportFragmentManager().beginTransaction().add(R.id.fg_base, new EducationOpinionFragment()).commit();
    }
}
