package com.shoudukejiguan.www.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by dengmingzhi on 16/6/16.
 */
public abstract class BaseTestFragment extends Fragment implements PopupWindow.OnDismissListener, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private boolean isRefresh;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isCanRefresh;

    private boolean isFirst = true;

    private boolean isVisible;

    private boolean isCanFirstInitData;

    private boolean isOnlyInitOne;


    /**
     * 标志位，标志视图已经初始化完成
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
        Log.d("可见", getClass().getName());

        if (isCanFirstInitData) {
            isCanFirstInitData = false;
            return;
        }

        if (isOnlyInitOne) {
            if (!isFirst) {
                return;
            }
        }

        if (!isVisible) {
            return;
        }


        if (!isPrepared) {
            return;
        } else {
            isFirst = false;
        }

        if (isCanRefresh) {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                }
            });

            swipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onRefresh();
                }
            },150);

        } else {
            swipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    initData();
                }
            }, 50);
        }

    }


    /**
     * 不可见
     */
    protected void onInvisible() {
        Log.d("不可见", getClass().getName());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isCanFirstInitData = isFirstInitData();
        isOnlyInitOne = isOnlyInitOne();
        swipeRefreshLayout = new SwipeRefreshLayout(getContext());
        SwipeRefreshLayout.LayoutParams params = new SwipeRefreshLayout.LayoutParams(SwipeRefreshLayout.LayoutParams.MATCH_PARENT
                , SwipeRefreshLayout.LayoutParams.MATCH_PARENT);
        swipeRefreshLayout.setLayoutParams(params);
        View view = inflater.inflate(getViewId(), null);
        initView(view);
        isPrepared = true;
        swipeRefreshLayout.addView(view);
        isCanRefresh = isSetRefreshListener();
        if (isCanRefresh) {
            swipeRefreshLayout.setOnRefreshListener(this);
            if (isCanFirstInitData) {
                swipeRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(true);
                    }
                });

                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onRefresh();
                    }
                },150);
            }
        } else {
            swipeRefreshLayout.setEnabled(false);
            if (isCanFirstInitData) {
                initData();
            }

        }

        return swipeRefreshLayout;
    }

    protected boolean isOnlyInitOne() {
        return true;
    }


    @Override
    public void onRefresh() {
        if (isRefresh) {
            return;
        } else {
            isRefresh = true;
        }
        initData();
    }


    protected void setStopRefresh() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                isRefresh = false;
                swipeRefreshLayout.setRefreshing(false);
            }
        },100);
    }


    /**
     * 是否在加载视图的同时加载数据
     *
     * @return
     */
    protected boolean isFirstInitData() {
        return false;
    }

    /**
     * 是否设置下拉刷新
     *
     * @return
     */
    protected boolean isSetRefreshListener() {
        return true;
    }

    /**
     * 初始化数据
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
    protected abstract int getViewId();

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }


    public int dp2Px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public int px2Dp(float px) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void onDismiss() {
        backgroundAlpha(1f);
    }

    public void skip(Class clx) {
        Intent intent = new Intent(getContext(), clx);
        startActivity(intent);
    }


}
