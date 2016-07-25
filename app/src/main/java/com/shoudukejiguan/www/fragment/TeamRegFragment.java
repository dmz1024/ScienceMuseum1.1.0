package com.shoudukejiguan.www.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.api.APiHttp;
import com.shoudukejiguan.www.constant.ApiConstant;
import com.shoudukejiguan.www.view.CustEdit;
import com.shoudukejiguan.www.view.MyToast;
import com.shoudukejiguan.www.view.TextImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dengmingzhi on 16/7/25.
 */
public class TeamRegFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_reg, null);
        return view;
    }

}
