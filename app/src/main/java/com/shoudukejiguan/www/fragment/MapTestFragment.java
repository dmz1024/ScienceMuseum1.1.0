package com.shoudukejiguan.www.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.shoudukejiguan.www.R;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class MapTestFragment extends BaseTestFragment {
    private String url;
    private WebView webview;
    private OnReturnTitleListener onReturnTitleListener;
    private boolean isFistInitData;
    private boolean isCanRefresh = true;

    public static MapTestFragment getInstance(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        MapTestFragment mapFragment = new MapTestFragment();
        mapFragment.setArguments(bundle);
        return mapFragment;
    }

    public static MapTestFragment getInstance(String url, boolean isFistInitData) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putBoolean("isFistInitData", isFistInitData);
        MapTestFragment mapFragment = new MapTestFragment();
        mapFragment.setArguments(bundle);
        return mapFragment;
    }

    public static MapTestFragment getInstance(String url, boolean isFistInitData, boolean isCanRefresh) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putBoolean("isFistInitData", isFistInitData);
        bundle.putBoolean("isCanRefresh", isCanRefresh);
        MapTestFragment mapFragment = new MapTestFragment();
        mapFragment.setArguments(bundle);
        return mapFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            url = bundle.getString("url");
            isFistInitData = bundle.getBoolean("isFistInitData", false);
            isCanRefresh = bundle.getBoolean("isCanRefresh", true);
        }

    }

    @Override
    protected boolean isSetRefreshListener() {
        return isCanRefresh;
    }


    @Override
    protected boolean isFirstInitData() {
        return isFistInitData;
    }



    @Override
    protected void initData() {
        webview.loadUrl(url);
    }

    @Override
    protected void initView(View view) {
        webview = (WebView) view;
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                setStopRefresh();
                if (onReturnTitleListener != null) {
                    onReturnTitleListener.title(view.getTitle());
                }
            }
        });
    }

    @Override
    protected int getViewId() {
        return R.layout.map;
    }


    public interface OnReturnTitleListener {
        void title(String title);
    }

    public void setOnReturnTitleListener(OnReturnTitleListener onReturnTitleListener) {
        this.onReturnTitleListener = onReturnTitleListener;
    }


}
