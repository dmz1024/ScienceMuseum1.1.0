package com.shoudukejiguan.www.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoudukejiguan.www.activity.BaseActivity;
import com.shoudukejiguan.www.view.TitleBar;

/**
 * Created by dengmingzhi on 16/6/3.
 */
public abstract class MainBaseFragment extends BaseFragment implements View.OnClickListener {
    public boolean isFirst = true;
    public boolean isVisible;
    /**
     * 标志位，标志已经初始化完成
     */
    public boolean isPrepared;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * 可见
     */
    protected void onVisible() {

        if (!isFirst) {
            return;
        }
        isFirst = false;
        if (!isPrepared || !isVisible) {
            return;
        }
        initData();
    }


    /**
     * 不可见
     */
    protected void onInvisible() {


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getRid(), null);
        initView(view);
        if (isInit()) {
            isFirst = false;
            initData();
        }
        isPrepared = true;
        return view;
    }

    protected abstract boolean isInit();

    /**
     * 加载数据
     */
    public void init() {
//        if (!isFirst) {//数据是否已经加载过了,如果已经加载过了就不再重新加载
//            return;
//        } else {
//            isFirst = false;
//        }
//
//        initData();
    }


    public void skip(Class clx) {
        Intent intent = new Intent(getContext(), clx);
        startActivity(intent);
    }


    /**
     * 加载数据
     */
    protected abstract void initData();

    /**
     * 初始化视图
     *
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 获取视图资源id
     *
     * @return
     */
    protected abstract int getRid();


    @Override
    public void onClick(View view) {

    }


}
