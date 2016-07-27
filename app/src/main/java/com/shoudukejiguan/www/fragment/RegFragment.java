package com.shoudukejiguan.www.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shoudukejiguan.www.api.ApiRequest;
import com.shoudukejiguan.www.constant.ApiConstant;
import com.shoudukejiguan.www.view.CustEdit;
import com.shoudukejiguan.www.view.MyToast;
import com.shoudukejiguan.www.view.TextImage;
import com.shoudukejiguan.www.R;

import java.util.Map;

/**
 * Created by dengmingzhi on 16/7/25.
 */
public class RegFragment extends BaseFragment {
    private boolean isRead = false;
    private TextImage ti_protocol;
    private TextView tv_protocol;
    private CustEdit ce_nick;
    private CustEdit ce_tel;
    private CustEdit ce_password;
    private CustEdit ce_password1;
    private CustEdit ce_code;
    private Button bt_getCode;
    private Button bt_reg;
    private int time;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what > 0) {
                bt_getCode.setText(time + "S后重新获取");
                handler.sendEmptyMessageDelayed(time = time - 1, 1000);
            } else {
                bt_getCode.setText("获取验证码");
                bt_getCode.setEnabled(true);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reg, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ce_nick = (CustEdit) view.findViewById(R.id.ce_nick);
        ce_tel = (CustEdit) view.findViewById(R.id.ce_tel);
        ce_password = (CustEdit) view.findViewById(R.id.ce_password);
        ce_password1 = (CustEdit) view.findViewById(R.id.ce_password1);
        ce_code = (CustEdit) view.findViewById(R.id.ce_code);
        bt_getCode = (Button) view.findViewById(R.id.bt_getCode);
        bt_reg = (Button) view.findViewById(R.id.bt_reg);
        ti_protocol = (TextImage) view.findViewById(R.id.ti_protocol);
        tv_protocol = (TextView) view.findViewById(R.id.tv_protocol);
        bt_getCode.setOnClickListener(this);
        bt_reg.setOnClickListener(this);
        tv_protocol.setOnClickListener(this);
        ti_protocol.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_getCode:
                getCode();
                break;
            case R.id.bt_reg:
                reg();
                break;
            case R.id.tv_protocol:
                read();
                break;
            case R.id.ti_protocol:
                selectRead();
                break;
        }
    }

    /**
     * 选择阅读协议
     */
    private void selectRead() {
        if (!isRead) {
            ti_protocol.setDrawable(R.mipmap.icon_login_checked);
        } else {
            ti_protocol.setDrawable(R.mipmap.icon_login_check);
        }
        isRead = !isRead;
    }

    /**
     * 阅读服务协议
     */
    private void read() {

    }


    /**
     * 用户注册
     */
    private void reg() {
        if (!isRead) {
            MyToast.showToast("请先阅读服务协议");
            return;
        }
        final String name = ce_nick.getContent();
        if (TextUtils.isEmpty(name) || name.length() > 6) {
            MyToast.showToast("请输入最多6位的用户名");
            return;
        }

        final String tel = ce_tel.getContent();
        if (tel.length() != 11) {
            MyToast.showToast("请输入正确格式的手机号");
            return;
        }
        final String password = ce_password.getContent();
        if (password.length() < 6 || password.length() > 14) {
            MyToast.showToast("请输入密码6-16位密码");
            return;
        }

        String password1 = ce_password1.getContent();
        if (!TextUtils.equals(password, password1)) {
            MyToast.showToast("确认密码与第一次密码不一致");
            return;
        }

        final String code = ce_code.getContent();
        if (code.length() != 6) {
            MyToast.showToast("请输入6位验证码");
            return;
        }



        new ApiRequest<com.shoudukejiguan.www.entity.Message>(getContext(), ApiConstant.REG, com.shoudukejiguan.www.entity.Message.class) {
            @Override
            protected void noNetWork() {
                MyToast.showToast("网络无连接，请检查网络");
            }

            @Override
            protected Map<String, String> map(Map<String, String> map) {
                map.put("username", name);
                map.put("mobile", tel);
                map.put("password", password);
                map.put("mobilecode", code);
                return map;
            }

            @Override
            protected void success(com.shoudukejiguan.www.entity.Message message) {
                if (message.result == 0) {
                    MyToast.showToast("注册成功!");
                    getActivity().finish();
                } else {
                    MyToast.showToast(message.msg);
                }
            }

            @Override
            protected void onErr() {
                MyToast.showToast("服务器错误，请联系客服!");
            }
        }.post("正在注册...");
    }

    /**
     * 获取验证码
     */
    private void getCode() {
        final String tel = ce_tel.getContent();
        if (tel.length() != 11) {
            MyToast.showToast("请输入正确格式的手机号");
            return;
        }
        time = 60;
        new ApiRequest<com.shoudukejiguan.www.entity.Message>(getContext(), ApiConstant.CODE, com.shoudukejiguan.www.entity.Message.class) {
            @Override
            protected void noNetWork() {
                MyToast.showToast("网络无连接，请检查网络");
            }

            @Override
            protected Map<String, String> map(Map<String, String> map) {
                map.put("sjh", tel);
                return map;
            }

            @Override
            protected void success(com.shoudukejiguan.www.entity.Message message) {
                if (message.result == 0) {
                    MyToast.showToast("验证码已发送,请注意查看短信");
                    bt_getCode.setEnabled(false);
                    handler.sendEmptyMessage(time);
                } else {
                    MyToast.showToast(message.msg);
                }
            }

            @Override
            protected void onErr() {
                MyToast.showToast("服务器错误，请联系客服!");
            }
        }.post("正在获取验证码...");

    }


}
