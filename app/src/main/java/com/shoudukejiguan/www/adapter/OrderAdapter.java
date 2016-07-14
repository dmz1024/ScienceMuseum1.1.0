package com.shoudukejiguan.www.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.Order;
import com.shoudukejiguan.www.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class OrderAdapter extends ListBaseAdapter<Order.Data, OrderAdapter.OrderHolder> {
    private LayoutInflater inflater;
    private Map<Integer, Map<Integer, String>> map;

    public OrderAdapter(List<Order.Data> list) {
        super(list);
        map = new HashMap<>();
    }

    public OrderAdapter(List<Order.Data> list, LayoutInflater inflater) {
        this(list);
        this.inflater = inflater;
    }

    @Override
    protected void bindHolder(final OrderHolder holder, int position) {
        Glide.with(Util.getApplication()).load("http://cdn.duitang.com/uploads/item/201412/04/20141204163409_Tdusf.thumb.700_0.jpeg")
                .into(holder.iv_img);
        final List<String> list = new ArrayList<>();
        for (int i = 1; i < 15; i++) {
            list.add("8:00 场次" + i);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(Util.getApplication());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.rv_cc.setLayoutManager(layoutManager);
        holder.rv_cc.setAdapter(new OrderCcAdapter(list, map, position));
    }

    @Override
    protected int getViewRid() {
        return R.layout.item_order;
    }

    @Override
    public OrderHolder getViewHolder(View view) {
        return new OrderHolder(view);
    }

    public class OrderHolder extends RecyclerView.ViewHolder {
        public ImageView iv_img;
        public RecyclerView rv_cc;

        public OrderHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            rv_cc = (RecyclerView) itemView.findViewById(R.id.rv_cc);
        }
    }
}
