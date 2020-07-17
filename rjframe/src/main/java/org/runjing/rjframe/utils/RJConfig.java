package org.runjing.rjframe.utils;

/**
 * Created by admin on 2016/4/29.
 */
public class RJConfig {


    public static final double VERSION = 2.6;

    /**
     * 错误处理广播
     */
    public static final String RECEIVER_ERROR = RJConfig.class.getName()
            + "com.meinong.android.error";
    /**
     * 无网络警告广播
     */
    public static final String RECEIVER_NOT_NET_WARN = RJConfig.class.getName()
            + "com.meinong.android.notnet";
    /**
     * preference键值对
     */
    public static final String SETTING_FILE = "mn_preference";
    public static final String ONLY_WIFI = "only_wifi";
}
