package com.shoudukejiguan.www.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.shoudukejiguan.www.MyApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dengmingzhi on 16/5/8.
 */
public abstract class ApiRequest<T> {
    private Context ctx;
    private ProgressDialog pd;
    private String url;
    private Class<T> cls;
    /**
     * @param ctx
     * @param url
     * @param cls
     */
    public ApiRequest(Context ctx, String url, Class<T> cls) {
        this.ctx = ctx.getApplicationContext();
        this.url = url;
        this.cls = cls;
    }

    /**
     * 带进度框的get请求
     *
     * @param mes
     */
    public void get(String mes) {
        pd(mes);
        pd.getCurrentFocus().postDelayed(new Runnable() {
            @Override
            public void run() {
                get();
            }
        }, 300);
    }

    public void post(String mes) {
        pd(mes);
        pd.getCurrentFocus().postDelayed(new Runnable() {
            @Override
            public void run() {
                post();
            }
        }, 300);

    }

    private void pd(String mes) {
        pd = new ProgressDialog(ctx);
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.setMessage(mes);
        pd.show();
    }

    public void get() {
        request(Request.Method.GET);
    }

    public void post() {
        request(Request.Method.POST);
    }


    private void request(int method) {
        if (!isNetworkAvailable(ctx)) {
            noNetWork();
            return;
        }
        GsonRequest<T> gsonRequest = new GsonRequest<T>(method, url, cls, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (pd != null) {
                    pd.cancel();
                }
                onErr();
            }
        }) {
            @Override
            public void onSuccess(T t) {
                success(t);
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                return map(map);
            }
        };
        MyApplication.getmQueue().add(gsonRequest);
    }


    /**
     * 没有网络
     */
    protected abstract void noNetWork();

    /**
     * 成功返回数据
     * @param t
     */
    protected abstract void success(T t);
    /**
     * 代码或服务器异常
     */
    protected abstract void onErr();

    protected Map<String, String> map(Map<String, String> map) {
        return map;
    }

    /**
     * 检测当的网络（WLAN、3G/2G）状态
     *
     * @param context Context
     * @return true 表示网络可用
     */
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

}
