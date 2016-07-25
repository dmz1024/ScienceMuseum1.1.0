package com.shoudukejiguan.www.constant;

/**
 * Created by dengmingzhi on 16/7/25.
 */
public interface ApiConstant {
    String URL = "http://keji.lovect.cn/app/";
    /**
     * 新闻资讯、大事记、通知公告
     */
    String NEWS = URL + "news.php";

    /**
     * 个人注册
     */
    String REG = URL + "reg.php";

    /**
     * 获取注册验证码
     */
    String REG_CODE = URL + "reg_code.php";
}
