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

import com.bumptech.glide.Glide;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.TabEntity;
import com.shoudukejiguan.www.view.Color2Text;

import java.util.ArrayList;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class ScienceFragment extends BaseFragment {
    private Color2Text tv_time;
    private Color2Text tv_address;
    private Color2Text tv_cr_price;
    private Color2Text tv_et_price;
    private Color2Text tv_tips;
    private View rootView;
    private int oldPaddingTop;
    private LinearLayout ll_top;
    private FrameLayout fg_science;
    private float deltaY;
    private float downY;
    private ScienceDataFragment scienceDataFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_science, null);
        initView(view);
        initData();
        return view;
    }


    private void initView(View view) {
        rootView = view;
        ImageView iv = (ImageView) view.findViewById(R.id.iv_img);
        Glide.with(getActivity()).load("http://img5.duitang.com/uploads/item/201411/29/20141129233121_GQPWn.thumb.700_0.jpeg").into(iv);
        tv_time = (Color2Text) view.findViewById(R.id.tv_time);
        tv_address = (Color2Text) view.findViewById(R.id.tv_address);
        tv_cr_price = (Color2Text) view.findViewById(R.id.tv_cr_price);
        tv_et_price = (Color2Text) view.findViewById(R.id.tv_et_price);
        tv_tips = (Color2Text) view.findViewById(R.id.tv_tips);
        ll_top = (LinearLayout) view.findViewById(R.id.ll_top);
        fg_science = (FrameLayout) view.findViewById(R.id.fg_science);

        ViewTreeObserver vto = ll_top.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll_top.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                oldPaddingTop = ll_top.getPaddingTop();
            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float tab_y = fg_science.getY();

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downY = motionEvent.getRawY();
                        oldPaddingTop = view.getPaddingTop();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        deltaY = motionEvent.getRawY() - downY;
                        if (deltaY >= 0 && view.getPaddingTop() >= 0) {
                            view.setPadding(view.getPaddingLeft(), 0, view.getPaddingRight(), view.getPaddingBottom());
                            return true;
                        }

                        view.setPadding(view.getPaddingLeft(), (int) (oldPaddingTop + deltaY), view.getPaddingRight(), view.getPaddingBottom());
                        return true;
                    case MotionEvent.ACTION_UP:
                        if (deltaY >= 0 && view.getPaddingTop() >= 0) {
                            view.setPadding(view.getPaddingLeft(), 0, view.getPaddingRight(), view.getPaddingBottom());
                            return true;
                        }
                        break;
                }

                return true;
            }
        });
    }

    private void initData() {
        tv_time.setTextContent("日常 08:00至 20:00,节假日：10:00 至 21:00");
        tv_address.setTextContent("首都科学技术官 D馆3层生命乐章展厅");
        tv_cr_price.setTextContent("￥35");
        tv_et_price.setTextContent("￥17");
        tv_tips.setTextContent("您可以带您的家人一起来感受首都科技馆的氛围,这里是科学殿堂,梦想的开始。一人一票，儿童票半价，一票只可使用一次。");
        getChildFragmentManager().beginTransaction().add(R.id.fg_science, scienceDataFragment=new ScienceDataFragment()).commit();
        scienceDataFragment.setListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float tab_y = fg_science.getY();
                Log.d("tab",tab_y+"");
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downY = motionEvent.getY();
                        oldPaddingTop = rootView.getPaddingTop();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        deltaY = motionEvent.getY() - downY;
                        if (deltaY >= 0) {
                            if (tab_y > 0) {
                                if (rootView.getPaddingTop() >= 0) {
                                    rootView.setPadding(rootView.getPaddingLeft(), 0, rootView.getPaddingRight(), rootView.getPaddingBottom());
                                    return true;
                                }
                                rootView.setPadding(rootView.getPaddingLeft(), (int) (oldPaddingTop + deltaY), rootView.getPaddingRight(), rootView.getPaddingBottom());
                            } else {
                                return false;
                            }

                            return true;
                        } else {
                            if (tab_y <= 0) {
                                return false;
                            } else {
                                rootView.setPadding(rootView.getPaddingLeft(), (int) (oldPaddingTop + deltaY), rootView.getPaddingRight(), rootView.getPaddingBottom());
                                return true;
                            }

                        }


                    case MotionEvent.ACTION_UP:
//                        if (deltaY >= 0 && rootView.getPaddingTop() >= 0) {
//                            rootView.setPadding(rootView.getPaddingLeft(), 0, rootView.getPaddingRight(), rootView.getPaddingBottom());
//                            return true;
//                        }
                        break;
                }
                if(tab_y>0){
                    return true;
                }
                return false;
            }

        });
    }

}
