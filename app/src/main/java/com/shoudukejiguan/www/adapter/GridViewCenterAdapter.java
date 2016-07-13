package com.shoudukejiguan.www.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.view.TextImage;

/**
 * Created by dengmingzhi on 16/4/22.
 */
public class GridViewCenterAdapter extends BaseAdapter {
    private String[] titles;
    private int[] images;
    private Context ctx;

    public GridViewCenterAdapter(Context ctx, String[] titles, int[] images) {
        this.ctx = ctx;
        this.titles = titles;
        this.images = images;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int i) {
        return titles[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(ctx, R.layout.item_gv_center_ti, null);
        TextImage tv = (TextImage) view.findViewById(R.id.tv_center);
        tv.setDrawable(images[i], 2);
        tv.setText(titles[i]);
        return view;
    }
}
