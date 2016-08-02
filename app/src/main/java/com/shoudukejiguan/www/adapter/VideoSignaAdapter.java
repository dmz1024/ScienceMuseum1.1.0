package com.shoudukejiguan.www.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hedgehog.ratingbar.RatingBar;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.VideoSigna;
import com.shoudukejiguan.www.util.Util;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class VideoSignaAdapter extends ListBaseAdapter<VideoSigna.Data, VideoSignaAdapter.VideoSignaHolder> {


    public VideoSignaAdapter(List<VideoSigna.Data> list) {
        super(list);
    }

    @Override
    protected void bindHolder(VideoSignaHolder holder, int position) {
        VideoSigna.Data data = list.get(position);
        Glide.with(Util.getApplication()).load(data.thumb)
                .into(holder.iv_img);

        holder.tv_title.setText("《" + data.title + "》");
        holder.tv_content.setText(data.wxts);
        holder.tv_director.setText("导演：" + data.daoyan);
        holder.tv_star.setText(data.star);
        holder.ratingbar.setStar(Float.parseFloat(data.star));

    }

    @Override
    protected int getViewRid() {
        return R.layout.item_video_signa;
    }

    @Override
    public VideoSignaHolder getViewHolder(View view) {
        return new VideoSignaHolder(view);
    }

    public class VideoSignaHolder extends RecyclerView.ViewHolder {
        public ImageView iv_img;
        public TextView tv_title;
        public TextView tv_content;
        public TextView tv_director;
        public RatingBar ratingbar;
        public TextView tv_star;
        public Button bt_order;

        public VideoSignaHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            tv_director = (TextView) itemView.findViewById(R.id.tv_director);
            ratingbar = (RatingBar) itemView.findViewById(R.id.ratingbar);
            tv_star = (TextView) itemView.findViewById(R.id.tv_star);
            bt_order = (Button) itemView.findViewById(R.id.bt_order);
        }
    }
}
