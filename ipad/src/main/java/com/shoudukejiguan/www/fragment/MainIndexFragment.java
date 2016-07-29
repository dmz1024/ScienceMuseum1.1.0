package com.shoudukejiguan.www.fragment;

import android.support.v7.widget.GridLayoutManager;
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
import com.shoudukejiguan.www.adapter.IndexFilmAdapter;
import com.shoudukejiguan.www.adapter.IndexNewsAdapter;
import com.shoudukejiguan.www.adapter.NewsAdapter;
import com.shoudukejiguan.www.entity.IndexNews;
import com.shoudukejiguan.www.entity.News;
import com.shoudukejiguan.www.entity.VideoSigna;
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
    private RecyclerView rv_film;
    private RelativeLayout rl_news;
    private RelativeLayout rl_exhibition;
    private RelativeLayout rl_film;
    private RelativeLayout rl_education;
    private TipView tip_notice;
    private RelativeLayout rl_menu_news, rl_menu_order, rl_menu_visit, rl_menu_scan;
    private boolean isFirstSao1Sao;
    private RecyclerView rv_news;

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

        exhibitionView();

        educationView(urlList);

        List<VideoSigna.Data> fileList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            VideoSigna.Data data = new VideoSigna.Data();
            fileList.add(data);
        }
        fileView(fileList);

        List<News.Data> newsList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            News.Data data = new News.Data();
            data.addtime = "2016-07-29";
            data.title = "测试用标题";
            data.introduce = "测试用内容测试用内容测试用内容测试用内容测试用内容测试用内容测试用内容测试用内容测试用内容";
            data.linkurl = "https://www.baidu.com";
            newsList.add(data);
        }
        newsView(newsList);
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
        rv_news = (RecyclerView) view.findViewById(R.id.rv_news);
        rv_film = (RecyclerView) view.findViewById(R.id.rv_film);
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


    /**
     * 常设展览专区
     */
    private void exhibitionView() {
        List<String> listUrl = new ArrayList<>();
        List<String> listTitle = new ArrayList<>();
        listUrl.add("http://cdn.duitang.com/uploads/item/201412/04/20141204163409_Tdusf.thumb.700_0.jpeg");
        listUrl.add("http://img1.v.tmcdn.net/img/h000/h08/img20120822145108301270.jpg");
        listUrl.add("http://img5.duitang.com/uploads/item/201411/29/20141129233121_GQPWn.thumb.700_0.jpeg");
        listUrl.add("http://img5.duitang.com/uploads/item/201411/29/20141129233121_GQPWn.thumb.700_0.jpeg");

        listTitle.add("生命乐章展厅");
        listTitle.add("生活追梦展厅");
        listTitle.add("生存对话主题展厅");
        listTitle.add("临时展览展厅");
        int width = (Util.getWidth() - 180 - dp2Px(70)) / 4;
        LinearLayout.LayoutParams params;
        for (int i = 0; i < listUrl.size(); i++) {
            View view = View.inflate(getActivity(), R.layout.item_exhibition, null);
            params = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i != 0) {
                params.leftMargin = 30;
            }

            view.setLayoutParams(params);
            ImageView iv_img = (ImageView) view.findViewById(R.id.iv_img);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            Glide.with(getActivity()).load(listUrl.get(i)).into(iv_img);
            tv_title.setText(listTitle.get(i));
            ll_exhibition.addView(view);
        }


    }


    /**
     * 教育专区
     *
     * @param urlList
     */
    private void educationView(List<String> urlList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_education.setLayoutManager(layoutManager);
        rv_education.setAdapter(new IndexEducationAdapter(urlList));
    }

    /**
     * 新闻资讯
     *
     * @param datas
     */
    private void newsView(List<News.Data> datas) {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rv_news.setLayoutManager(layoutManager);
        rv_news.setAdapter(new NewsAdapter(datas, getContext()));
    }


    /**
     * 特效影院
     */
    private void fileView(List<VideoSigna.Data> datas) {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rv_film.setLayoutManager(layoutManager);
        rv_film.setAdapter(new IndexFilmAdapter(datas));
    }

}
