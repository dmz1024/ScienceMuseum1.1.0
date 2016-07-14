package com.shoudukejiguan.www.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderCcAdapter extends RecyclerView.Adapter<OrderCcAdapter.ViewHolder> {
    private List<String> urlList;
    private Map<Integer, String> map;
    private Map<Integer, Map<Integer, String>> mapAll;
    private int positionAll;

    public OrderCcAdapter(List<String> urlList, Map<Integer, Map<Integer, String>> mapAll, int position) {
        this.urlList = urlList;
        this.positionAll = position;
        this.mapAll = mapAll;
        map = mapAll.get(positionAll);
        if (map == null) {
            map = new HashMap<>();

        }
        if (map.size() == 0) {
            map.put(0, "");
        }
        mapAll.put(position, map);
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!map.containsKey(position)) {
                    map.clear();
                    notifyDataSetChanged();
                    holder.tv_cc.setBackgroundResource(R.mipmap.icon_time_checked);
                    holder.tv_cc.setTextColor(Util.getApplication().getResources().getColor(R.color.color00acbb));
                    map.put(position, "");
                } else {
                    if (map.size() > 1) {
                        holder.tv_cc.setBackgroundResource(R.mipmap.icon_time_check);
                        holder.tv_cc.setTextColor(Util.getApplication().getResources().getColor(R.color.color666));
                        map.remove(position);
                    }
                }

                mapAll.put(positionAll, map);
            }
        });

        if (map.containsKey(position)) {
            holder.tv_cc.setBackgroundResource(R.mipmap.icon_time_checked);
            holder.tv_cc.setTextColor(Util.getApplication().getResources().getColor(R.color.color00acbb));
        } else {
            holder.tv_cc.setBackgroundResource(R.mipmap.icon_time_check);
            holder.tv_cc.setTextColor(Util.getApplication().getResources().getColor(R.color.color666));
        }

        holder.tv_cc.setText(urlList.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(Util.getApplication(), R.layout.item_tv, null);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_cc;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_cc = (TextView) itemView.findViewById(R.id.tv_cc);
        }
    }
}

