package com.shoudukejiguan.www.entity;

import java.util.List;

/**
 * Created by dengmingzhi on 16/7/12.
 */
public class MyOrder extends BaseLvEntity<MyOrder.Data> {

    public class Data extends com.shoudukejiguan.www.entity.Data {
        public List<OrderData> orders;
    }

    public static class OrderData{

    }
}
