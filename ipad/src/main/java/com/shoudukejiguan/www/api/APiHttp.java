package com.shoudukejiguan.www.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;

import com.shoudukejiguan.www.view.MyToast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * Created by dengmingzhi on 16/5/11.
 */
public abstract class APiHttp {
    private RequestParams params;
    private ProgressDialog pd;
    private static Handler handler;
    private Context ctx;

    public APiHttp(String url, Map<String, String> map, Context ctx) {
        params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());

            }
        }
        this.ctx = ctx.getApplicationContext();
    }

    public void post() {
        if (!isNetworkAvailable(ctx)) {
            noNetWork();
            return;
        }
        x.http().post(params, new MyCallback());
    }

    public void get() {
        if (!isNetworkAvailable(ctx)) {
            noNetWork();
            return;
        }
        x.http().get(params, new MyCallback());
    }

    public void get(String msg) {
        if (!isNetworkAvailable(ctx)) {
            noNetWork();
            return;
        }
        pd = new ProgressDialog(ctx);
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.setMessage(msg);
        pd.show();
        pd.getCurrentFocus().postDelayed(new Runnable() {
            @Override
            public void run() {
                get();
            }
        },300);
    }


    protected void noNetWork() {
        MyToast.showToast("当前网络不可用");
    }

    public void post(String msg) {
        if (!isNetworkAvailable(ctx)) {
            noNetWork();
            return;
        }
        pd = new ProgressDialog(ctx);
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.setMessage(msg);
        pd.show();
        pd.getCurrentFocus().postDelayed(new Runnable() {
            @Override
            public void run() {
                post();
            }
        },300);
    }


    protected abstract void success(String json);

    protected void error() {

    }

    class MyCallback implements Callback.CommonCallback<String> {

        @Override
        public void onSuccess(String json) {
            Log.d("返回的json数据", json);
            success(json);
        }

        @Override
        public void onError(Throwable throwable, boolean b) {
            throwable.printStackTrace();
            error();
        }

        @Override
        public void onCancelled(CancelledException e) {

        }

        @Override
        public void onFinished() {
            if (pd != null) {
                pd.cancel();
            }
        }
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
