package com.shoudukejiguan.www.api;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by dengmingzhi on 16/5/5.
 * 请求Gson数据的封装类
 */
public abstract class GsonRequest<T> extends Request<T> {
    private final Response.Listener<T> mListener;
    private Gson mGson;
    private Class<T> mClass;

    /**
     * 请求类型
     *
     * @param method
     * @param url
     * @param clazz
     */
    public GsonRequest(int method, String url, Class<T> clazz, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mGson = new Gson();
        mClass = clazz;
        mListener = new Response.Listener<T>() {
            @Override
            public void onResponse(T t) {
                onSuccess(t);
            }
        };
    }

    /**
     * 默认post提交
     *
     * @param url
     * @param clazz
     */
    public GsonRequest(String url, Class<T> clazz, Response.ErrorListener errorListener) {
        this(Method.POST, url, clazz, errorListener);
    }

    /**
     * 成功
     *
     * @param t
     */
    public abstract void onSuccess(T t);


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            Log.d("jsonString", jsonString);
            return Response.success(mGson.fromJson(jsonString, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

}
