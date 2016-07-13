package com.shoudukejiguan.www.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoudukejiguan.www.R;

/**
 */
public class MainPeosonalFragment extends MainBaseFragment {

    @Override
    protected boolean isInit() {
        return false;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getRid() {
        return R.layout.fragment_main_peosonal;
    }

    @Override
    protected boolean isTitleBarShow() {
        return false;
    }
}
