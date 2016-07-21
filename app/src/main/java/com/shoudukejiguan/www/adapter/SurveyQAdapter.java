package com.shoudukejiguan.www.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.Survey;
import com.shoudukejiguan.www.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by dengmingzhi on 16/7/12.
 */
public class SurveyQAdapter extends ListBaseAdapter<Survey.Data, SurveyQAdapter.SurveyHolder> {
    private Map<Integer, Map<Integer, String>> map;
    private Map<Integer, Integer> countMap;

    public SurveyQAdapter(List<Survey.Data> list) {
        super(list);
    }

    public SurveyQAdapter(List<Survey.Data> list, Map<Integer, Map<Integer, String>> map, Map<Integer, Integer> countMap) {
        super(list);
        this.map = map;
        this.countMap = countMap;
    }

    @Override
    protected void bindHolder(final SurveyHolder holder, final int position) {
        Survey.Question data1 = new Survey.Question();
        data1.a = "问题1";
        Survey.Question data2 = new Survey.Question();
        data2.a = "问题2";
        Survey.Question data3 = new Survey.Question();
        data2.a = "问题3";
        List<Survey.Question> list = new ArrayList<>();
        list.add(data1);
        list.add(data2);
        list.add(data3);
        setA(holder.rv_a, list, position);
        if (!countMap.containsKey(position)) {
            countMap.put(position, 1);
        }

        holder.tv_title.setText("Q" + position + "：您经常浏览首都科学技术馆？" + position);
    }

    /**
     * 场次
     *
     * @param rv_cc
     * @param list
     * @param position
     */
    private void setA(RecyclerView rv_cc, List<Survey.Question> list, int position) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(Util.getApplication());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_cc.setLayoutManager(layoutManager);
        Map<Integer, String> currentMap = map.get(position);
        if (currentMap == null) {
            currentMap = new HashMap<>();
            map.put(position, currentMap);
        }
        rv_cc.setAdapter(new SurveyAAdapter(list, currentMap));
    }


    @Override
    protected int getViewRid() {
        return R.layout.item_survey_q;
    }

    @Override
    public SurveyHolder getViewHolder(View view) {
        return new SurveyHolder(view);
    }

    public class SurveyHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public RecyclerView rv_a;


        public SurveyHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            rv_a = (RecyclerView) itemView.findViewById(R.id.rv_a);
        }
    }
}
