package com.runjing.base;


import android.text.TextUtils;

import com.runjing.MyApplication;
import com.runjing.common.Appconfig;
import com.runjing.utils.location.LocalUtil;
import com.runjing.utils.store.MMKVUtil;

import org.runjing.rjframe.utils.SystemTool;

/**
 * @Created by xiaoyu on 2017/1/6.
 * @Describe：公共请求Bean
 * @Review by：
 * @Modify by：
 * @Version : $ v_1.0 on 2017/1/6.
 * @Remark:
 */
public class BaseRequest {
    private String phonemodel;//手机机型
    private String deviceId; //设备id
    private String sessionId;//标识码判断登录过期
    private String appVersion;//版本信息
    private String latitude;
    private String longitude;
    private int pageSize;

    public BaseRequest() {
        this.latitude = LocalUtil.lat;
//        if (TextUtils.isEmpty(LocalUtil.lat)) {
//            this.latitude = MMKVUtil.getInstance().decodeDouble(Appconfig.lat) + "";
//        }
        longitude = LocalUtil.lon;
//        if (TextUtils.isEmpty(LocalUtil.lon)) {
//            this.longitude = MMKVUtil.getInstance().decodeDouble(Appconfig.lon) + "";
//        }
        this.appVersion = SystemTool.getAppVersionName(MyApplication.contextApp);
    }

    public String getPhonemodel() {
        return phonemodel;
    }

    public void setPhonemodel(String phonemodel) {
        this.phonemodel = phonemodel;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "BaseRequest{" +
                "phonemodel='" + phonemodel + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
