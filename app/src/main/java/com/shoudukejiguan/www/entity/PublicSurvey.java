package com.shoudukejiguan.www.entity;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class PublicSurvey extends BaseLvEntity<PublicSurvey.Data> {

    public class Data extends com.shoudukejiguan.www.entity.Data {
        public String content;
        public String itemid;
        public String title;
    }
}
