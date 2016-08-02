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
    String CODE = URL + "yzm.php";

    /**
     * 登录
     */
    String LOGIN = URL + "login.php";

    /**
     * 全模块列表
     */

    String LIST = URL + "list.php";

    /**
     * 首页
     */

    String INDEX = URL + "index.php";

    /**
     * 调查问卷列表
     */
    String FORMLIST = URL + "formlist.php";

}
