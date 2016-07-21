package com.shoudukejiguan.www.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shoudukejiguan.www.R;

public class ChildrenParkActivity extends BaseActivity {


    @Override
    protected int getRid() {
        return R.layout.activity_children_park;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("儿童乐园");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }
}
