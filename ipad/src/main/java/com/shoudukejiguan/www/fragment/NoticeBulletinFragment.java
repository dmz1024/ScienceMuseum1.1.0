package com.shoudukejiguan.www.fragment;

import android.content.Context;

import com.shoudukejiguan.www.adapter.MyOrderAdapter;
import com.shoudukejiguan.www.adapter.NoticeBulletinAdapter;
import com.shoudukejiguan.www.constant.ApiConstant;
import com.shoudukejiguan.www.entity.MyOrder;
import com.shoudukejiguan.www.entity.NoticeBulletin;

import java.util.List;
import java.util.Map;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class NoticeBulletinFragment extends ListDataBaseFragment<NoticeBulletin, NoticeBulletin.Data, NoticeBulletinAdapter> {
    @Override
    protected NoticeBulletinAdapter getAdapter(Context context, List<NoticeBulletin.Data> totalList) {
        return new NoticeBulletinAdapter(totalList);
    }

    @Override
    protected Class<NoticeBulletin> getTClass() {
        return NoticeBulletin.class;
    }

    @Override
    protected String getUrl() {
        return ApiConstant.NEWS;
    }

    @Override
    protected Map<String, String> getMap(Map<String, String> map) {
        map.put("catid", "80");
        return map;
    }
}
