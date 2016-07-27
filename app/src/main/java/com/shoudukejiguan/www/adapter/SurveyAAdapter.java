package com.shoudukejiguan.www.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shoudukejiguan.www.view.TextImage;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.Survey;
import com.shoudukejiguan.www.util.Util;

import java.util.List;
import java.util.Map;

public class SurveyAAdapter extends RecyclerView.Adapter<SurveyAAdapter.ViewHolder> {
    private List<Survey.Question> urlList;
    private Map<Integer, String> map;
//    private Map<Integer, Map<Integer, String>> mapAll;
//    private int positionAll;

    public SurveyAAdapter(List<Survey.Question> urlList, Map<Integer, String> map) {
        this.urlList = urlList;
        this.map = map;
//        this.positionAll = position;
//        this.mapAll = mapAll;
//        map = mapAll.get(positionAll);
//        if (map == null) {
//            map = new HashMap<>();
//        }
//        mapAll.put(position, map);
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!map.containsKey(position)) {
                    map.clear();
                    map.put(position, "");
                    notifyDataSetChanged();
//                    mapAll.put(positionAll, map);
                }

            }
        });

        if (map.containsKey(position)) {
            holder.tv_a.setDrawable(R.mipmap.icon_pay_checked);

        } else {
            holder.tv_a.setDrawable(R.mipmap.icon_pay_check);
        }

    }

    private void textChange(TextView tv, int bg, int color) {
        tv.setBackgroundResource(bg);
        tv.setTextColor(Util.getApplication().getResources().getColor(color));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(Util.getApplication(), R.layout.item_a, null);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextImage tv_a;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_a = (TextImage) itemView.findViewById(R.id.tv_a);
        }
    }
}

