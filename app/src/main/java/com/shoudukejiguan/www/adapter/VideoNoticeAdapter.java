package com.shoudukejiguan.www.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        VideoNotice.Data data = list.get(position);
        Glide.with(Util.getApplication()).load(data.thumb)
                .into(holder.iv_img);

        holder.tv_title.setText("《" + data.title + "》");
        holder.tv_content.setText(data.wxts);
        holder.tv_director.setText("导演：" + data.daoyan);
        holder.tv_time.setText("上映时间：" + data.addtime);

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
        public TextView tv_title;
        public TextView tv_content;
        public TextView tv_director;
        public TextView tv_time;

        public VideoNoticeHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            tv_director = (TextView) itemView.findViewById(R.id.tv_director);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}
