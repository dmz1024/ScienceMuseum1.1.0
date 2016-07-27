package com.shoudukejiguan.www.entity;

/**
 * Created by dengmingzhi on 16/7/26.
 */
public class UserLogin extends Message {
    public Data data;
    public class Data {
        public String uid;
        public String token;
        public String type;
    }
}
