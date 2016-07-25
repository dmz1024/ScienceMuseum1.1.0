package com.shoudukejiguan.www.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shoudukejiguan.www.R;

public class LoginActivity extends BaseActivity {

    @Override
    protected int getRid() {
        return R.layout.activity_login;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("登录");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }
}
