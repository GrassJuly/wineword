package com.runjing.bean.response.update;

/**
 * 升级接口
 * Created by zm on 2016/6/28.
 */
public class UpgradeBean {
    private String appUrl;//apk链接地址
    private String appName;//app名称
    private String appForce; //是否强制升级
    private String appId;
    private String appVersion; //app版本号
    private String appDescribe;//升级内容描述
    private String appCode;
    private String limitUpdate;

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppForce() {
        return appForce;
    }

    public void setAppForce(String appForce) {
        this.appForce = appForce;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppDescribe() {
        return appDescribe;
    }

    public void setAppDescribe(String appDescribe) {
        this.appDescribe = appDescribe;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getLimitUpdate() {
        return limitUpdate;
    }

    public void setLimitUpdate(String limitUpdate) {
        this.limitUpdate = limitUpdate;
    }

    @Override
    public String toString() {
        return "UpgradeBean{" +
                "appUrl='" + appUrl + '\'' +
                ", appName='" + appName + '\'' +
                ", appForce='" + appForce + '\'' +
                ", appId='" + appId + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", appDescribe='" + appDescribe + '\'' +
                ", appCode='" + appCode + '\'' +
                ", limitUpdate='" + limitUpdate + '\'' +
                '}';
    }
}
