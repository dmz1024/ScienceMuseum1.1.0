package com.shoudukejiguan.www.entity;

import java.util.List;

/**
 * Created by dengmingzhi on 16/8/2.
 */
public class Index extends Message {
    public Data data;

    public class Data {
        public List<Ad> ad;
        public List<GoogGao> googgao;
        public List<JiaoYu> jiaoyu;
        public List<News> news;
        public List<YingYuan> yingyuan;
        public List<ZhanLan> zhanlan;

    }

    public class Ad {
        public String image_src;
        public String image_url;
        public String introduce;
        public String title;
    }

    public class GoogGao {
        public String addtime;
        public String itemid;
        public String title;
    }

    public class JiaoYu {
        public String addtime;
        public String itemid;
        public String kcpj;
        public String thumb;
        public String title;
        public String ybm;
    }


    public class News {
        public String addtime;
        public String itemid;
        public String introduce;
        public String title;
    }

    public class YingYuan {
        public String addtime;
        public String itemid;
        public String introduce;
        public String title;
        public String thumb;
        public String daoyan;
        public String star;

    }

    public class ZhanLan {
        public String addtime;
        public String itemid;
        public String thumb;
        public String title;

    }


}
