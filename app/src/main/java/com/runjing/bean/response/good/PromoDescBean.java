package com.runjing.bean.response.good;

import java.util.List;

/**
 * @Created: qianxs  on 2020.07.23 13:42.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.23 13:42.
 * @Remark:
 */
public class PromoDescBean {
    private String needProcess;
    private String promoSubType;
    private String privilegePrice;
    private String adv;
    private String channel;
    private List<FullPromotionBean> fullPromotionList;
    private String boundPinType;
    private String endTime;
    private String promoId;
    private String name;
    private String platform;
    private String tenantId;
    private List<RiskTypesBean> riskTypes;
    private List<GiftItemListBean> giftItemList;
    private String boundNumberType;
    private String saleMode;
    private String reason;
    private String everydayBoundPinType;
    private String saleUnit;
    private String promoType;
    private String beginTime;

    public String getNeedProcess() {
        return needProcess;
    }

    public void setNeedProcess(String needProcess) {
        this.needProcess = needProcess;
    }

    public String getPromoSubType() {
        return promoSubType;
    }

    public void setPromoSubType(String promoSubType) {
        this.promoSubType = promoSubType;
    }

    public String getPrivilegePrice() {
        return privilegePrice;
    }

    public void setPrivilegePrice(String privilegePrice) {
        this.privilegePrice = privilegePrice;
    }

    public String getAdv() {
        return adv;
    }

    public void setAdv(String adv) {
        this.adv = adv;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public List<FullPromotionBean> getFullPromotionList() {
        return fullPromotionList;
    }

    public void setFullPromotionList(List<FullPromotionBean> fullPromotionList) {
        this.fullPromotionList = fullPromotionList;
    }

    public String getBoundPinType() {
        return boundPinType;
    }

    public void setBoundPinType(String boundPinType) {
        this.boundPinType = boundPinType;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public List<RiskTypesBean> getRiskTypes() {
        return riskTypes;
    }

    public void setRiskTypes(List<RiskTypesBean> riskTypes) {
        this.riskTypes = riskTypes;
    }

    public List<GiftItemListBean> getGiftItemList() {
        return giftItemList;
    }

    public void setGiftItemList(List<GiftItemListBean> giftItemList) {
        this.giftItemList = giftItemList;
    }

    public String getBoundNumberType() {
        return boundNumberType;
    }

    public void setBoundNumberType(String boundNumberType) {
        this.boundNumberType = boundNumberType;
    }

    public String getSaleMode() {
        return saleMode;
    }

    public void setSaleMode(String saleMode) {
        this.saleMode = saleMode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEverydayBoundPinType() {
        return everydayBoundPinType;
    }

    public void setEverydayBoundPinType(String everydayBoundPinType) {
        this.everydayBoundPinType = everydayBoundPinType;
    }

    public String getSaleUnit() {
        return saleUnit;
    }

    public void setSaleUnit(String saleUnit) {
        this.saleUnit = saleUnit;
    }

    public String getPromoType() {
        return promoType;
    }

    public void setPromoType(String promoType) {
        this.promoType = promoType;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    @Override
    public String toString() {
        return "PromoDescBean{" +
                "needProcess='" + needProcess + '\'' +
                ", promoSubType='" + promoSubType + '\'' +
                ", privilegePrice='" + privilegePrice + '\'' +
                ", adv='" + adv + '\'' +
                ", channel='" + channel + '\'' +
                ", fullPromotionList=" + fullPromotionList +
                ", boundPinType='" + boundPinType + '\'' +
                ", endTime='" + endTime + '\'' +
                ", promoId='" + promoId + '\'' +
                ", name='" + name + '\'' +
                ", platform='" + platform + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", riskTypes=" + riskTypes +
                ", giftItemList=" + giftItemList +
                ", boundNumberType='" + boundNumberType + '\'' +
                ", saleMode='" + saleMode + '\'' +
                ", reason='" + reason + '\'' +
                ", everydayBoundPinType='" + everydayBoundPinType + '\'' +
                ", saleUnit='" + saleUnit + '\'' +
                ", promoType='" + promoType + '\'' +
                ", beginTime='" + beginTime + '\'' +
                '}';
    }
}
