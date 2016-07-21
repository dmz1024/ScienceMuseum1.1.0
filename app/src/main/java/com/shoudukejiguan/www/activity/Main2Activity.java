package com.shoudukejiguan.www.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.view.PinchImageView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        PinchImageView iv= (PinchImageView) findViewById(R.id.iv_img);
        Glide.with(this).load("http://keji.lovect.cn/file/upload/201606/29/194714471.jpg").into(iv);
    }
}
