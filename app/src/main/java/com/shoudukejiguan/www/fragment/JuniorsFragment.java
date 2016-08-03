package com.shoudukejiguan.www.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.shoudukejiguan.www.api.ApiRequest;
import com.shoudukejiguan.www.constant.ApiConstant;
import com.shoudukejiguan.www.entity.JuniorsTab;
import com.shoudukejiguan.www.entity.TabEntity;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.view.Color2Text;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class JuniorsFragment extends BaseFragment {
    private Color2Text tv_title;
    private Color2Text tv_open_time;
    private Color2Text tv_cr_price;
    private Color2Text tv_et_price;
    private Color2Text tv_tips;
    private ImageView iv;
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
        iv = (ImageView) view.findViewById(R.id.iv_img);
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
        initTitleBar();
        initTab();
    }

    private void initTitleBar() {
        new ApiRequest<JuniorsTab>(getContext(), ApiConstant.LIST, JuniorsTab.class) {
            @Override
            protected void success(JuniorsTab juniorsTab) {
                if (juniorsTab.result == 0) {
                    if (juniorsTab.data.size() > 0) {
                        JuniorsTab.Data data = juniorsTab.data.get(0);
                        Glide.with(getActivity()).load(data.thumb).into(iv);
                        tv_title.setTextContent(data.title);
                        tv_open_time.setTextContent(data.kfsj);
                        tv_cr_price.setTextContent("￥" + data.crpj);
                        tv_et_price.setTextContent("￥" + data.etpj);
                        tv_tips.setTextContent(data.wxts);
                    }
                }
            }

            @Override
            protected Map<String, String> map(Map<String, String> map) {
                map.put("mid","16");
                map.put("catid","84");
                return map;
            }
        }.post();
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
        mFragments.add(
                new JuniorsDataFragment() {
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

