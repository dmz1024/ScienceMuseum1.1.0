package com.shoudukejiguan.www.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengmingzhi on 16/5/11.
 * 轮播ViewPager
 */
public class RotationViewPager extends ViewPager {
    private boolean isFirst = true;
    private int viewCount = 0;
    private List<View> views;
    private PagerAdapter mAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    rotation();
                    break;
            }
        }
    };

    private void rotation() {
        if (viewCount <= 1) {
            return;
        }

        setCurrentItem(getCurrentItem() + 1);
        handler.sendEmptyMessageDelayed(1, 2500);
    }

    public RotationViewPager(Context context) {
        super(context);
    }

    public RotationViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置轮播视图
     *
     * @param views
     */
    public void setViews(List<View> views) {
        this.views = views;
        if (views != null && (viewCount = views.size()) > 0) {
            setAdapter(mAdapter = new MyAdapter());
            setCurrentItem(viewCount * 100000);
            handler.sendEmptyMessageDelayed(1, 2500);
        }


    }


    public void setUrls(List<String> list) {
        if (list != null && list.size() > 0) {
            List<View> mViews = new ArrayList<>();
            for (String url : list) {
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(getContext().getApplicationContext()).load(url).into(imageView);
                mViews.add(imageView);
            }
            setViews(mViews);
        }

    }


    public PagerAdapter getAdapter() {
        return mAdapter;
    }

    public void ChangerRotation(boolean type) {
        if (isFirst) {
            isFirst = false;
            return;
        }
        if (type) {
            handler.sendEmptyMessageDelayed(1, 2500);
        } else {
            handler.removeMessages(1);
        }
    }


    class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= viewCount;

            View v = views.get(position);
            ViewParent vp = v.getParent();
            if (vp != null) {
                ViewGroup parent = (ViewGroup) vp;
                parent.removeView(v);
            }
            container.addView(v, 0);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            ((RotationViewPager) container).removeView(views.get(position % views.size()));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handler.removeMessages(1);
                break;
            case MotionEvent.ACTION_UP:
//                if (getCurrentItem() == views.size() - 1) {
//                    handler.sendEmptyMessage(1);
//                } else {
//                    handler.sendEmptyMessageDelayed(1, 2500);
//                }
                handler.sendEmptyMessageDelayed(1, 2500);
                break;
            case MotionEvent.ACTION_MOVE:
                requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_CANCEL:
                requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onTouchEvent(ev);
    }
}
