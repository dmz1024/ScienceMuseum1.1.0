package com.shoudukejiguan.www.activity;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserLevelActivity extends BaseActivity {
    private CircleImageView iv_img;

    @Override
    protected int getRid() {
        return R.layout.activity_user_level;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("会员等级");
    }

    @Override
    protected void initData() {
        Glide.with(this).load("http://img5.duitang.com/uploads/item/201411/29/20141129233121_GQPWn.thumb.700_0.jpeg").into(iv_img);

    }

    @Override
    protected void initView() {
        iv_img = getView(R.id.iv_img);
    }
}
