package com.shoudukejiguan.www;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.xutils.x;

/**
 * Created by dengmingzhi on 16/6/3.
 */
public class MyApplication extends Application {
    public static MyApplication application;
    public static RequestQueue mQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        application = this;
    }

    public static MyApplication app() {
        return application;
    }

    public static RequestQueue getmQueue() {
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(application);
        }
        return mQueue;
    }
}
