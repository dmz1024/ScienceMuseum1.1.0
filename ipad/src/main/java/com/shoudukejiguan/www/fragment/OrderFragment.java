package com.shoudukejiguan.www.fragment;

import android.content.Context;

import com.shoudukejiguan.www.adapter.OrderAdapter;
import com.shoudukejiguan.www.adapter.StyleShowAdapter;
import com.shoudukejiguan.www.entity.Order;
import com.shoudukejiguan.www.entity.StyleShow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class OrderFragment extends ListDataBaseFragment<Order, Order.Data, OrderAdapter> {
    private Map<Integer, Map<Integer, String>> mapAll = new HashMap<>();
    private Map<Integer, Integer> countMap = new HashMap<>();

    @Override
    protected OrderAdapter getAdapter(Context context, List<Order.Data> totalList) {
        return new OrderAdapter(totalList, mapAll, countMap);
    }

    @Override
    protected Class<Order> getTClass() {
        return Order.class;
    }

    @Override
    protected String getUrl() {
        return "";
    }

    @Override
    protected Map<String, String> getMap(Map<String, String> map) {
        return map;
    }


    @Override
    public void onRefresh() {
        mapAll.clear();
        countMap.clear();
        super.onRefresh();
    }
}
