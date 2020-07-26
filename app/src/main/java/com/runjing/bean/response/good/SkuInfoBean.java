package com.runjing.bean.response.good;

import java.util.List;

/**
 * @Created: qianxs  on 2020.07.23 13:31.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.23 13:31.
 * @Remark:
 */
public class SkuInfoBean {
    private String specification;
    private String storeId;
    private String unit;
    private String saleAward;
    private String goodsSn;
    private String saleText;
    private String marketPrice;
    private String stock;
    private String skuId;
    private String storeSn;
    private String goodsName;
    private String mobiledesc;
    private List<SkuImageBean> images;
    private String cartNum;
    private List<SkuPromotionBean> skuPromotionResult;
    private String salesPrice;
    private String status;

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSaleAward() {
        return saleAward;
    }

    public void setSaleAward(String saleAward) {
        this.saleAward = saleAward;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public String getSaleText() {
        return saleText;
    }

    public void setSaleText(String saleText) {
        this.saleText = saleText;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getStoreSn() {
        return storeSn;
    }

    public void setStoreSn(String storeSn) {
        this.storeSn = storeSn;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getMobiledesc() {
        return mobiledesc;
    }

    public void setMobiledesc(String mobiledesc) {
        this.mobiledesc = mobiledesc;
    }

    public List<SkuImageBean> getImages() {
        return images;
    }

    public void setImages(List<SkuImageBean> images) {
        this.images = images;
    }

    public String getCartNum() {
        return cartNum;
    }

    public void setCartNum(String cartNum) {
        this.cartNum = cartNum;
    }

    public List<SkuPromotionBean> getSkuPromotionResult() {
        return skuPromotionResult;
    }

    public void setSkuPromotionResult(List<SkuPromotionBean> skuPromotionResult) {
        this.skuPromotionResult = skuPromotionResult;
    }

    public String getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SkuInfoBean{" +
                "specification='" + specification + '\'' +
                ", storeId='" + storeId + '\'' +
                ", unit='" + unit + '\'' +
                ", saleAward='" + saleAward + '\'' +
                ", goodsSn='" + goodsSn + '\'' +
                ", saleText='" + saleText + '\'' +
                ", marketPrice='" + marketPrice + '\'' +
                ", stock='" + stock + '\'' +
                ", skuId='" + skuId + '\'' +
                ", storeSn='" + storeSn + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", mobiledesc='" + mobiledesc + '\'' +
                ", images=" + images +
                ", cartNum='" + cartNum + '\'' +
                ", skuPromotionResult=" + skuPromotionResult +
                ", salesPrice='" + salesPrice + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
