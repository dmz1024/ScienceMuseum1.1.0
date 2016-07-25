package com.shoudukejiguan.www.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.util.Util;
import com.shoudukejiguan.www.view.PinchImageView;
import com.shoudukejiguan.www.view.TouchImageView;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class GuestsFragment extends BaseFragment {
    private int width;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_guests, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TouchImageView iv_img = (TouchImageView) view.findViewById(R.id.iv_img);
        width = Util.getWidth();
        Log.d("宽度：", width + "");
        float scan = width / 850;
        Log.d("宽度1：", (width / 850) + "");
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_img.getLayoutParams();
        params.height = (int) (((780 * width) / 850) + 0.5f);
//        params.height = 3000;
        params.width = width;
        Log.d("宽度2", (int) (((780 * width) / 805) + 0.5f) + "--" + width);
        iv_img.setLayoutParams(params);
        Glide.with(getActivity()).load("http://keji.lovect.cn/file/upload/201607/01/154820861.jpg").into(iv_img);
    }
}
