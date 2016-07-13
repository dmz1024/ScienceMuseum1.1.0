package com.shoudukejiguan.www.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.shoudukejiguan.www.view.TitleBar;

import java.util.LinkedList;
import java.util.List;

/**
 * activity基类
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, TitleBar.OnRightClickListener, TitleBar.OnLeftClickListener, PopupWindow.OnDismissListener {
    private final static List<BaseActivity> activities = new LinkedList<>();
    private LinearLayout ll_root;
    public TitleBar title_bar;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            handler(msg);
        }
    };

    /**
     * 处理Handler事件
     *
     * @param msg
     */
    public void handler(Message msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeActivity(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addActivity(this);
        initContentView();
        int rid = getRid();
        if (rid != 0) {
            setContentView(rid);
        }

        initView();
        if (getIsInit()) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    initData();
                    initTitleBar();
                }
            }, 0);
        } else {
            initData();
            initTitleBar();
        }


    }

    protected boolean getIsInit() {
        return false;
    }

    protected abstract int getRid();


    protected abstract void initTitleBar();

    private void initContentView() {
        ViewGroup viewGroup = getView(android.R.id.content);
        viewGroup.removeAllViews();
        ll_root = new LinearLayout(this);
        ll_root.setOrientation(LinearLayout.VERTICAL);
        viewGroup.addView(ll_root);
        ll_root.addView(title_bar = new TitleBar(this));
        title_bar.setOnLeftClickListener(this);
        title_bar.setOnRightClickListener(this);
        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


    }


    protected abstract void initData();

    protected abstract void initView();


    /**
     * 代替findViewById
     *
     * @param id
     * @param <E>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <E extends View> E getView(int id) {
        try {
            return (E) findViewById(id);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, ll_root, true);
    }

    @Override
    public void setContentView(View view) {
        ll_root.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        ll_root.addView(view, params);
    }

    /**
     * title_bar右侧点击事件
     */
    @Override
    public void right() {

    }

    @Override
    public void left() {
        finish();
    }

    /**
     * Activity跳转,不销毁当前Activity
     *
     * @param clx
     */
    public void skip(Class clx) {
        startActivity(new Intent(this, clx));
    }

    /**
     * Activity跳转,销毁当前Activity
     *
     * @param clx
     */
    public void skipFinish(Class clx) {
        startActivity(new Intent(this, clx));
        finish();
    }


    /**
     * view点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            left();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * TextView设置字体颜色
     *
     * @param tv
     * @param rid
     */
    public void setTextColor(TextView tv, int rid) {
        tv.setTextColor(getResources().getColor(rid));
    }


    /**
     * 返回titleBar对象
     *
     * @return
     */
    public TitleBar getTitleBarTitle() {
        return title_bar;
    }


    public static void addActivity(BaseActivity activity) {
        synchronized (activities) {
            activities.add(activity);
        }
    }

    public static void removeActivity(Activity activity) {
        synchronized (activities) {
            activities.remove(activity);
        }
    }

    public void finishAll() {
        /*
         * 复制了一份mActivities集合
		 *(预防在finish()后调用调用activity的onDestroy()方法,
		 *在activity的onDestroy()又设置了removeActivity()方法,在遍历过程中无法移除会报异常的)
		 *所有复制一份
		 */
        List<Activity> copy;
        synchronized (activities) {
            copy = new LinkedList<Activity>(activities);
        }
        for (Activity activity : copy) {
            activity.finish();
        }
        // 杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
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
    public void onDismiss() {
        backgroundAlpha(1f);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }


    public boolean isPad() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        // 屏幕宽度
        float screenWidth = display.getWidth();
        // 屏幕高度
        float screenHeight = display.getHeight();

        if (screenHeight < screenWidth) {
            return true;
        } else {
            return false;
        }
    }

}
