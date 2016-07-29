package com.shoudukejiguan.www.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.VideoSigna;
import com.shoudukejiguan.www.util.Util;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class IndexFilmAdapter extends ListBaseAdapter<VideoSigna.Data, IndexFilmAdapter.VideoSignaHolder> {


    public IndexFilmAdapter(List<VideoSigna.Data> list) {
        super(list);
    }

    @Override
    protected void bindHolder(VideoSignaHolder holder, int position) {
        Glide.with(Util.getApplication()).load("http://cdn.duitang.com/uploads/item/201412/04/20141204163409_Tdusf.thumb.700_0.jpeg")
                .into(holder.iv_img);
    }

    @Override
    protected int getViewRid() {
        return R.layout.item_index_film;
    }

    @Override
    public VideoSignaHolder getViewHolder(View view) {
        return new VideoSignaHolder(view);
    }

    public class VideoSignaHolder extends RecyclerView.ViewHolder {
        public ImageView iv_img;

        public VideoSignaHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }
}
