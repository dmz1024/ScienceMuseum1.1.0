package com.shoudukejiguan.www.activity;

import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.fragment.NewsFragment;

public class MoreNewsActivity extends FragmentBaseActivity {

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("新闻资讯");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportFragmentManager().beginTransaction().add(R.id.fg_base, new NewsFragment()).commit();
    }
}
