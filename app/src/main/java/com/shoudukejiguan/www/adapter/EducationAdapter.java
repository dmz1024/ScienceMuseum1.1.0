package com.shoudukejiguan.www.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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

    @Override
    protected void bindHolder(EducationHolder holder, int position) {
        Glide.with(Util.getApplication()).load("http://cdn.duitang.com/uploads/item/201412/04/20141204163409_Tdusf.thumb.700_0.jpeg")
                .into(holder.iv_img);
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

        public EducationHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }
}
