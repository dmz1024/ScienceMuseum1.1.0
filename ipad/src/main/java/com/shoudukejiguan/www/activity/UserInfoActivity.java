package com.shoudukejiguan.www.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.view.TitleRelativeLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfoActivity extends BaseActivity {
    private CircleImageView iv_img;
    private TitleRelativeLayout tr_tel;
    private TitleRelativeLayout tr_name;
    private TitleRelativeLayout tr_update_password;
    private TitleRelativeLayout tr_team_link_tel;
    private TitleRelativeLayout tr_team_link_name;
    private TitleRelativeLayout tr_team_count;
    private TitleRelativeLayout tr_team_name;
    private LinearLayout ll_team;

    private boolean isTeam = false;

    @Override
    protected int getRid() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("用户信息");
    }

    @Override
    protected void initData() {
        Glide.with(this).load("http://img5.duitang.com/uploads/item/201411/29/20141129233121_GQPWn.thumb.700_0.jpeg").into(iv_img);
    }

    @Override
    protected void initView() {
        iv_img = getView(R.id.iv_img);
        tr_tel = getView(R.id.tr_tel);
        tr_name = getView(R.id.tr_name);
        tr_update_password = getView(R.id.tr_update_password);
        tr_team_link_tel = getView(R.id.tr_team_link_tel);
        tr_team_link_name = getView(R.id.tr_team_link_name);
        tr_team_count = getView(R.id.tr_team_count);
        tr_team_name = getView(R.id.tr_team_name);
        ll_team = getView(R.id.ll_team);

        tr_update_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tr_update_password:
                isTeam();
                break;
        }
    }

    private void isTeam() {
        tr_tel.setVisibility(View.GONE);
        ll_team.setVisibility(View.GONE);
        tr_name.setViewVisi(false);
        if (isTeam) {
            ll_team.setVisibility(View.VISIBLE);
        } else {
            tr_tel.setVisibility(View.VISIBLE);
            tr_name.setViewVisi(true);
        }
        isTeam = !isTeam;
    }
}
