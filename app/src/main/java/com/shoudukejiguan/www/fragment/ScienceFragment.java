package com.shoudukejiguan.www.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.api.ApiRequest;
import com.shoudukejiguan.www.constant.ApiConstant;
import com.shoudukejiguan.www.entity.JuniorsTab;
import com.shoudukejiguan.www.entity.ScienceTab;
import com.shoudukejiguan.www.view.Color2Text;

import java.util.Map;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class ScienceFragment extends BaseFragment {
    private Color2Text tv_time;
    private Color2Text tv_address;
    private Color2Text tv_cr_price;
    private Color2Text tv_et_price;
    private Color2Text tv_tips;
    private ImageView iv;
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
        iv = (ImageView) view.findViewById(R.id.iv_img);
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
        initTitleBar();
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



    private void initTitleBar() {
        new ApiRequest<ScienceTab>(getContext(), ApiConstant.LIST, ScienceTab.class) {
            @Override
            protected void success(ScienceTab scienceTab) {
                if (scienceTab.result == 0) {
                    if (scienceTab.data.size() > 0) {
                        ScienceTab.Data data = scienceTab.data.get(0);
                        Glide.with(getActivity()).load(data.thumb).into(iv);
                        tv_time.setTextContent(data.kfsj);
                        tv_cr_price.setTextContent("￥" + data.crpj);
                        tv_et_price.setTextContent("￥" + data.etpj);
                        tv_tips.setTextContent(data.wxts);
                        tv_address.setTextContent(data.didian);
                    }
                }
            }

            @Override
            protected Map<String, String> map(Map<String, String> map) {
                map.put("mid","16");
                map.put("catid","85");
                return map;
            }
        }.post();
    }

}
