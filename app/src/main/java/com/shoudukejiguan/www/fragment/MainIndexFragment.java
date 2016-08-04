package com.shoudukejiguan.www.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.shoudukejiguan.www.activity.MoreNewsActivity;
import com.shoudukejiguan.www.api.APiHttp;
import com.shoudukejiguan.www.api.ApiRequest;
import com.shoudukejiguan.www.constant.ApiConstant;
import com.shoudukejiguan.www.entity.Index;
import com.shoudukejiguan.www.view.MaxGridView;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.activity.CaptureActivity;
import com.shoudukejiguan.www.activity.CinemaActivity;
import com.shoudukejiguan.www.activity.EducationActivity;
import com.shoudukejiguan.www.activity.ExhibitionActivity;
import com.shoudukejiguan.www.activity.MainActivity;
import com.shoudukejiguan.www.activity.NoticeBulletinActivity;
import com.shoudukejiguan.www.adapter.GridViewCenterAdapter;
import com.shoudukejiguan.www.adapter.IndexEducationAdapter;
import com.shoudukejiguan.www.adapter.IndexNewsAdapter;
import com.shoudukejiguan.www.entity.IndexNews;
import com.shoudukejiguan.www.util.Util;
import com.shoudukejiguan.www.view.MaxListView;
import com.shoudukejiguan.www.view.MyToast;
import com.shoudukejiguan.www.view.PopMenu;
import com.shoudukejiguan.www.view.RotationRelativeLayout;
import com.shoudukejiguan.www.view.TipView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 */
public class MainIndexFragment extends MainBaseFragment {
    private RotationRelativeLayout layout_rotation;
    private LinearLayout ll_exhibition;
    private RecyclerView rv_education;
    private RelativeLayout rl_news;
    private RelativeLayout rl_exhibition;
    private RelativeLayout rl_film;
    private RelativeLayout rl_education;
    private MaxListView lv_news;
    private MaxGridView gv_center;
    private TipView tip_notice;
    private String[] titles = {"新闻资讯", "票务预订", "参观指引", "扫一扫"};
    private int[] images = {R.mipmap.index_menu_news, R.mipmap.index_menu_booktic, R.mipmap.index_menu_map, R.mipmap.index_menu_sao};
    private boolean isFirstSao1Sao;

    @Override
    protected void initData() {
        titleBar.setLeftImage(R.mipmap.icon_more);
        new ApiRequest<Index>(getActivity(), ApiConstant.INDEX, Index.class) {
            @Override
            protected void noNetWork() {
                setStopRefresh();
            }


            @Override
            protected void success(Index index) {

                if (index.result == 0) {
//
                    Index.Data data = index.data;
                    List<Index.Ad> ads = data.ad;
                    List<String> adsList = new ArrayList<String>();
                    for (int i = 0; i < ads.size(); i++) {
                        adsList.add(ads.get(i).image_src);
                        adsList.add(ads.get(i).image_src);
                    }
                    layout_rotation.setUrls(adsList);

//                    List<Index.GoogGao> googGaos = data.googgao;
//                    List<String> gGList = new ArrayList<String>();
//                    for (int i = 0; i < googGaos.size(); i++) {
//                        gGList.add(googGaos.get(i).title);
//                    }
//                    tip_notice.setTipList(gGList);

//                    List<IndexNews> newsList = new ArrayList<IndexNews>();
//                    List<Index.News> newss = new ArrayList<Index.News>();
//                    for (int i = 0; i < newss.size(); i++) {
//                        Index.News news = newss.get(i);
//                        IndexNews indexNews = new IndexNews(news.title, news.addtime);
//                        newsList.add(indexNews);
//                    }
//                    lv_news.setAdapter(new IndexNewsAdapter(getContext(), newsList));

                    List<String[]> exhibitionList = new ArrayList<String[]>();
                    List<Index.ZhanLan> zhanLans = new ArrayList<Index.ZhanLan>();
                    for (int i = 0; i < zhanLans.size(); i++) {
                        exhibitionList.add(new String[]{zhanLans.get(i).title, zhanLans.get(i).thumb});
                    }
                    exhibitionView(exhibitionList);

                    educationView(data.jiaoyu);


                } else {
                    MyToast.showToast(index.msg);
                }
                setStopRefresh();
            }

            @Override
            protected void onErr() {
                setStopRefresh();
            }
        }.post();

        gv_center.setAdapter(new GridViewCenterAdapter(getContext(), titles, images));
        tip_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skip(NoticeBulletinActivity.class);
            }
        });
        gv_center.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 3:
                        sao1sao();
                        break;
                    case 0:
                        skip(MoreNewsActivity.class);
                    case 1:
                    case 2:
                        ((MainActivity) getActivity()).onTabSelect(i);
                        break;

                }
            }
        });

    }

    @Override
    protected boolean isFirstInitData() {
        return true;
    }

    @Override
    protected void titleBar() {
        super.titleBar();
        titleBar.setlLeftVisi(true);
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
        gv_center = (MaxGridView) view.findViewById(R.id.gv_center);
        layout_rotation = (RotationRelativeLayout) view.findViewById(R.id.layout_rotation);
        tip_notice = (TipView) view.findViewById(R.id.tip_notice);
        lv_news = (MaxListView) view.findViewById(R.id.lv_news);
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

    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_main_index;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_news:
                moreNews();
                break;
            case R.id.rl_exhibition:
                moreExhibition();
                break;
            case R.id.rl_film:
                moreFilm();
                break;
            case R.id.rl_education:
                moreEducation();
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

    /**
     * 更多新闻资讯
     */
    private void moreNews() {
        skip(MoreNewsActivity.class);
    }

    @Override
    protected String getTitleBarTitle() {
        return "首都科学技术馆";
    }


    /**
     * 常设展览专区
     */
    private void exhibitionView(List<String[]> list) {
        int width = (Util.getWidth() - 180 - dp2Px(70)) / 4;
        LinearLayout.LayoutParams params;
        for (int i = 0; i < list.size(); i++) {
            View view = View.inflate(getActivity(), R.layout.item_exhibition, null);
            params = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i != 0) {
                params.leftMargin = 30;
            }

            view.setLayoutParams(params);
            ImageView iv_img = (ImageView) view.findViewById(R.id.iv_img);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_title.setText(list.get(i)[0]);
            Glide.with(getActivity()).load(list.get(i)[1]).into(iv_img);
            ll_exhibition.addView(view);
        }


    }


    /**
     * 横向滚动的视图
     *
     * @param jylList
     */
    private void educationView(List<Index.JiaoYu> jylList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_education.setLayoutManager(layoutManager);
        rv_education.setAdapter(new IndexEducationAdapter(jylList));
    }

    @Override
    public void left() {
        backgroundAlpha(0.5f);
        new PopMenu() {
            @Override
            public PopupWindow.OnDismissListener getDis() {
                return MainIndexFragment.this;
            }
        }.initPop(getContext(), titleBar);
    }
}
