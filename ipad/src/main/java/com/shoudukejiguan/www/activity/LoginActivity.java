package com.shoudukejiguan.www.activity;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.api.ApiRequest;
import com.shoudukejiguan.www.constant.ApiConstant;
import com.shoudukejiguan.www.entity.Message;
import com.shoudukejiguan.www.entity.UserLogin;
import com.shoudukejiguan.www.util.SharedPreferenUtil;
import com.shoudukejiguan.www.view.CustEdit;
import com.shoudukejiguan.www.view.MyToast;
import com.shoudukejiguan.www.view.TextImage;

import java.util.Map;

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
        isRemember = new SharedPreferenUtil(this).getData("isRemember");
        if (isRemember) {
            tv_remember.setDrawable(R.mipmap.icon_login_checked);
            Map<String, String> map = new SharedPreferenUtil(this, "userLogin").getData(new String[]{"username", "password"});
            ce_nick.setContent(map.get("username"));
            ce_password.setContent(map.get("password"));
        }
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
        final String userName = ce_nick.getContent();
        if (TextUtils.isEmpty(userName)) {
            MyToast.showToast("请输入用户名!");
            return;
        }

        final String password = ce_password.getContent();
        if (TextUtils.isEmpty(password)) {
            MyToast.showToast("请输入密码");
            return;
        }

        new ApiRequest<UserLogin>(this, ApiConstant.LOGIN, UserLogin.class) {
            @Override
            protected void noNetWork() {
                MyToast.showToast("网络无连接，请检查网络");
            }

            @Override
            protected void success(UserLogin userLogin) {
                if (userLogin.result == 0) {
                    MyToast.showToast("登录成功!");
                    if (isRemember) {
                        new SharedPreferenUtil(LoginActivity.this).setData("isRemember", true);
                        new SharedPreferenUtil(LoginActivity.this, "userLogin").setData(new String[]{"username", userName, "password", password});
                    } else {
                        new SharedPreferenUtil(LoginActivity.this).setData("isRemember", false);
                        new SharedPreferenUtil(LoginActivity.this, "userLogin").setData(new String[]{"username", "", "password", ""});
                    }
                    new SharedPreferenUtil(LoginActivity.this, "userInfo").setData(new String[]{"uid", userLogin.data.uid, "token", userLogin.data.token, "type", userLogin.data.type});
                } else {
                    MyToast.showToast(userLogin.msg);
                    ce_password.setContent("");
                }
            }

            @Override
            protected Map<String, String> map(Map<String, String> map) {
                map.put("username", userName);
                map.put("password", password);
                return map;
            }

            @Override
            protected void onErr() {
                MyToast.showToast("服务器错误，请联系客服!");
            }
        }.post("正在登录....");
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
        skip(ForgotPasswordActivity.class);
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
