package com.shoudukejiguan.www.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
public class JuniorsFragment extends BaseFragment {
    private Color2Text tv_title;
    private Color2Text tv_open_time;
    private Color2Text tv_cr_price;
    private Color2Text tv_et_price;
    private Color2Text tv_tips;
    private CommonTabLayout tl_tab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_juniors, null);
        initView(view);
        initData();
        return view;
    }


    private void initView(View view) {
        ImageView iv = (ImageView) view.findViewById(R.id.iv_img);
        Glide.with(getActivity()).load("http://img1.v.tmcdn.net/img/h000/h08/img20120822145108301270.jpg").into(iv);
        tv_title = (Color2Text) view.findViewById(R.id.tv_title);
        tv_open_time = (Color2Text) view.findViewById(R.id.tv_open_time);
        tv_cr_price = (Color2Text) view.findViewById(R.id.tv_cr_price);
        tv_et_price = (Color2Text) view.findViewById(R.id.tv_et_price);
        tv_tips = (Color2Text) view.findViewById(R.id.tv_tips);
        tl_tab = (CommonTabLayout) view.findViewById(R.id.tl_tab);
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
        tl_tab.setTabData(mTabEntities);
//        ArrayList<Fragment> mFragments = new ArrayList<>();
//        mFragments.add(new JuniorsDataFragment());
//        mFragments.add(new JuniorsDataFragment());
//        mFragments.add(new JuniorsDataFragment());
//        tl_tab.setTabData(mTabEntities, getActivity(), R.id.fg_juniors, mFragments);
    }
}
