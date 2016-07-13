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
public abstract class MainBaseFragment extends BaseFragment implements View.OnClickListener {
    public boolean isFirst = true;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            MainBaseFragment.this.handleMessage(msg);
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getRid(), null);
        initView(view);
        if (isInit()) {
            init();//判断是否在fragment创建的时候就加载数据
        }
        return view;
    }

    protected abstract boolean isInit();

    /**
     * 加载数据
     */
    public void init() {
        titleBar();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isFirst) {//数据是否已经加载过了,如果已经加载过了就不再重新加载
                    return;
                } else {
                    isFirst = false;
                }

                initData();
            }
        }, 100);
    }

    /**
     * 关于titleBar的一些操作
     */
    private void titleBar() {
        TitleBar titleBar = ((BaseActivity) getActivity()).getTitleBarTitle();
        titleBar.toggleTitleBar(isTitleBarShow());
        titleBar.setTvTitleText(getTitleBarTitle());
        titleBar.setTvRightVisi(isRightVisi());
    }

    public void skip(Class clx) {
        Intent intent = new Intent(getContext(), clx);
        startActivity(intent);
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

    ;

    /**
     * 获取titleBar标题
     *
     * @return
     */
    protected String getTitleBarTitle() {
        return "";
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

    /**
     * 处理Handler消息
     *
     * @param msg
     */
    protected void handleMessage(Message msg) {

    }

    @Override
    public void onClick(View view) {

    }

    public void search() {

    }


}
