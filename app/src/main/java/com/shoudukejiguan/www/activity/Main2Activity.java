package com.shoudukejiguan.www.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shoudukejiguan.www.api.ApiRequest;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.Message;
import com.shoudukejiguan.www.view.MyToast;

import java.util.Map;

public class Main2Activity extends BaseActivity {
    private EditText et_name;
    private EditText et_password;
    private Button bt_login;
    private Button bt_reg;
    @Override
    protected int getRid() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("测试页");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        et_name=getView(R.id.et_name);
        et_password=getView(R.id.et_password);
        bt_login=getView(R.id.bt_login);
        bt_reg=getView(R.id.bt_reg);
        bt_login.setOnClickListener(this);
        bt_reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_login:
                login();
                break;
            case R.id.bt_reg:
                reg();
                break;
        }
    }

    private void reg() {
        final String name=et_name.getText().toString();
        final String password=et_password.getText().toString();
        new ApiRequest<Message>(this, "http://123.118.104.255:8888/test_1/reg.php", Message.class) {
            @Override
            protected void noNetWork() {
                MyToast.showToast("网络无连接，请检查网络");
            }

            @Override
            protected Map<String, String> map(Map<String, String> map) {
                map.put("username", name);
                map.put("password", password);
                return map;
            }

            @Override
            protected void success(Message message) {
                MyToast.showToast(message.msg);
            }

            @Override
            protected void onErr() {
                MyToast.showToast("服务器错误，请联系客服!");
            }
        }.post("正在注册...");

    }

    private void login() {
        final String name=et_name.getText().toString();
        final String password=et_password.getText().toString();
        new ApiRequest<Message>(this, "http://123.118.104.255:8888/test_1/login.php", Message.class) {
            @Override
            protected void noNetWork() {
                MyToast.showToast("网络无连接，请检查网络");
            }

            @Override
            protected Map<String, String> map(Map<String, String> map) {
                map.put("username", name);
                map.put("password", password);
                return map;
            }

            @Override
            protected void success(Message message) {
                MyToast.showToast(message.msg);
            }

            @Override
            protected void onErr() {
                MyToast.showToast("服务器错误，请联系客服!");
            }
        }.post("正在登录...");
    }
}
