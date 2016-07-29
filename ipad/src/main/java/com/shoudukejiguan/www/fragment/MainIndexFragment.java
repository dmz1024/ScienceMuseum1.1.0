package com.shoudukejiguan.www.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.google.zxing.integration.android.IntentIntegrator;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.activity.CaptureActivity;
import com.shoudukejiguan.www.activity.CinemaActivity;
import com.shoudukejiguan.www.activity.EducationActivity;
import com.shoudukejiguan.www.activity.ExhibitionActivity;
import com.shoudukejiguan.www.activity.MainActivity;
import com.shoudukejiguan.www.activity.MoreNewsActivity;
import com.shoudukejiguan.www.activity.NoticeBulletinActivity;
import com.shoudukejiguan.www.adapter.GridViewCenterAdapter;
import com.shoudukejiguan.www.adapter.IndexEducationAdapter;
import com.shoudukejiguan.www.adapter.IndexNewsAdapter;
import com.shoudukejiguan.www.entity.IndexNews;
import com.shoudukejiguan.www.util.Util;
import com.shoudukejiguan.www.view.MaxGridView;
import com.shoudukejiguan.www.view.MaxListView;
import com.shoudukejiguan.www.view.MyToast;
import com.shoudukejiguan.www.view.PopMenu;
import com.shoudukejiguan.www.view.RotationRelativeLayout;
import com.shoudukejiguan.www.view.RotationViewPager;
import com.shoudukejiguan.www.view.TipView;

import java.util.ArrayList;
import java.util.List;


/**
 */
public class MainIndexFragment extends MainBaseFragment {
    private RotationViewPager rvp_rotation;
    private LinearLayout ll_exhibition;
    private RecyclerView rv_education;
    private RelativeLayout rl_news;
    private RelativeLayout rl_exhibition;
    private RelativeLayout rl_film;
    private RelativeLayout rl_education;
    private TipView tip_notice;
    private RelativeLayout rl_menu_news, rl_menu_order, rl_menu_visit, rl_menu_scan;
    private boolean isFirstSao1Sao;

    @Override
    protected boolean isInit() {
        return true;
    }

    @Override
    protected void initData() {
        List<String> urlList = new ArrayList<>();
        urlList.add("http://cdn.duitang.com/uploads/item/201412/04/20141204163409_Tdusf.thumb.700_0.jpeg");
        urlList.add("http://img1.v.tmcdn.net/img/h000/h08/img20120822145108301270.jpg");
        urlList.add("http://img5.duitang.com/uploads/item/201411/29/20141129233121_GQPWn.thumb.700_0.jpeg");
        urlList.add("http://img5.duitang.com/uploads/item/201411/29/20141129233121_GQPWn.thumb.700_0.jpeg");
        rvp_rotation.setUrls(urlList);
        tip_notice.setTipList(urlList);
        tip_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skip(NoticeBulletinActivity.class);
            }
        });

//        gv_center.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                switch (i) {
//                    case 3:
//                        sao1sao();
//                        break;
//                    case 0:
//                        skip(MoreNewsActivity.class);
//                    case 1:
//                    case 2:
//                        ((MainActivity) getActivity()).onTabSelect(i);
//                        break;
//
//                }
//            }
//        });

//        List<IndexNews> listNews = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            IndexNews indexNews = new IndexNews("标题：" + i, "2016-09-16");
//            listNews.add(indexNews);
//        }
//        lv_news.setAdapter(new IndexNewsAdapter(getContext(), listNews));

        for (int i = 0; i < urlList.size(); i++) {
            addImage(urlList.get(i));
        }

        educationView(urlList);
    }

    /**
     * 扫一扫
     */
    private void sao1sao() {
        if (!isFirstSao1Sao) {
            IntentIntegrator.forSupportFragment(this);
        } else {
            isFirstSao1Sao = true;
        }
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setCaptureActivity(CaptureActivity.class);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    @Override
    protected void initView(View view) {
        rvp_rotation = (RotationViewPager) view.findViewById(R.id.rvp_rotation);
        tip_notice = (TipView) view.findViewById(R.id.tip_notice);
        rl_exhibition = (RelativeLayout) view.findViewById(R.id.rl_exhibition);
        ll_exhibition = (LinearLayout) view.findViewById(R.id.ll_exhibition);
        rv_education = (RecyclerView) view.findViewById(R.id.rv_education);
        rl_news = (RelativeLayout) view.findViewById(R.id.rl_news);
        rl_exhibition = (RelativeLayout) view.findViewById(R.id.rl_exhibition);
        rl_film = (RelativeLayout) view.findViewById(R.id.rl_film);
        rl_education = (RelativeLayout) view.findViewById(R.id.rl_education);
        rl_news.setOnClickListener(this);
        rl_exhibition.setOnClickListener(this);
        rl_film.setOnClickListener(this);
        rl_education.setOnClickListener(this);
        rl_menu_news = (RelativeLayout) view.findViewById(R.id.rl_menu_news);
        rl_menu_order = (RelativeLayout) view.findViewById(R.id.rl_menu_order);
        rl_menu_visit = (RelativeLayout) view.findViewById(R.id.rl_menu_visit);
        rl_menu_scan = (RelativeLayout) view.findViewById(R.id.rl_menu_scan);
        rl_menu_news.setOnClickListener(this);
        rl_menu_order.setOnClickListener(this);
        rl_menu_visit.setOnClickListener(this);
        rl_menu_scan.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.rl_exhibition:
                moreExhibition();
                break;
            case R.id.rl_film:
                moreFilm();
                break;
            case R.id.rl_education:
                moreEducation();
                break;
            case R.id.rl_menu_scan:
                sao1sao();
                break;
            case R.id.rl_news:
            case R.id.rl_menu_news:
                skip(MoreNewsActivity.class);
                break;
            case R.id.rl_menu_order:
                ((MainActivity) getActivity()).onTabSelect(1);
                break;
            case R.id.rl_menu_visit:
                ((MainActivity) getActivity()).onTabSelect(2);
                break;
        }
    }


    /**
     * 更多教育
     */
    private void moreEducation() {
        skip(EducationActivity.class);
    }


    /**
     * 更多影院
     */
    private void moreFilm() {
        skip(CinemaActivity.class);
    }

    /**
     * 更多展览专区
     */
    private void moreExhibition() {
        skip(ExhibitionActivity.class);
    }


    @Override
    protected int getRid() {
        return R.layout.fragment_main_index;
    }


    private void addImage(String url) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((Util.getWeight() / 4) - 20, (Util.getWeight() / 4) - 20);
        params.leftMargin = 20;
        ImageView iv = new ImageView(getContext());
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setLayoutParams(params);
        Glide.with(getActivity()).load(url).into(iv);
        ll_exhibition.addView(iv);
    }


    /**
     * 横向滚动的视图
     *
     * @param urlList
     */
    private void educationView(List<String> urlList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_education.setLayoutManager(layoutManager);
        rv_education.setAdapter(new IndexEducationAdapter(urlList));
    }

}
