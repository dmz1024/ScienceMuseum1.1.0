package com.shoudukejiguan.www.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by dengmingzhi on 16/7/15.
 */
public class TouchLinearLayout extends LinearLayout {
    private OnTouchEnentListener listener;

    public TouchLinearLayout(Context context) {
        this(context, null);
    }

    public TouchLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (listener != null) {

            return listener.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    public void setListener(OnTouchEnentListener listener) {
        this.listener = listener;
    }

    public interface OnTouchEnentListener {
        boolean onTouchEvent(MotionEvent event);
    }
}
