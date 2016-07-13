package com.shoudukejiguan.www.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.shoudukejiguan.www.R;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by dengmingzhi on 16/7/11.
 */
public class RotationRelativeLayout extends RelativeLayout {
    private RotationViewPager viewPager;
    private CircleIndicator indicator;

    public RotationRelativeLayout(Context context) {
        this(context, null);
    }

    public RotationRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotationRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.layout_rotation, this);
        viewPager = (RotationViewPager) findViewById(R.id.rvp_rotation);
        indicator = (CircleIndicator) findViewById(R.id.indicator);
    }


    public void setUrls(List<String> list) {
        viewPager.setUrls(list);
//        indicator.setViewPager(viewPager);
    }

    public void setViews(List<View> list) {
        viewPager.setViews(list);
//        indicator.setViewPager(viewPager);
    }

}
