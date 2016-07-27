package com.shoudukejiguan.www.activity;

import com.shoudukejiguan.www.fragment.NoticeBulletinFragment;
import com.shoudukejiguan.www.R;

/**
 * Created by dengmingzhi on 16/7/25.
 */
public class NoticeBulletinActivity extends FragmentBaseActivity {
    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("通知公告");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportFragmentManager().beginTransaction().add(R.id.fg_base, new NoticeBulletinFragment()).commit();
    }
}
