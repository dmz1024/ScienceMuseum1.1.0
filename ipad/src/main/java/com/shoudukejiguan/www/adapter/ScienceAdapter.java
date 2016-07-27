package com.shoudukejiguan.www.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.Juniors;
import com.shoudukejiguan.www.entity.Science;
import com.shoudukejiguan.www.util.Util;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class ScienceAdapter extends ListBaseAdapter<Science.Data, ScienceAdapter.ScienceHolder> {

    public ScienceAdapter(List<Science.Data> list) {
        super(list);
    }

    @Override
    protected void bindHolder(ScienceHolder holder, int position) {
        Glide.with(Util.getApplication()).load("http://cdn.duitang.com/uploads/item/201412/04/20141204163409_Tdusf.thumb.700_0.jpeg")
                .into(holder.iv_img);
    }

    @Override
    protected int getViewRid() {
        return R.layout.item_science;
    }

    @Override
    public ScienceHolder getViewHolder(View view) {
        return new ScienceHolder(view);
    }

    public class ScienceHolder extends RecyclerView.ViewHolder {
        public ImageView iv_img;

        public ScienceHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }
}
