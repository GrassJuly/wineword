package com.runjing.base;


import com.runjing.MyApplication;

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
    private String userId; //用户id
    private String deviceId; //设备id
    private String sessionId;//标识码判断登录过期
    private String accountId; //账户id
    private String appVersion;//版本信息
    private String shopsCode;//商户编码

    public BaseRequest() {
        this.accountId = MyApplication.accountId;
        this.appVersion = SystemTool.getAppVersionName(MyApplication.contextApp);
        shopsCode = MyApplication.shopCode;
    }

    public String getShopsCode() {
        return shopsCode;
    }

    public void setShopsCode(String shopsCode) {
        this.shopsCode = shopsCode;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getPhonemodel() {
        return phonemodel;
    }

    public void setPhonemodel(String phonemodel) {
        this.phonemodel = phonemodel;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "BaseRequest{" +
                "phonemodel='" + phonemodel + '\'' +
                ", userId='" + userId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", shopsCode='" + shopsCode + '\'' +
                '}';
    }
}
