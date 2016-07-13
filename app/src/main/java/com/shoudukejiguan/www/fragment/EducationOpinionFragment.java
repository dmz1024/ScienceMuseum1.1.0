package com.shoudukejiguan.www.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoudukejiguan.www.R;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class EducationOpinionFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getContext(), R.layout.fragment_education_opinion, null);
    }
}
