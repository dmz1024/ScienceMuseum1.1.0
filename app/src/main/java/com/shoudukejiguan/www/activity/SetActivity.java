package com.shoudukejiguan.www.activity;

import android.view.View;

import com.shoudukejiguan.www.view.TitleRelativeLayout;
import com.shoudukejiguan.www.R;

public class SetActivity extends BaseActivity {

    private TitleRelativeLayout tr_user_info;


    @Override
    protected int getRid() {
        return R.layout.activity_set;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("设置");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tr_user_info = getView(R.id.tr_user_info);
        tr_user_info.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tr_user_info:
                userInfo();
                break;
        }
    }

    private void userInfo() {
        skip(UserInfoActivity.class);
    }
}
