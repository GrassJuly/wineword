package com.runjing.common;


import static com.runjing.MyApplication.MnBaseUrl;

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

    public final static String MnBaseUrl1 = "http://101.201.41.220/interface_supplier/";//线上正式环境(1)
    public final static String MnBaseUrl2 = "http://101.201.47.90:7080/interface_supplier/";//线上正式环境(2)
    public final static String MnBaseUrl3 = "http://123.56.178.125:8080/interface_supplier/";//测试环境
//    public final static String MnBaseUrl = "http://123.56.177.118/interface_supplier/";//准生产试（1）
//    public final static String MnBaseUrl = "http://101.201.221.230:9080/interface_supplier/";//准生产测试（2）
//    public final static String MnBaseUrl = "http://101.201.221.230:7080/interface_supplier/";//准生产测试（3）
//    public final static String MnBaseUrl = "http://192.168.61.40:8080/interface_supplier/";//彭德满
//    public final static String MnBaseUrl = "http://192.168.62.33:8080/interface_supplier/";//小雨
//    public final static String MnBaseUrl = "http://192.168.57.58:8080/interface_supplier/";//孙俊杰
//      public final static String MnBaseUrl = "http://192.168.64.249:8080/interface_supplier/";//张申兆
//    public final static String MnBaseUrl = "http://192.168.65.151:8080/interface_supplier/";//赵润凯
//    public final static String MnBaseUrl = "http://192.168.41.135:8082/interface_supplier/";//杨师利


    /*登录接口*/
    public static String LoginIn = MnBaseUrl + "user/login";
    /*登出接口*/
    public static String LoginOut = MnBaseUrl + "user/logout";
    /*升级接口*/
    public static String AppUpdate = MnBaseUrl + "appmanager/show";
    /*个人中心接口*/
    public static String AppPersional = MnBaseUrl + "settings/getSettingsPermissions";
    /*首页接口*/
    public static String AppMain = MnBaseUrl + "首页";

}