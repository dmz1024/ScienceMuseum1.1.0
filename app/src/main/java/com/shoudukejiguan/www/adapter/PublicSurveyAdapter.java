package com.shoudukejiguan.www.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.activity.WriteSurveyActivity;
import com.shoudukejiguan.www.entity.PublicSurvey;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class PublicSurveyAdapter extends ListBaseAdapter<PublicSurvey.Data, PublicSurveyAdapter.PublicSurveyHolder> {
    private Context ctx;

    public PublicSurveyAdapter(List<PublicSurvey.Data> list) {
        super(list);
    }

    public PublicSurveyAdapter(Context ctx, List<PublicSurvey.Data> list) {
        super(list);
        this.ctx = ctx;
    }

    @Override
    protected void bindHolder(PublicSurveyHolder holder, final int position) {
        holder.tv_num.setText(position + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, WriteSurveyActivity.class);
                intent.putExtra("title", "首都科学技术馆游客满意度调查" + position);
                intent.putExtra("id", position + "");
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    protected int getViewRid() {
        return R.layout.item_public_survey;
    }

    @Override
    public PublicSurveyHolder getViewHolder(View view) {
        return new PublicSurveyHolder(view);
    }

    public class PublicSurveyHolder extends RecyclerView.ViewHolder {
        public TextView tv_num;
        public TextView tv_title;
        public TextView tv_content;
        public View itemView;

        public PublicSurveyHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tv_num = (TextView) itemView.findViewById(R.id.tv_num);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
