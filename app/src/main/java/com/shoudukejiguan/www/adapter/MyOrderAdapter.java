package com.shoudukejiguan.www.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.MyOrder;
import com.shoudukejiguan.www.view.Color2Text;
import com.shoudukejiguan.www.view.MaxListView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dengmingzhi on 16/7/12.
 */
public class MyOrderAdapter extends ListBaseAdapter<MyOrder.Data, MyOrderAdapter.MyOrderHolder> {

    public MyOrderAdapter(List<MyOrder.Data> list) {
        super(list);
    }

    @Override
    protected void bindHolder(final MyOrderHolder holder, final int position) {
        MyOrder.OrderData data1 = new MyOrder.OrderData();
        MyOrder.OrderData data2 = new MyOrder.OrderData();
        List<MyOrder.OrderData> list = new ArrayList<>();
        list.add(data1);
        list.add(data2);
        holder.lv_order.setAdapter(new MyOrderItemAdapter(list));
    }


    @Override
    protected int getViewRid() {
        return R.layout.item_my_order;
    }

    @Override
    public MyOrderHolder getViewHolder(View view) {
        return new MyOrderHolder(view);
    }

    public class MyOrderHolder extends RecyclerView.ViewHolder {
        public TextView tv_sn;
        public TextView tv_type;
        public TextView tv_count;
        public Color2Text tv_price;
        public Button bt_left;
        public Button bt_right;
        public MaxListView lv_order;


        public MyOrderHolder(View itemView) {
            super(itemView);
            tv_sn = (TextView) itemView.findViewById(R.id.tv_sn);
            tv_type = (TextView) itemView.findViewById(R.id.tv_type);
            tv_count = (TextView) itemView.findViewById(R.id.tv_count);
            tv_price = (Color2Text) itemView.findViewById(R.id.tv_price);
            bt_left = (Button) itemView.findViewById(R.id.bt_left);
            bt_right = (Button) itemView.findViewById(R.id.bt_right);
            lv_order = (MaxListView) itemView.findViewById(R.id.lv_order);
        }
    }
}
