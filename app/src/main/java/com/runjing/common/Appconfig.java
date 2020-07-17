package com.runjing.common;

import java.util.UUID;

/**
 * @Created by xiaoyu on 2017/1/6.
 * @Describe：公共常量
 * @Review by：
 * @Modify by：
 * @Version : $ v_1.0 on 2017/1/6.
 * @Remark:
 */
public class Appconfig {
    public static String LoginSuccess = "113";//登录  请求成功8
    public static String RequestSuccess = "116";//网络 请求成功
    public static String LoadMorePageSize = "15";//请求数据条数
    public static String RESPONSE_CODE_RESULTS = "100";//数据查询有数据
    public static String RESPONSE_CODE_NO_DATA = "110";//数据查询无数据
    public static String RESPONSE_CODE_NORESULTS = "101";//数据查询 无数据
    public static String ACCOUNT_REPEAT = "3008";//账户重复，请联系客服
    public static String CONTENT_KEY = "mn_content_key";
    public static String DATA_KEY = "mngx_data_key";
    public static final int DEFAULT_VALUES = -1000;//默认 数据
    public static int DEFAULT_VALUE_LONG = 1;
    /*是否强制升级*/
    public static String isFource;
    /*是否下载*/
    public static String isDownLoad = "F";
    /*是否强制升级*/
    public static String LimitError = "0";
    public static String isLimtCode = "1";
    /*临时添加常量字段*/
    public static String TAG = "supplier";
    public static String TAG_One = "supplie1";
    public static String TAG_Two = "supplier_2";
    public static String TAG_Three = "supplier_3";
    public static String TAG_Four = "supplier_4";
    /*蓝牙打印缓存*/
    public static final String Bluetooth = "Bluetooth";
    public static final String Tag0 = "0";
    public static final String Tag1 = "1";
    public static final String Tag2 = "2";
    public static final String Tag3 = "3";
    public static final int TAG_ZERO = 0;
    public static final int TAG_ONE = 1;
    public static final int TAG_TWO = 2;
    public static final int TAG_THREE = 3;
    public static final int TAG_FOUR = 4;
    public static final int TAG_FIVE = 5;
    public static final int TAG_SIX = 6;
    public static final int TAG_SEVEN = 7;
    public static final int TAG_EIGHT = 8;
    public static final int TAG_NINE = 9;
    public static final int TAG_TEN = 10;
    public static final int TAG_ELEVEN = 11;
    public static final int TAG_TWELVE = 12;
    public static final int TAG_THIRTEEN = 13;
    public static final int TAG_FOURTEEN = 14;
    public static final int TAG_FIFTEEN = 15;
    public static final int TAG_SIXTEEN = 16;
    public static final int TAG_SEVENTEEN = 17;
    public static final int TAG_EIGHTEEN= 18;
    public static final int TAG_TENTHOUSAND_ONE = 10001;
    public static final int TAG_TENTHOUSAND_TWO = 10002;
    /* 访问图片的基础路径*/
    public static final String BASE_PIC_URL = "http://101.201.111.60/";
    /** * 蓝牙UUID*/
    public static UUID SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    //启用蓝牙
    public static final int BLUE_TOOTH_OPEN = 1000;
    //禁用蓝牙
    public static final int BLUE_TOOTH_CLOSE = BLUE_TOOTH_OPEN + TAG_ONE;
    //搜索蓝牙
    public static final int BLUE_TOOTH_SEARTH = BLUE_TOOTH_CLOSE + TAG_ONE;
    //被搜索蓝牙
    public static final int BLUE_TOOTH_MY_SEARTH = BLUE_TOOTH_SEARTH + TAG_ONE;
    //关闭蓝牙连接
    public static final int BLUE_TOOTH_CLEAR = BLUE_TOOTH_MY_SEARTH + TAG_ONE;
    public static final int Fruits_Value = TAG_ONE;
}
