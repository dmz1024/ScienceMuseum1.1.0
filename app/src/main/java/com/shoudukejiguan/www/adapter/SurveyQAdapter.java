package com.shoudukejiguan.www.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.MyOrder;
import com.shoudukejiguan.www.entity.Survey;
import com.shoudukejiguan.www.view.Color2Text;
import com.shoudukejiguan.www.view.MaxListView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dengmingzhi on 16/7/12.
 */
public class SurveyQAdapter extends ListBaseAdapter<Survey.Data, SurveyQAdapter.SurveyHolder> {

    public SurveyQAdapter(List<Survey.Data> list) {
        super(list);
    }

    @Override
    protected void bindHolder(final SurveyHolder holder, final int position) {
        Survey.Question data1 = new Survey.Question();
        data1.a = "问题1";
        Survey.Question data2 = new Survey.Question();
        data2.a = "问题2";
        List<Survey.Question> list = new ArrayList<>();
        list.add(data1);
        list.add(data2);
        holder.lv_a.setAdapter(new SurveyAAdapter(list));
        holder.tv_title.setText("Q" + position + "：您经常浏览首都科学技术馆？" + position);
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
        public MaxListView lv_a;


        public SurveyHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            lv_a = (MaxListView) itemView.findViewById(R.id.lv_a);
        }
    }
}
