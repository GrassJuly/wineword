package com.runjing.bean.response.good;

import java.util.List;

/**
 * @Created: qianxs  on 2020.07.23 13:41.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.23 13:41.
 * @Remark:
 */
public class SkuPromotionBean {
    private String sku;
    private List<PromoDescBean> promoDescList;
    private boolean isContainDiscount;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public List<PromoDescBean> getPromoDescList() {
        return promoDescList;
    }

    public void setPromoDescList(List<PromoDescBean> promoDescList) {
        this.promoDescList = promoDescList;
    }

    public boolean isContainDiscount() {
        return isContainDiscount;
    }

    public void setContainDiscount(boolean containDiscount) {
        isContainDiscount = containDiscount;
    }

    @Override
    public String toString() {
        return "SkuPromotionBean{" +
                "sku='" + sku + '\'' +
                ", promoDescList=" + promoDescList +
                ", isContainDiscount=" + isContainDiscount +
                '}';
    }
}
