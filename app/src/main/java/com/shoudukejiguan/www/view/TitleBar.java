package com.shoudukejiguan.www.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shoudukejiguan.www.R;

/**
 * Created by dengmingzhi on 16/4/20.
 */
public class TitleBar extends RelativeLayout implements View.OnClickListener {
    private RelativeLayout titleBar;
    private ImageView iv_left;
    private TextView tv_title;
    private ImageView iv_right;
    private FrameLayout fg_bar;
    private TextView bar_tv_right;
    private OnLeftClickListener onLeftListener;
    private OnRightClickListener onRightListener;

    public TitleBar(Context context) {
        this(context, null);
    }


    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.item_title_bar, this);
        iv_left = (ImageView) findViewById(R.id.bar_left);
        tv_title = (TextView) findViewById(R.id.bar_title);
        iv_right = (ImageView) findViewById(R.id.bar_right);
        fg_bar = (FrameLayout) findViewById(R.id.fg_bar);
        bar_tv_right = (TextView) findViewById(R.id.bar_tv_right);
        titleBar = (RelativeLayout) findViewById(R.id.rl_titleBar);
        initOnclick();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setShowBar();
        }

    }

    /**
     * 设置点击事件
     */
    private void initOnclick() {
        iv_left.setOnClickListener(this);
        iv_right.setOnClickListener(this);
        bar_tv_right.setOnClickListener(this);
    }


    public void setBar_tv_right(String content) {
        bar_tv_right.setText(content);
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void setShowBar() {
        LayoutParams params = (LayoutParams) fg_bar.getLayoutParams();
        params.height = getStatusBarHeight();
        fg_bar.setLayoutParams(params);
        fg_bar.setVisibility(VISIBLE);
    }

    /**
     * 隐藏或显示titleBar
     */
    public void toggleTitleBar(boolean visi) {
        titleBar.setVisibility(visi ? VISIBLE : GONE);
    }


    /**
     * 点击事件处理
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bar_left:
                if (onLeftListener != null) {
                    onLeftListener.left();
                }
                break;
            case R.id.bar_right:
            case R.id.bar_tv_right:
                if (onRightListener != null) {
                    onRightListener.right();
                }
                break;
        }
    }

    /**
     * 设置回调接口
     *
     * @param onLeft
     */
    public void setOnLeftClickListener(OnLeftClickListener onLeft) {
        this.onLeftListener = onLeft;
    }

    public void setOnRightClickListener(OnRightClickListener onRight) {
        this.onRightListener = onRight;
    }


    public void setTvTitleText(String text) {
        tv_title.setText(text);
    }

    public void setTvTitleText(int rId) {
        tv_title.setText(rId);
    }


    public void setTvTitleVisi(boolean visibi) {
        tv_title.setVisibility(visibi ? View.VISIBLE : View.GONE);
    }

    public void setTvRightVisi(boolean visibi) {
        iv_right.setVisibility(visibi ? View.VISIBLE : View.GONE);
    }

    public void setlLeftVisi(boolean visibi) {
        iv_left.setVisibility(visibi ? View.VISIBLE : View.GONE);
    }


    /**
     * 左侧点击事件监听接口
     */
    public interface OnLeftClickListener {
        void left();
    }

    /**
     * 右侧点击事件监听接口
     */
    public interface OnRightClickListener {
        void right();
    }
}
