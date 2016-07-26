package com.shoudukejiguan.www.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shoudukejiguan.www.R;

public class ForgotPasswordActivity extends BaseActivity {

    @Override
    protected int getRid() {
        return R.layout.activity_forgot_password;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("忘记密码");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }
}
