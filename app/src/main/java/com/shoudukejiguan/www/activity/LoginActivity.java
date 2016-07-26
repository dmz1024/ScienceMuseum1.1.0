package com.shoudukejiguan.www.activity;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.view.CustEdit;
import com.shoudukejiguan.www.view.TextImage;

public class LoginActivity extends BaseActivity {
    private CustEdit ce_nick;
    private CustEdit ce_password;
    private TextImage tv_remember;
    private TextView tv_forgot;
    private TextView tv_reg;
    private Button bt_login;

    private boolean isRemember = false;

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
        ce_nick = getView(R.id.ce_nick);
        ce_password = getView(R.id.ce_password);
        tv_remember = getView(R.id.tv_remember);
        tv_forgot = getView(R.id.tv_forgot);
        tv_reg = getView(R.id.tv_reg);
        bt_login = getView(R.id.bt_login);
        tv_remember.setOnClickListener(this);
        tv_forgot.setOnClickListener(this);
        tv_reg.setOnClickListener(this);
        bt_login.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_remember:
                remember();
                break;
            case R.id.tv_forgot:
                forgot();
                break;
            case R.id.tv_reg:
                reg();
                break;
            case R.id.bt_login:
                login();
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {

    }

    /**
     * 去注册
     */
    private void reg() {
        skip(RegActivity.class);
    }

    /**
     * 忘记密码
     */
    private void forgot() {

    }

    /**
     * 记住密码
     */
    private void remember() {
        if (!isRemember) {
            tv_remember.setDrawable(R.mipmap.icon_login_checked);
        } else {
            tv_remember.setDrawable(R.mipmap.icon_login_check);
        }
        isRemember = !isRemember;
    }
}
