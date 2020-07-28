package com.runjing.common;


/**
 * @Created by xiaoyu on 2017/1/6.
 * @Describe：接口 工具类
 * @Review by：
 * @Modify by：
 * @Version : $ v_1.0 on 2017/1/6.
 * @Remark:
 * @see <a href="https://github.com/mrxiaoyu100001">github</a>
 */
public class BaseUrl {

//    public final static String RjBaseUrl = "https://cxc.jd9sj.com/api/";//线上正式环境
//    public final static String RjBaseUrl = "https://pre-cxc.jd9sj.com/api/";//线上正式环境
    public final static String BaseUrl = "http://116.196.90.67:9002/api/";//测试环境


    /*获取banner图片*/
    public static String GetBanner = BaseUrl + "获取banner图片";
    /*登录接口*/
    public static String LoginIn = BaseUrl + "customer/appLogin";
    /*登出接口*/
    public static String LoginOut = BaseUrl + "user/logout";
    /*升级接口*/
    public static String AppUpdate = BaseUrl + "appmanager/show";
    /*个人中心接口*/
    public static String AppPersional = BaseUrl + "settings/getSettingsPermissions";
    /*首页接口*/
    public static String AppMain = BaseUrl + "首页";


    public final static String BASE_PIC_URL = "https://cxc.jd9sj.com/api/";//图片远程地址

}