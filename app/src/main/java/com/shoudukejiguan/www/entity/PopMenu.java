package com.shoudukejiguan.www.entity;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/20.
 */
public class PopMenu {
    public List<Data> data;
    public class Data {
        public int ID;
        public String name;
        public List<SecMenu> secMenu;

    }

    public static class SecMenu {
        public int ID;
        public String name;
    }
}
