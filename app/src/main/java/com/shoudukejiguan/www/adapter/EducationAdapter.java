package com.shoudukejiguan.www.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.activity.WebViewActivity;
import com.shoudukejiguan.www.entity.Education;
import com.shoudukejiguan.www.util.Util;
import com.shoudukejiguan.www.R;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class EducationAdapter extends ListBaseAdapter<Education.Data, EducationAdapter.EducationHolder> {

    public EducationAdapter(List<Education.Data> list) {
        super(list);
    }

    public EducationAdapter(List<Education.Data> list, Context ctx) {
        super(list, ctx);
    }

    @Override
    protected void bindHolder(EducationHolder holder, int position) {
        final Education.Data data = list.get(position);
        Glide.with(Util.getApplication()).load(data.thumb)
                .into(holder.iv_img);
        holder.tv_title.setText(data.title);
        holder.tv_count.setText("名额上限：" + data.mesx + "人   |   已报名：" + data.ybm + "人");
        holder.tv_peoson.setText("适合人群：" + data.shrq);
        holder.tv_price.setText(data.kcpj);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, WebViewActivity.class);
                intent.putExtra("url", "http://keji.lovect.cn/app/show.php?mid=27&itemid=" + data.itemid);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    protected int getViewRid() {
        return R.layout.item_education;
    }

    @Override
    public EducationHolder getViewHolder(View view) {
        return new EducationHolder(view);
    }

    public class EducationHolder extends RecyclerView.ViewHolder {
        public ImageView iv_img;
        public TextView tv_title;
        public TextView tv_count;
        public TextView tv_peoson;
        public TextView tv_price;
        public Button bt_order;

        public EducationHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_count = (TextView) itemView.findViewById(R.id.tv_count);
            tv_peoson = (TextView) itemView.findViewById(R.id.tv_peoson);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            bt_order = (Button) itemView.findViewById(R.id.bt_order);
        }
    }
}
