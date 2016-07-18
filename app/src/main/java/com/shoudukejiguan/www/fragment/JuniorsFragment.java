package com.shoudukejiguan.www.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.TabEntity;
import com.shoudukejiguan.www.view.Color2Text;
import com.shoudukejiguan.www.view.TouchLinearLayout;

import java.util.ArrayList;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class JuniorsFragment extends BaseFragment {
    private Color2Text tv_title;
    private Color2Text tv_open_time;
    private Color2Text tv_cr_price;
    private Color2Text tv_et_price;
    private Color2Text tv_tips;
    private CommonTabLayout tl_tab;
    private LinearLayout ll_top;
    private float downY;
    private int oldPaddingTop;
    private float deltaY;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_juniors, null);
        initView(view);
        initData();
        return view;
    }


    private void initView(View view) {
        rootView = view;
        ImageView iv = (ImageView) view.findViewById(R.id.iv_img);
        Glide.with(getActivity()).load("http://img1.v.tmcdn.net/img/h000/h08/img20120822145108301270.jpg").into(iv);
        tv_title = (Color2Text) view.findViewById(R.id.tv_title);
        tv_open_time = (Color2Text) view.findViewById(R.id.tv_open_time);
        tv_cr_price = (Color2Text) view.findViewById(R.id.tv_cr_price);
        tv_et_price = (Color2Text) view.findViewById(R.id.tv_et_price);
        tv_tips = (Color2Text) view.findViewById(R.id.tv_tips);
        tl_tab = (CommonTabLayout) view.findViewById(R.id.tl_tab);
        ll_top = (LinearLayout) view.findViewById(R.id.ll_top);

//        ViewTreeObserver vto = ll_top.getViewTreeObserver();
//        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                ll_top.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                oldPaddingTop = ll_top.getPaddingTop();
//            }
//        });

        ll_top.setOnTouchListener(getListener());
    }

    private void initData() {
        tv_title.setTextContent("\"生命乐章主展\"、\"生活追梦主展\"、\"生存对话主展\"");
        tv_open_time.setTextContent("日常 08:00至 20：00,节假日：10:00 至 21:00");
        tv_cr_price.setTextContent("￥35");
        tv_et_price.setTextContent("￥17");
        tv_tips.setTextContent("您可以带您的家人一起来感受首都科技馆的氛围,这里是科学殿堂,梦想的开始。一人一票，儿童票半价，一票只可使用一次。");
        initTab();
    }

    private void initTab() {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        mTabEntities.add(new TabEntity("生命乐章展厅", 0, 0));
        mTabEntities.add(new TabEntity("生活追梦展厅", 0, 0));
        mTabEntities.add(new TabEntity("生存对话主题展厅", 0, 0));
        ArrayList<Fragment> mFragments = new ArrayList<>();
//        tl_tab.setTabData(mTabEntities);
        mFragments.add(new JuniorsDataFragment() {
            @Override
            public View.OnTouchListener setListener() {
                return getListener();
            }
        });

        mFragments.add(new JuniorsDataFragment() {
            @Override
            public View.OnTouchListener setListener() {
                return getListener();
            }
        });
        mFragments.add(new JuniorsDataFragment() {
            @Override
            public View.OnTouchListener setListener() {
                return getListener();
            }
        });

        tl_tab.setTabData(mTabEntities, getActivity(), R.id.fg_juniors, mFragments);

    }


    private View.OnTouchListener getListener() {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("运行", "fff");
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downY = motionEvent.getY();
                        oldPaddingTop = rootView.getPaddingTop();
                        return false;
                    case MotionEvent.ACTION_MOVE:
                        float tab_y = tl_tab.getY();
                        Log.d("运行", tab_y + "");
                        Log.d("deltaY", deltaY + "");
                            deltaY = motionEvent.getY() - downY;
                            if (deltaY >= 0) {
                                if (tab_y > 0) {
//                                    if (rootView.getPaddingTop() >= 0) {
//                                        rootView.setPadding(rootView.getPaddingLeft(), 0, rootView.getPaddingRight(), rootView.getPaddingBottom());
//                                        return true;
//                                    }
                                    rootView.setPadding(rootView.getPaddingLeft(), (int) (oldPaddingTop + deltaY), rootView.getPaddingRight(), rootView.getPaddingBottom());
                                    return true;
                                }

                            } else {
                                if (tab_y <= 0) {
                                    return false;
                                } else {
                                    rootView.setPadding(rootView.getPaddingLeft(), (int) (oldPaddingTop + deltaY), rootView.getPaddingRight(), rootView.getPaddingBottom());
                                    return true;
                                }

                            }
                        break;

                    case MotionEvent.ACTION_UP:
                        if (deltaY >= 0 && rootView.getPaddingTop() >= 0) {
                            rootView.setPadding(rootView.getPaddingLeft(), 0, rootView.getPaddingRight(), rootView.getPaddingBottom());
                            return true;
                        }
                        return false;
                }

                return true;
            }

        };
    }
}

