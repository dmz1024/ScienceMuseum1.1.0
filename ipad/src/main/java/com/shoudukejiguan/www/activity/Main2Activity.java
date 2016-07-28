package com.shoudukejiguan.www.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.api.ApiRequest;
import com.shoudukejiguan.www.constant.ApiConstant;
import com.shoudukejiguan.www.entity.Message;
import com.shoudukejiguan.www.view.MyToast;
import com.shoudukejiguan.www.view.PinchImageView;
import com.shoudukejiguan.www.view.RotationRelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main2Activity extends BaseActivity {
    private EditText et_name;
    private EditText et_password;
    private Button bt_login;
    private Button bt_reg;
    private RotationRelativeLayout rrll_banner;
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
        List<String> urlList = new ArrayList<>();
        urlList.add("http://cdn.duitang.com/uploads/item/201412/04/20141204163409_Tdusf.thumb.700_0.jpeg");
        urlList.add("http://img1.v.tmcdn.net/img/h000/h08/img20120822145108301270.jpg");
        urlList.add("http://img5.duitang.com/uploads/item/201411/29/20141129233121_GQPWn.thumb.700_0.jpeg");
        urlList.add("http://img5.duitang.com/uploads/item/201411/29/20141129233121_GQPWn.thumb.700_0.jpeg");
        rrll_banner.setUrls(urlList);
    }

    @Override
    protected void initView() {
        et_name=getView(R.id.et_name);
        rrll_banner=getView(R.id.rrll_banner);
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
        new ApiRequest<Message>(this, "http://123.118.104.255:8888/test_1/reg.php", com.shoudukejiguan.www.entity.Message.class) {
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
            protected void success(com.shoudukejiguan.www.entity.Message message) {
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
        new ApiRequest<Message>(this, "http://123.118.104.255:8888/test_1/login.php", com.shoudukejiguan.www.entity.Message.class) {
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
            protected void success(com.shoudukejiguan.www.entity.Message message) {
                MyToast.showToast(message.msg);
            }

            @Override
            protected void onErr() {
                MyToast.showToast("服务器错误，请联系客服!");
            }
        }.post("正在登录...");
    }
}
