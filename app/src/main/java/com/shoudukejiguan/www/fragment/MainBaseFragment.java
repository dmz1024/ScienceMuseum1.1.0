package com.shoudukejiguan.www.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoudukejiguan.www.activity.BaseActivity;
import com.shoudukejiguan.www.view.TitleBar;

/**
 * Created by dengmingzhi on 16/6/3.
 */
public abstract class MainBaseFragment extends BaseTestFragment {
    public TitleBar titleBar;

    @Override
    protected void onVisible() {
        titleBar();
        super.onVisible();
    }

    /**
     * 关于titleBar的一些操作
     */
    protected void titleBar() {
        titleBar = ((BaseActivity) getActivity()).title_bar;
        titleBar.toggleTitleBar(isTitleBarShow());
        titleBar.setTvTitleText(getTitleBarTitle());
        titleBar.setTvRightVisi(isRightVisi());
    }

    /**
     * titleBar右侧按钮是否显示
     *
     * @return
     */
    protected boolean isRightVisi() {
        return true;
    }



    /**
     * 判断是否显示titleBar
     *
     * @return
     */
    protected boolean isTitleBarShow() {
        return true;
    }


    /**
     * 获取titleBar标题
     *
     * @return
     */
    protected String getTitleBarTitle() {
        return "";
    }


    public void left(){

    }


}
