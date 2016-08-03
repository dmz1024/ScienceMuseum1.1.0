package com.shoudukejiguan.www.activity;

import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.fragment.MapFragment;

public class WebViewActivity extends FragmentBaseActivity {

    @Override
    protected void initTitleBar() {
        title_bar.setTvTitleText("正在加载...");
    }

    @Override
    protected void initData() {
        MapFragment fragment = MapFragment.getInstance(getIntent().getStringExtra("url"));
        fragment.setOnReturnTitleListener(new MapFragment.OnReturnTitleListener() {
            @Override
            public void title(String title) {
                title_bar.setTvTitleText(title);
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.fg_base, fragment).commit();
    }

    @Override
    protected void initView() {

    }

}
