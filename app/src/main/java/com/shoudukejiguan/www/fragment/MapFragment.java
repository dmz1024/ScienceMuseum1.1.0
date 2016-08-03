package com.shoudukejiguan.www.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.andview.refreshview.XWebView;
import com.shoudukejiguan.www.R;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class MapFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private String url;
    private boolean isRefresh = false;
    private WebView webview;
    private OnReturnTitleListener onReturnTitleListener;

    public static MapFragment getInstance(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        MapFragment mapFragment = new MapFragment();
        mapFragment.setArguments(bundle);
        return mapFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            url = bundle.getString("url");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_load, null);
        final ProgressBar pb_load = (ProgressBar) view.findViewById(R.id.pb_load);
        webview = (WebView) view.findViewById(R.id.webview);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
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
                isRefresh = false;
                swipeRefreshLayout.setRefreshing(false);
                if (onReturnTitleListener != null) {
                    onReturnTitleListener.title(view.getTitle());
                }
            }
        });
        swipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        }, 50);

        return view;
    }


    @Override
    public void onRefresh() {
        if (isRefresh) {
            Log.d("这里", "哈哈");
            return;
        } else {
            isRefresh = true;
        }
        webview.loadUrl(url);
    }


    public interface OnReturnTitleListener {
        void title(String title);
    }

    public void setOnReturnTitleListener(OnReturnTitleListener onReturnTitleListener) {
        this.onReturnTitleListener = onReturnTitleListener;
    }


}
