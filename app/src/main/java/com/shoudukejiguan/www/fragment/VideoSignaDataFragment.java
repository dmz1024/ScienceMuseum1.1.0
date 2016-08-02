package com.shoudukejiguan.www.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shoudukejiguan.www.adapter.VideoSignaAdapter;
import com.shoudukejiguan.www.constant.ApiConstant;
import com.shoudukejiguan.www.entity.VideoSigna;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class VideoSignaDataFragment extends ListDataBaseFragment<VideoSigna, VideoSigna.Data, VideoSignaAdapter> {
    public String catid;

    public static VideoSignaDataFragment getInstance(String catid) {
        Bundle bundle = new Bundle();
        bundle.putString("catid", catid);
        VideoSignaDataFragment videoSignaDataFragment = new VideoSignaDataFragment();
        videoSignaDataFragment.setArguments(bundle);
        return videoSignaDataFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            catid = bundle.getString("catid");
        }
    }

    @Override
    protected VideoSignaAdapter getAdapter(Context context, List<VideoSigna.Data> totalList) {
        return new VideoSignaAdapter(totalList);
    }

    @Override
    protected Class<VideoSigna> getTClass() {
        return VideoSigna.class;
    }

    @Override
    protected String getUrl() {
        return ApiConstant.LIST;
    }

    @Override
    protected Map<String, String> getMap(Map<String, String> map) {
        map.put("mid", "16");
        map.put("catid", catid);
        return map;
    }
}
