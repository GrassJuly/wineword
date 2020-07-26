package com.runjing.bean.response.good;

/**
 * @Created: qianxs  on 2020.07.23 13:39.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.23 13:39.
 * @Remark:
 */
public class SkuImageBean {
    private String imgUrl;
    private String sceneType;
    private String tenantId;
    private String index;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSceneType() {
        return sceneType;
    }

    public void setSceneType(String sceneType) {
        this.sceneType = sceneType;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "SkuImageBean{" +
                "imgUrl='" + imgUrl + '\'' +
                ", sceneType='" + sceneType + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}
