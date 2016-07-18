package com.shoudukejiguan.www.activity;

import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.view.Color2Text;

public class PayActivity extends BaseActivity {

    private Color2Text tv_money;

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
        tv_money=getView(R.id.tv_money);
    }
}
