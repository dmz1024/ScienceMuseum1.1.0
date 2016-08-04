package com.shoudukejiguan.www.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.activity.WebViewActivity;
import com.shoudukejiguan.www.entity.StyleShow;
import com.shoudukejiguan.www.util.Util;
import com.shoudukejiguan.www.R;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class StyleShowAdapter extends ListBaseAdapter<StyleShow.Data, StyleShowAdapter.StyleShowHolder> {

    public StyleShowAdapter(List<StyleShow.Data> list) {
        super(list);
    }

    public StyleShowAdapter(List<StyleShow.Data> list, Context context) {
        super(list,context);
    }

    @Override
    protected void bindHolder(StyleShowHolder holder, int position) {
        final StyleShow.Data data = list.get(position);
        Glide.with(Util.getApplication()).load(data.thumb)
                .into(holder.iv_img);
        holder.tv_title.setText(data.title);
        holder.tv_content.setText(data.introduce);
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
        return R.layout.item_style_show;
    }

    @Override
    public StyleShowHolder getViewHolder(View view) {
        return new StyleShowHolder(view);
    }

    public class StyleShowHolder extends RecyclerView.ViewHolder {
        public ImageView iv_img;
        public TextView tv_title;
        public TextView tv_content;
        public View itemView;

        public StyleShowHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
