package com.shoudukejiguan.www.fragment;

import android.content.Context;

import com.shoudukejiguan.www.adapter.MyOrderAdapter;
import com.shoudukejiguan.www.adapter.NewsAdapter;
import com.shoudukejiguan.www.entity.MyOrder;
import com.shoudukejiguan.www.entity.News;

import java.util.List;
import java.util.Map;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class MyOrderFragment extends ListDataBaseFragment<MyOrder,MyOrder.Data,MyOrderAdapter> {
    @Override
    protected MyOrderAdapter getAdapter(Context context, List<MyOrder.Data> totalList) {
        return new MyOrderAdapter(totalList);
    }

    @Override
    protected Class<MyOrder> getTClass() {
        return MyOrder.class;
    }

    @Override
    protected String getUrl() {
        return "";
    }

    @Override
    protected Map<String, String> getMap(Map<String, String> map) {
        return map;
    }
}
