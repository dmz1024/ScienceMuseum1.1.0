package com.shoudukejiguan.www.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.view.TextImage;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.Order;
import com.shoudukejiguan.www.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class OrderAdapter extends ListBaseAdapter<Order.Data, OrderAdapter.OrderHolder> {
    private Map<Integer, Map<Integer, String>> map;
    private Map<Integer, Integer> countMap;

    public OrderAdapter(List<Order.Data> list, Map<Integer, Map<Integer, String>> map, Map<Integer, Integer> countMap) {
        super(list);
        this.map = map;
        this.countMap = countMap;
    }

    @Override
    protected void bindHolder(final OrderHolder holder, final int position) {
        Glide.with(Util.getApplication()).load("http://cdn.duitang.com/uploads/item/201412/04/20141204163409_Tdusf.thumb.700_0.jpeg")
                .into(holder.iv_img);
        final List<String> list = new ArrayList<>();
        for (int i = 1; i < 15; i++) {
            list.add("8:00 场次" + i);
        }
        setCc(holder.rv_cc, list, position);


        if (!countMap.containsKey(position)) {
            countMap.put(position, 1);
        }
        holder.tv_count.setText(countMap.get(position) + "");

        if (position % 2 == 0) {
            holder.tv_open_time.setVisibility(View.VISIBLE);
            holder.rv_cc.setVisibility(View.GONE);
        } else {
            holder.rv_cc.setVisibility(View.VISIBLE);
            holder.tv_open_time.setVisibility(View.GONE);
        }


        holder.tv_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(holder.tv_count.getText().toString());
                if (count <= 1) {
                    return;
                } else {
                    holder.tv_count.setText((count - 1) + "");
                    countMap.put(position, count - 1);
                }
            }
        });

        final int maxCount = 10;
        holder.tv_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(holder.tv_count.getText().toString());
                if (count >= maxCount) {
                    return;
                } else {
                    holder.tv_count.setText((count + 1) + "");
                    countMap.put(position, count + 1);
                }
            }
        });
    }


    /**
     * 场次
     *
     * @param rv_cc
     * @param list
     * @param position
     */
    private void setCc(RecyclerView rv_cc, List<String> list, int position) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(Util.getApplication());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_cc.setLayoutManager(layoutManager);
        rv_cc.setAdapter(new OrderCcAdapter(list, map, position));
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
        public TextView tv_jian;
        public TextView tv_jia;
        public TextView tv_count;
        public Button bt_add;
        public TextView tv_title;
        public TextView tv_type;
        public TextView tv_price;
        public TextImage tv_open_time;

        public OrderHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            rv_cc = (RecyclerView) itemView.findViewById(R.id.rv_cc);
            tv_jian = (TextView) itemView.findViewById(R.id.tv_jian);
            tv_jia = (TextView) itemView.findViewById(R.id.tv_jia);
            tv_count = (TextView) itemView.findViewById(R.id.tv_count);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_type = (TextView) itemView.findViewById(R.id.tv_type);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            bt_add = (Button) itemView.findViewById(R.id.bt_add);
            tv_open_time = (TextImage) itemView.findViewById(R.id.tv_open_time);


        }
    }
}
