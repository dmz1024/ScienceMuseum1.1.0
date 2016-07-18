package com.shoudukejiguan.www.fragment;

import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.activity.SetActivity;
import com.shoudukejiguan.www.adapter.GridViewCenterAdapter;
import com.shoudukejiguan.www.view.MaxGridView;
import com.shoudukejiguan.www.view.TextImage;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 */
public class MainPeosonalFragment extends MainBaseFragment {
    private MaxGridView gv_menu;
    private MaxGridView gv_info;
    private View view_set;
    private String[] titles_menu = {"购电影票", "票务预订", "新闻资讯", "参观指引"};
    private int[] images_menu = {R.mipmap.icon_gdyp, R.mipmap.icon_pwyd, R.mipmap.icon_xwzx, R.mipmap.icon_cgzy};
    private LinearLayout ll_progress;
    private TextImage tv_level;
    private String[] titles_info;
    private boolean isType = false;
    private int[] images_info;
    private GridViewCenterAdapter mAdapter;
    private CircleImageView iv_img;
    @Override
    protected boolean isInit() {
        return false;
    }

    @Override
    protected void initData() {
        set();
        gv_menu.setAdapter(new GridViewCenterAdapter(getContext(), titles_menu, images_menu, R.layout.item_gv_ti));

        Glide.with(getActivity()).load("http://img5.duitang.com/uploads/item/201411/29/20141129233121_GQPWn.thumb.700_0.jpeg").into(iv_img);
    }

    @Override
    protected void initView(View view) {
        gv_menu = (MaxGridView) view.findViewById(R.id.gv_menu);
        gv_info = (MaxGridView) view.findViewById(R.id.gv_info);
        view_set = view.findViewById(R.id.view_set);
        view_set.setOnClickListener(this);
        ll_progress= (LinearLayout) view.findViewById(R.id.ll_progress);
        tv_level= (TextImage) view.findViewById(R.id.tv_level);
        iv_img= (CircleImageView) view.findViewById(R.id.iv_img);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_set:
//                set();
                skip(SetActivity.class);
                break;
        }
    }

    private void set() {
        ll_progress.setVisibility(View.GONE);
        tv_level.setVisibility(View.GONE);
        if (!isType) {
            titles_info = new String[]{"我的课程", "我的订单", "我的收藏", "用户信息", "意见反馈", "修改密码", "  ", "  "};
            images_info = new int[]{R.mipmap.icon_wdkc, R.mipmap.icon_wddd, R.mipmap.icon_wdsc, R.mipmap.icon_yhxx, R.mipmap.icon_yjfk, R.mipmap.icon_xgmm, R.mipmap.icon_null, R.mipmap.icon_null};
            ll_progress.setVisibility(View.VISIBLE);
        } else {
            titles_info = new String[]{"我的订单", "我的收藏", "用户信息", "修改密码"};
            images_info = new int[]{R.mipmap.icon_wddd, R.mipmap.icon_wdsc, R.mipmap.icon_yhxx, R.mipmap.icon_xgmm};
            tv_level.setVisibility(View.VISIBLE);
        }
        isType = !isType;
        gv_info.setAdapter(mAdapter=new GridViewCenterAdapter(getContext(), titles_info, images_info, R.layout.item_gv_ti));
    }

    @Override
    protected int getRid() {
        return R.layout.fragment_main_peosonal;
    }

    @Override
    protected boolean isTitleBarShow() {
        return false;
    }
}
