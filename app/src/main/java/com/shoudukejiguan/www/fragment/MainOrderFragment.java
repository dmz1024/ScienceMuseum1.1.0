package com.shoudukejiguan.www.fragment;


import android.support.v4.app.Fragment;
import android.view.View;

import com.shoudukejiguan.www.view.TextImage;
import com.shoudukejiguan.www.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainOrderFragment extends MainBaseFragment {
    private TextImage tv_time;
    private int year;
    private int month;
    private int day;

    @Override
    protected boolean isInit() {
        return false;
    }

    @Override
    protected void initData() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);
        tv_time.setText("选择日期");
        getChildFragmentManager().beginTransaction().add(R.id.fg_order, new OrderFragment()).commit();
    }

    @Override
    protected void initView(View view) {
        tv_time = (TextImage) view.findViewById(R.id.tv_time);
        tv_time.setOnClickListener(this);

    }

    @Override
    protected void titleBar() {
        super.titleBar();
        titleBar.setlLeftVisi(false);
    }

    @Override
    protected int getRid() {
        return R.layout.fragment_main_order;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_time:
                date();
                break;
        }
    }

    @Override
    protected String getTitleBarTitle() {
        return "票务预订";
    }

    /**
     * 选择日期
     */
    private void date() {
        DatePickerFragment dateFragment = DatePickerFragment.getInstance(year + "-" + month + "-" + day);
        dateFragment.setDateListener(new DatePickerFragment.DateListener() {
            @Override
            public void date(int year, int month, int day) {
                dateFilter(year, month, day);
            }
        });
        dateFragment.show(getActivity().getFragmentManager(), "date");
    }


    private void dateFilter(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        tv_time.setText(year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day));
    }
}
