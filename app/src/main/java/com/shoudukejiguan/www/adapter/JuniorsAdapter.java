package com.shoudukejiguan.www.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.entity.Juniors;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.util.Util;
import com.shoudukejiguan.www.view.TextImage;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class JuniorsAdapter extends ListBaseAdapter<Juniors.Data, JuniorsAdapter.JuniorsHolder> {

    public JuniorsAdapter(List<Juniors.Data> list) {
        super(list);
    }

    @Override
    protected void bindHolder(JuniorsHolder holder, int position) {
        Juniors.Data data = list.get(position);
        Glide.with(Util.getApplication()).load(data.thumb1).into(holder.iv_img_1);
        Glide.with(Util.getApplication()).load(data.thumb2).into(holder.iv_img_2);
        Glide.with(Util.getApplication()).load(data.thumb).into(holder.iv_img_3);
        holder.tv_title.setText(data.title);
        holder.tv_content.setText(data.introduce);
    }

    @Override
    protected int getViewRid() {
        return R.layout.item_juniors;
    }

    @Override
    public JuniorsHolder getViewHolder(View view) {
        return new JuniorsHolder(view);
    }

    public class JuniorsHolder extends RecyclerView.ViewHolder {
        public ImageView iv_img_1;
        public ImageView iv_img_2;
        public ImageView iv_img_3;
        public TextView tv_title;
        public TextView tv_content;

        public JuniorsHolder(View itemView) {
            super(itemView);
            iv_img_1 = (ImageView) itemView.findViewById(R.id.iv_img_1);
            iv_img_2 = (ImageView) itemView.findViewById(R.id.iv_img_2);
            iv_img_3 = (ImageView) itemView.findViewById(R.id.iv_img_3);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
