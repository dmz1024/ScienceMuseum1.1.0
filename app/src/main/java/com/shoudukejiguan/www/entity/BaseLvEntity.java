package com.shoudukejiguan.www.entity;

import java.util.List;

/**
 * Created by dengmingzhi on 16/6/14.
 */
public class BaseLvEntity<T extends Data> {
    public int result;
    public List<T> data;
}
