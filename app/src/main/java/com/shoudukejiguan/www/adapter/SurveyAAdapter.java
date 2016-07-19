package com.shoudukejiguan.www.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.MyOrder;
import com.shoudukejiguan.www.entity.Survey;
import com.shoudukejiguan.www.util.Util;
import com.shoudukejiguan.www.view.TextImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class SurveyAAdapter extends DefaultAdapter<Survey.Question> {
    public Map<Integer,Integer> map=new HashMap<>();
    public SurveyAAdapter(List<Survey.Question> datas) {
        super(datas);
    }

    @Override
    protected BaseHolder<Survey.Question> getHolder() {
        return new SurveyHolder();
    }

    class SurveyHolder extends BaseHolder<Survey.Question> {
        public TextImage tv_a;


        @Override
        protected void refreshView(Survey.Question data) {
            tv_a.setText(data.a);
            tv_a.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }

        @Override
        protected View initView() {
            View view = View.inflate(Util.getApplication(), R.layout.item_a, null);
            tv_a = (TextImage) view.findViewById(R.id.tv_a);
            return view;
        }
    }
}
