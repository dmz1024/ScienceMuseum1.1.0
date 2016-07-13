package com.shoudukejiguan.www.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.google.gson.Gson;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.api.APiHttp;
import com.shoudukejiguan.www.entity.BaseLvEntity;
import com.shoudukejiguan.www.entity.Data;
import com.shoudukejiguan.www.entity.News;
import com.shoudukejiguan.www.view.MyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dengmingzhi on 16/6/14.
 */
public abstract class ListDataBaseFragment<T extends BaseLvEntity, D extends Data, A extends BaseRecyclerAdapter> extends BaseFragment implements XRefreshView.XRefreshViewListener {
    private XRefreshView xRefreshView;
    protected RecyclerView rv_base;
    private RelativeLayout rl_load;
    private RelativeLayout rl_root;
    private Button bt_agin;
    private TextView tv_nodata;
    public List<D> totalList;
    private int currentType;
    private int page;
    private int oldPage;
    public A mAdapter;
    protected Gson mGson;
    protected Map<String, String> map = new HashMap<>();
    protected String size = "10";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            size = bundle.getString("size");
        }
    }

    /**
     * 初始化视图
     *
     * @return
     */

    private View initView() {
        mGson = new Gson();
        totalList = new ArrayList<>();
        View view = View.inflate(getActivity(), R.layout.fg_base_lv, null);
        xRefreshView = (com.andview.refreshview.XRefreshView) view.findViewById(R.id.xrefreshview);
        rv_base = (RecyclerView) view.findViewById(R.id.rv_base);
        rl_load = (RelativeLayout) view.findViewById(R.id.rl_load);
        rl_root = (RelativeLayout) view.findViewById(R.id.rl_root);
        bt_agin = (Button) view.findViewById(R.id.bt_agin);
        bt_agin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showView(4);
                bt_agin.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = oldPage;
                        initData(currentType);
                    }
                }, 300);
            }
        });

        tv_nodata = (TextView) view.findViewById(R.id.tv_nodata);
        setxRefresh();
        if (isFirstInitData()) {
            init();
        }

        return view;
    }


    protected boolean isFirstInitData() {
        return true;
    }

    public void init() {
        if (!isInitData()) {
            initData(1);
        } else {
            bt_agin.postDelayed(new Runnable() {
                @Override
                public void run() {
                    initData(1);
                }
            }, 300);
        }
    }

    /**
     * 下拉刷新的回调
     */
    @Override
    public void onRefresh() {
        bt_agin.postDelayed(new Runnable() {
            @Override
            public void run() {
                oldPage = page;
                page = 1;
                currentType = 2;
                initData(2);
            }
        }, 500);

    }

    /**
     * 获取headerview显示的高度与headerview高度的比例
     *
     * @param offset  移动距离和headerview高度的比例，范围是0~1，0：headerview完全没显示
     *                1：headerview完全显示
     * @param offsetY headerview移动的距离
     */
    @Override
    public void onHeaderMove(double offset, int offsetY) {

    }

    /**
     * 上拉加载更多的回调
     */
    @Override
    public void onLoadMore(boolean isSlience) {
        if (noLoad()) {
            currentType = 1;
            oldPage = page;
            page += 1;
            initData(1);
        } else {
            xRefreshView.stopLoadMore();
        }

    }

    /**
     * 不进行分页
     *
     * @return
     */
    protected boolean noLoad() {
        return true;
    }

    /**
     * 用户手指释放的监听回调
     *
     * @param direction >0: 下拉释放，<0:上拉释放
     */

    @Override
    public void onRelease(float direction) {

    }

    /**
     * 设置xRefreshView相关参数
     */
    private void setxRefresh() {
        xRefreshView.setXRefreshViewListener(this);
        xRefreshView.setPinnedContent(true);//刷新时，不让里面的列表上下滑动
        xRefreshView.setSlienceLoadMore();// 设置静默加载模式
        rv_base.setLayoutManager(getLayoutManager());
        // 静默加载模式不能设置footerview
        rv_base.setAdapter(mAdapter = getAdapter(getContext(), totalList));
//        xRefreshView.setAutoLoadMore(true);
        //设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        //设置静默加载时提前加载的item个数
        xRefreshView.setPreLoadCount(1);
        xRefreshView.setPullRefreshEnable(getRefresh());
        xRefreshView.setPullLoadEnable(getLoad());
//        xRefreshView.setCustomHeaderView(new XrefreshHeaderView(getContext()));
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    // 设置是否可以下拉刷新
    protected boolean getRefresh() {
        return true;
    }

    // 设置是否可以上拉加载
    protected boolean getLoad() {
        return true;
    }


    /**
     * 返回适配器对象
     *
     * @param context
     * @param totalList
     * @return
     */
    protected abstract A getAdapter(Context context, List<D> totalList);

    /**
     * 按钮点击开始刷新，
     */
    public void startRefresh() {
        showView(4);
//        xRefreshView.startRefresh();
        onRefresh();
    }

    public void initData(final int type) {
        map.put("page", page + "");
        map.put("size", size);
        if (type == 2) {
            totalList.clear();
        }

        for (int i = 0; i < Integer.parseInt(size); i++) {
            totalList.add((D) new Data());
        }

        mAdapter.notifyDataSetChanged();
        if (totalList.size() > 0) {
            showView(1);
        } else if (totalList.size() == 0) {
            showView(3);
        }
        if (type == 1) {
            xRefreshView.stopLoadMore();
        } else {
            xRefreshView.stopRefresh();
        }

//        APiHttp api = new APiHttp(getUrl(), getMap(map), getContext()) {
//            @Override
//            protected void success(String json) {
//                try {
//                    JSONObject object = new JSONObject(json);
//                    int result = object.getInt("result");
//                    if (result == 0) {
//                        T t = mGson.fromJson(json, getTClass());
//                        if (t.data.size() == 0) {
//                            page = oldPage;
//                        }
//                        if (type == 2) {
//                            totalList.clear();
//                        }
//                        totalList.addAll(t.data);
//                        if (totalList.size() > 0) {
//                            showView(1);
//                        } else if (totalList.size() == 0) {
//                            showView(3);
//                        }
//
//                        mAdapter.notifyDataSetChanged();
//                    } else {
//                        page = oldPage;
//                    }
//
//                    if (type == 1) {
//                        xRefreshView.stopLoadMore();
//                    } else {
//                        xRefreshView.stopRefresh();
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    page = oldPage;
//                    if (type == 1) {
//                        xRefreshView.stopLoadMore();
//                    } else {
//                        xRefreshView.stopRefresh();
//                    }
//                }
//            }
//
//            @Override
//            protected void noNetWork() {
//                if (totalList.size() > 0) {
//                    MyToast.showToast("网络连接失败!");
//                } else {
//                    showView(2);
//                }
//                page = oldPage;
//                currentType = type;
//                if (type == 1) {
//                    xRefreshView.stopLoadMore();
//                } else {
//                    xRefreshView.stopRefresh();
//                }
//            }
//
//            @Override
//            protected void error() {
//                if (totalList.size() > 0) {
//                    MyToast.showToast("服务器异常!");
//                } else {
//                    showView(2);
//                }
//                page = oldPage;
//                if (type == 1) {
//                    xRefreshView.stopLoadMore();
//                } else {
//                    xRefreshView.stopRefresh();
//                }
//            }
//        };
//
//        if (getMethod()) {
//            api.post();
//        } else {
//            api.get();
//        }

    }

    protected boolean getMethod() {
        return true;
    }

    protected abstract Class<T> getTClass();


    /**
     * 返回http请求地址
     *
     * @return
     */
    protected abstract String getUrl();

    protected abstract Map<String, String> getMap(Map<String, String> map);


    /**
     * 是否延时加载数据
     *
     * @return
     */
    protected boolean isInitData() {
        return true;
    }


    /**
     * rl_load中view的显示状态
     *
     * @param type
     */
    protected void showView(int type) {
        xRefreshView.setVisibility(View.GONE);
        bt_agin.setVisibility(View.GONE);
        tv_nodata.setVisibility(View.GONE);
        rl_load.setVisibility(View.GONE);
        rl_root.setVisibility(View.VISIBLE);
        if (type == 1) {
            rl_root.setVisibility(View.GONE);
            xRefreshView.setVisibility(View.VISIBLE);
        } else if (type == 2) {
            bt_agin.setVisibility(View.VISIBLE);
        } else if (type == 3) {
            tv_nodata.setVisibility(View.VISIBLE);
        } else if (type == 4) {
            rl_load.setVisibility(View.VISIBLE);
        }
    }


}
