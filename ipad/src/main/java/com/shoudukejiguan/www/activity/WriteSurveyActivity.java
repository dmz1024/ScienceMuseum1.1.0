package com.shoudukejiguan.www.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.fragment.WriteSurveyFragment;

public class WriteSurveyActivity extends BaseActivity {
    private TextView tv_title;
    private String title;
    private String id;

    @Override
    protected int getRid() {
        return R.layout.activity_write_survey;
    }

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText(title);
    }

    @Override
    protected void initData() {
        title = getIntent().getStringExtra("title");
        id = getIntent().getStringExtra("id");
        tv_title.setText("您好！欢迎参加\"" + title + "\"，希望您可以花几分钟时间参加我们的满意度调查，谢谢您！");
        getSupportFragmentManager().beginTransaction().add(R.id.fg_content, new WriteSurveyFragment()).commit();
    }

    @Override
    protected void initView() {
        tv_title = getView(R.id.tv_title);
        Button bt_submit=getView(R.id.bt_submit);
        bt_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
