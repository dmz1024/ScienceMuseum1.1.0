package com.shoudukejiguan.www.entity;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class NoticeBulletin extends BaseLvEntity<NoticeBulletin.Data> {

    public class Data extends com.shoudukejiguan.www.entity.Data {
        public String itemid;
        public String title;
        public String introduce;
        public String addtime;
        public String thumb;
    }
}
