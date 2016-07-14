package com.shoudukejiguan.www.fragment;

import android.content.Context;

import com.shoudukejiguan.www.adapter.OrderAdapter;
import com.shoudukejiguan.www.adapter.StyleShowAdapter;
import com.shoudukejiguan.www.entity.Order;
import com.shoudukejiguan.www.entity.StyleShow;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class OrderFragment extends ListDataBaseFragment<Order, Order.Data, OrderAdapter> {
    @Override
    protected OrderAdapter getAdapter(Context context, List<Order.Data> totalList) {
        return new OrderAdapter(totalList,getActivity().getLayoutInflater());
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
}
