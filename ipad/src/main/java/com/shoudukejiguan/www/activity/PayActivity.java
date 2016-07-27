package com.shoudukejiguan.www.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.view.Color2Text;

public class PayActivity extends BaseActivity {

    private Color2Text tv_money;
    private ImageView iv_zhi;
    private ImageView iv_wei;
    private ImageView iv_outline;
    private Button bt_pay;

    private int payType = 1;

    @Override
    protected int getRid() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("订单支付");
    }

    @Override
    protected void initData() {
        tv_money.setTextContent("￥112.00");
    }

    @Override
    protected void initView() {
        tv_money = getView(R.id.tv_money);
        iv_zhi = getView(R.id.iv_zhi);
        iv_wei = getView(R.id.iv_wei);
        iv_outline = getView(R.id.iv_outline);
        bt_pay = getView(R.id.bt_pay);
        iv_zhi.setOnClickListener(this);
        iv_wei.setOnClickListener(this);
        iv_outline.setOnClickListener(this);
        bt_pay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_zhi:
                pay(1);
                break;
            case R.id.iv_wei:
                pay(2);
                break;
            case R.id.iv_outline:
                pay(3);
                break;
            case R.id.bt_pay:
                goPay();
                break;
        }
    }

    /**
     * 支付
     */
    private void goPay() {

    }

    /**
     * 选择支付
     *
     * @param i
     */
    private void pay(int i) {
        iv_zhi.setImageResource(R.mipmap.icon_pay_check);
        iv_wei.setImageResource(R.mipmap.icon_pay_check);
        iv_outline.setImageResource(R.mipmap.icon_pay_check);
        switch (i) {
            case 1:
                iv_zhi.setImageResource(R.mipmap.icon_pay_checked);
                break;
            case 2:
                iv_wei.setImageResource(R.mipmap.icon_pay_checked);
                break;
            case 3:
                iv_outline.setImageResource(R.mipmap.icon_pay_checked);
                break;
        }
        payType = i;
    }
}
