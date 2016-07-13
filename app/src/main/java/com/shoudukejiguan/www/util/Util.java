package com.shoudukejiguan.www.util;

import android.content.Context;
import android.util.DisplayMetrics;

import com.shoudukejiguan.www.MyApplication;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class Util {
    /**
     * 获取应用的上下文对象，尽量使用该方法获取上下文，防止context被长时间占用造成内存泄漏
     *
     * @return
     */
    public static Context getApplication() {
        return MyApplication.app();
    }

    /**
     * 获取屏幕宽高
     *
     * @return
     */
    public static int getWeight() {
        DisplayMetrics dm = new DisplayMetrics();
        dm = getApplication().getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels; // 屏幕宽（像素，如：480px）
//        int screenHeight = dm.heightPixels; // 屏幕高（像素，如：800px）
        return screenWidth;
    }
}
