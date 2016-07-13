package com.shoudukejiguan.www.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by dengmingzhi on 16/6/16.
 */
public class BaseFragment extends Fragment implements PopupWindow.OnDismissListener,View.OnClickListener {

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
}
