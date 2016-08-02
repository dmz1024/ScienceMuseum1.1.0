package com.shoudukejiguan.www.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.entity.ChildrenPark;
import com.shoudukejiguan.www.util.Util;
import com.shoudukejiguan.www.R;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class ChildrenParkAdapter extends ListBaseAdapter<ChildrenPark.Data, ChildrenParkAdapter.ChildrenParkHolder> {

    public ChildrenParkAdapter(List<ChildrenPark.Data> list) {
        super(list);
    }

    @Override
    protected void bindHolder(ChildrenParkHolder holder, int position) {
        ChildrenPark.Data data = list.get(position);
        Glide.with(Util.getApplication()).load(data.thumb)
                .into(holder.iv_img);
        holder.tv_title.setText(data.title);
        holder.tv_content.setText(data.introduce);
    }

    @Override
    protected int getViewRid() {
        return R.layout.item_children_park;
    }

    @Override
    public ChildrenParkHolder getViewHolder(View view) {
        return new ChildrenParkHolder(view);
    }

    public class ChildrenParkHolder extends RecyclerView.ViewHolder {
        public ImageView iv_img;
        public TextView tv_title;
        public TextView tv_content;

        public ChildrenParkHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
