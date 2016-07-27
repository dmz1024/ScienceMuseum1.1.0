package com.shoudukejiguan.www.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by dengmingzhi on 16/7/8.
 */
public class LinearLayoutTab extends LinearLayout {
    public LinearLayoutTab(Context context) {
        this(context, null);
    }

    public LinearLayoutTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearLayoutTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

    }

}
