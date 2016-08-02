package com.shoudukejiguan.www.activity;

import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends BaseActivity {
    WebView webView;

    @Override
    protected int getRid() {
        return 0;
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initData() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                title_bar.setTvTitleText(webView.getTitle());
            }
        });
        Log.d("url",getIntent().getStringExtra("url"));
        webView.loadUrl(getIntent().getStringExtra("url"));
    }

    @Override
    protected void initView() {
        webView = new WebView(this);
//        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) webView.getLayoutParams();
//        params.width=LinearLayout.LayoutParams.MATCH_PARENT;
//        params.height=LinearLayout.LayoutParams.MATCH_PARENT;
//        webView.setLayoutParams(params);
        setContentView(webView);
    }


    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        super.onKeyDown(keyCode,event);
        return false;
    }
}
