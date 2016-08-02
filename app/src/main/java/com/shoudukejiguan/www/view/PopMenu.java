package com.shoudukejiguan.www.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shoudukejiguan.www.activity.CinemaActivity;
import com.shoudukejiguan.www.activity.EducationActivity;
import com.shoudukejiguan.www.activity.MoreNewsActivity;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.activity.ChildrenParkActivity;
import com.shoudukejiguan.www.activity.ExhibitionActivity;
import com.shoudukejiguan.www.activity.InteractionFeedbackActivity;
import com.shoudukejiguan.www.activity.ServiceGuideActivity;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/20.
 */
public abstract class PopMenu {
    Context ctx;
    int current;
    PopupWindow window;
    static String json = new StringBuilder().append("{\"data\":").append("[{\"name\": \"新闻资讯\",")
            .append("\"ID\": \"1000\",").append(" \"secMenu\":[]").append(" },")
            .append(" {\"name\": \"常设展览\",").append(" \"ID\": \"2000\",")
            .append(" \"secMenu\":[").append("{\"name\": \"三生主展\",")
            .append("\"ID\": \"2001\"},").append("{\"name\": \"科学广场\",")
            .append("\"ID\": \"2002\"}]").append(" },")
            .append(" {\"name\": \"临时展览\",").append("\"ID\":\"3000\",")
            .append(" \"secMenu\":[]").append(" },").append("{\"name\": \"教育专区\",")
            .append(" \"ID\": \"4000\",").append("\"secMenu\":[").append("{\"name\": \"基本情况\",").append("\"ID\": \"4001\"},")
            .append("{\"name\": \"报名预约\",").append("\"ID\": \"4002\"},").append("{\"name\": \"风采展示\",").append("\"ID\": \"4003\"},")
            .append("{\"name\": \"意见反馈\",").append("\"ID\": \"4004\"}]").append("},")
            .append(" {\"name\": \"儿童乐园\",").append("\"ID\":\"5000\",").append("\"secMenu\":[")
            .append("{\"name\": \"奇趣大自然\",").append("\"ID\": \"5001\"},").append("{\"name\": \"健康小主人\",")
            .append("\"ID\": \"5002\"},").append("{\"name\": \"生活的奥妙\",").append("\"ID\": \"5003\"},")
            .append("{\"name\": \"放飞希望\",").append("\"ID\": \"5004\"}]").append("},").append(" {\"name\": \"特效影院\",")
            .append(" \"ID\": \"6000\",").append("\"secMenu\":[").append("{\"name\": \"影院介绍\",").append("\"ID\": \"6001\"},")
            .append("{\"name\": \"影讯介绍\",").append("\"ID\": \"6002\"},").append("{\"name\": \"影片预告\",")
            .append("\"ID\": \"6003\"}]").append("},").append("{\"name\":\"票务预订\",").append("\"ID\": \"7000\",")
            .append(" \"secMenu\":[]").append("},")
            .append("{\"name\": \"参观指引\",").append(" \"ID\": \"8000\",").append("\"secMenu\":[").append("{\"name\": \"地图导航\",").append("\"ID\": \"8001\"},").append("{\"name\": \"馆内导览\",").append("\"ID\": \"8002\"}]},")
            .append(" {\"name\": \"服务指南\",").append("\"ID\":\"9000\",")
            .append(" \"secMenu\":[").append("{\"name\": \"参观须知\",")
            .append("\"ID\": \"9001\"},").append("{\"name\": \"团体预定\",")
            .append("\"ID\": \"9002\"},").append("{\"name\": \"个人预定\",")
            .append("\"ID\": \"9003\"},").append("{\"name\": \"交通指南\",")
            .append("\"ID\": \"9004\"},").append("{\"name\": \"友情推荐\",")
            .append("\"ID\": \"9005\"},").append("{\"name\": \"游客服务\",")
            .append("\"ID\": \"9006\"}]").append(" },")
            .append(" {\"name\":\"互动反馈\",").append("\"ID\":\"1100\",")
            .append("\"secMenu\":[").append("{\"name\": \"公众调查\",")
            .append("\"ID\": \"1101\"},").append("{\"name\": \"在线调查\",")
            .append("\"ID\": \"1102\"}]").append(" }").append(" ]").append("}").toString();
    //    String json= new StringBuilder().append("{\"data\":\n").append("[\n").append(" \n").append(" {\"name\": \"新闻资讯\",\n").append("\"ID\": \"1000\",\n").append(" \"secMenu\":[]\n").append(" },\n").append(" \n").append(" {\"name\": \"常设展览\",\n").append(" \"ID\": \"2000\",\n").append(" \"secMenu\":[\n").append("            {\"name\": \"三生主展\",\n").append("            \"ID\": \"2001\"},\n").append("            {\"name\": \"科学广场\",\n").append("            \"ID\": \"2002\"}]\n").append(" },\n").append(" \n").append(" {\"name\": \"临时展览\",\n").append(" \"ID\": \"3000\",\n").append(" \"secMenu\":[]\n").append(" },\n").append(" \n").append(" {\"name\": \"教育专区\",\n").append(" \"ID\": \"4000\",\n").append(" \"secMenu\":[\n").append("            {\"name\": \"基本情况\",\n").append("            \"ID\": \"4001\"},\n").append("            {\"name\": \"报名预约\",\n").append("            \"ID\": \"4002\"},\n").append("            {\"name\": \"风采展示\",\n").append("            \"ID\": \"4003\"},\n").append("            {\"name\": \"意见反馈\",\n").append("            \"ID\": \"4004\"}]\n").append(" },\n").append(" \n").append(" {\"name\": \"儿童乐园\",\n").append(" \"ID\": \"5000\",\n").append(" \"secMenu\":[\n").append("            {\"name\": \"奇趣大自然\",\n").append("            \"ID\": \"5001\"},\n").append("            {\"name\": \"健康小主人\",\n").append("            \"ID\": \"5002\"},\n").append("            {\"name\": \"生活的奥妙\",\n").append("            \"ID\": \"5003\"},\n").append("            {\"name\": \"放飞希望\",\n").append("            \"ID\": \"5004\"}]\n").append(" },\n").append(" \n").append(" {\"name\": \"特效影院\",\n").append(" \"ID\": \"6000\",\n").append(" \"secMenu\":[\n").append("            {\"name\": \"影院介绍\",\n").append("            \"ID\": \"6001\"},\n").append("            {\"name\": \"影讯介绍\",\n").append("            \"ID\": \"6002\"},\n").append("            {\"name\": \"影片预告\",\n").append("            \"ID\": \"6003\"}]\n").append(" },\n").append(" \n").append(" {\"name\": \"票务预订\",\n").append(" \"ID\": \"7000\",\n").append(" \"secMenu\":[]\n").append(" },\n").append(" \n").append(" {\"name\": \"参观指引\",\n").append(" \"ID\": \"8000\",\n").append(" \"secMenu\":[\n").append("            {\"name\": \"地图导航\",\n").append("            \"ID\": \"8001\"},\n").append("            {\"name\": \"馆内导览\",\n").append("            \"ID\": \"8002\"},]\n").append(" },\n").append(" \n").append(" {\"name\": \"服务指南\",\n").append(" \"ID\": \"9000\",\n").append(" \"secMenu\":[\n").append("            {\"name\": \"参观须知\",\n").append("            \"ID\": \"9001\"},\n").append("            {\"name\": \"团体预定\",\n").append("            \"ID\": \"9002\"},\n").append("            {\"name\": \"个人预定\",\n").append("            \"ID\": \"9003\"},\n").append("            {\"name\": \"交通指南\",\n").append("            \"ID\": \"9004\"},\n").append("            {\"name\": \"友情推荐\",\n").append("            \"ID\": \"9005\"},\n").append("            {\"name\": \"游客服务\",\n").append("            \"ID\": \"9006\"}]\n").append(" },\n").append(" \n").append(" {\"name\": \"互动反馈\",\n").append(" \"ID\": \"1100\",\n").append(" \"secMenu\":[\n").append("            {\"name\": \"公众调查\",\n").append("            \"ID\": \"1101\"},\n").append("            {\"name\": \"在线调查\",\n").append("            \"ID\": \"1102\"}]\n").append(" }\n").append(" \n").append(" ]\n").append("}").toString();
    com.shoudukejiguan.www.entity.PopMenu menu;
    MaxListView lv_main;
    MaxListView lv_content;

    public PopMenu() {
        Gson gson = new Gson();
        menu = gson.fromJson(json, com.shoudukejiguan.www.entity.PopMenu.class);
    }

    public void initPop(final Context ctx, View v) {
        this.ctx = ctx;
        View view = View.inflate(this.ctx, R.layout.pop_menu, null);
        lv_main = (MaxListView) view.findViewById(R.id.lv_main);
        lv_main.setAdapter(new MainAdapter());
        lv_content = (MaxListView) view.findViewById(R.id.lv_content);
        lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        skip(MoreNewsActivity.class);
                        window.dismiss();
                        break;
                    case 6:
                        window.dismiss();
                        break;
                    case 1:
                    case 3:
                    case 4:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                        current = i;
                        lv_content.setAdapter(new ContentAdapter(menu.data.get(i).secMenu));
                        break;
                }

            }
        });

        lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (current == 7) {
                    window.dismiss();
                    return;
                }

                Intent intent = null;
                switch (current) {
                    case 1:
                        intent = new Intent(ctx, ExhibitionActivity.class);//常设展览
                        break;
                    case 3:
                        intent = new Intent(ctx, EducationActivity.class);//教育专区
                        break;
                    case 4:
                        intent = new Intent(ctx, ChildrenParkActivity.class);//儿童乐园
                        break;
                    case 5:
                        intent = new Intent(ctx, CinemaActivity.class);//特效影院
                        break;
                    case 8:
                        intent = new Intent(ctx, ServiceGuideActivity.class);//服务指南
                        break;
                    case 9:
                        intent = new Intent(ctx, InteractionFeedbackActivity.class);//互动反馈
                        break;
                }

                intent.putExtra("index", i);
                ctx.startActivity(intent);
                window.dismiss();
            }
        });

        window = new PopupWindow(view, FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, true);
        window.setOnDismissListener(getDis());
        window.setBackgroundDrawable(new ColorDrawable());
        window.showAsDropDown(v);
    }


    private void skip(Class clx) {
        ctx.startActivity(new Intent(ctx, clx));
    }

    private void skip(Intent intent) {
        ctx.startActivity(intent);
    }

    public abstract PopupWindow.OnDismissListener getDis();

    class MainAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return menu.data.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = View.inflate(ctx, R.layout.item_simple_text, null);
            TextView tv = (TextView) view;
            tv.setText(menu.data.get(i).name);
            return tv;
        }
    }


    class ContentAdapter extends BaseAdapter {
        List<com.shoudukejiguan.www.entity.PopMenu.SecMenu> secMenus;

        public ContentAdapter(List<com.shoudukejiguan.www.entity.PopMenu.SecMenu> secMenus) {
            this.secMenus = secMenus;
        }

        @Override
        public int getCount() {
            return secMenus.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = View.inflate(ctx, R.layout.item_simple_text, null);
            TextView tv = (TextView) view;
            tv.setText(secMenus.get(i).name);
            return tv;
        }
    }

}
