package com.shoudukejiguan.www.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.entity.NoticeBulletin;
import com.shoudukejiguan.www.entity.StyleShow;
import com.shoudukejiguan.www.util.Util;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class NoticeBulletinAdapter extends ListBaseAdapter<NoticeBulletin.Data, NoticeBulletinAdapter.NoticeBulletinHolder> {

    public NoticeBulletinAdapter(List<NoticeBulletin.Data> list) {
        super(list);
    }

    @Override
    protected void bindHolder(NoticeBulletinHolder holder, int position) {
        NoticeBulletin.Data data = list.get(position);
        Glide.with(Util.getApplication()).load(data.thumb)
                .into(holder.iv_img);
        holder.tv_title.setText(data.title);
        holder.tv_time.setText(data.addtime);
    }

    @Override
    protected int getViewRid() {
        return R.layout.item_notice_bulletin;
    }

    @Override
    public NoticeBulletinHolder getViewHolder(View view) {
        return new NoticeBulletinHolder(view);
    }

    public class NoticeBulletinHolder extends RecyclerView.ViewHolder {
        public ImageView iv_img;
        public TextView tv_title;
        public TextView tv_time;

        public NoticeBulletinHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}
