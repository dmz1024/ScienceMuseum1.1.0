package com.shoudukejiguan.www.entity;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class Survey extends BaseLvEntity<Survey.Data> {

    public class Data extends com.shoudukejiguan.www.entity.Data {
        public String q;
        public List<Question> questions;
    }

    public static class Question {
        public String a;
    }
}
