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

    public final static String RjBaseUrl = "http://101.201.41.220/interface_supplier/";//线上正式环境
//    public final static String RjBaseUrl = "http://101.201.47.90:7080/interface_supplier/";//预生产环境
//    public final static String RjBaseUrl = "http://123.56.178.125:8080/interface_supplier/";//测试环境


    /*获取banner图片*/
    public static String GetBanner = RjBaseUrl + "获取banner图片";
    /*登录接口*/
    public static String LoginIn = RjBaseUrl + "user/login";
    /*登出接口*/
    public static String LoginOut = RjBaseUrl + "user/logout";
    /*升级接口*/
    public static String AppUpdate = RjBaseUrl + "appmanager/show";
    /*个人中心接口*/
    public static String AppPersional = RjBaseUrl + "settings/getSettingsPermissions";
    /*首页接口*/
    public static String AppMain = RjBaseUrl + "首页";

}