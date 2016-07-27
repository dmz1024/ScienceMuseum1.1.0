package com.shoudukejiguan.www.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.entity.VideoNotice;
import com.shoudukejiguan.www.util.Util;
import com.shoudukejiguan.www.R;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class VideoNoticeAdapter extends ListBaseAdapter<VideoNotice.Data, VideoNoticeAdapter.VideoNoticeHolder> {


    public VideoNoticeAdapter(List<VideoNotice.Data> list) {
        super(list);
    }

    @Override
    protected void bindHolder(VideoNoticeHolder holder, int position) {
        Glide.with(Util.getApplication()).load("http://cdn.duitang.com/uploads/item/201412/04/20141204163409_Tdusf.thumb.700_0.jpeg")
                .into(holder.iv_img);
    }

    @Override
    protected int getViewRid() {
        return R.layout.item_video_notice;
    }

    @Override
    public VideoNoticeHolder getViewHolder(View view) {
        return new VideoNoticeHolder(view);
    }

    public class VideoNoticeHolder extends RecyclerView.ViewHolder {
        public ImageView iv_img;

        public VideoNoticeHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }
}
