package com.shoudukejiguan.www.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.IndexNews;
import com.shoudukejiguan.www.entity.MyOrder;
import com.shoudukejiguan.www.util.Util;
import com.shoudukejiguan.www.view.TextImage;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class MyOrderItemAdapter extends DefaultAdapter<MyOrder.OrderData> {

    public MyOrderItemAdapter(List<MyOrder.OrderData> datas) {
        super(datas);
    }

    @Override
    protected BaseHolder<MyOrder.OrderData> getHolder() {
        return new NewsHolder();
    }

    class NewsHolder extends BaseHolder<MyOrder.OrderData> {
        private ImageView iv_img;
        private TextView tv_time;
        private TextView tv_title;
        private TextView tv_mp;
        private TextView tv_price;


        @Override
        protected void refreshView(MyOrder.OrderData data) {
            Glide.with(Util.getApplication()).load("http://img5.duitang.com/uploads/item/201411/29/20141129233121_GQPWn.thumb.700_0.jpeg").into(iv_img);
        }

        @Override
        protected View initView() {
            View view = View.inflate(Util.getApplication(), R.layout.item_item_order, null);
            iv_img = (ImageView) view.findViewById(R.id.iv_img);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_mp = (TextView) view.findViewById(R.id.tv_mp);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            return view;
        }
    }
}
