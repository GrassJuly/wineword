package com.runjing.bean.response.good;

import java.util.List;

/**
 * @Created: qianxs  on 2020.07.23 13:19.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.23 13:19.
 * @Remark:
 */
public class GoodDetailBean {
    private List<SkuInfoBean> skuInfos;
    private StoreBean storeInfo;
    private String settlementButtonState;

    public List<SkuInfoBean> getSkuInfos() {
        return skuInfos;
    }

    public void setSkuInfos(List<SkuInfoBean> skuInfos) {
        this.skuInfos = skuInfos;
    }

    public StoreBean getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(StoreBean storeInfo) {
        this.storeInfo = storeInfo;
    }

    public String getSettlementButtonState() {
        return settlementButtonState;
    }

    public void setSettlementButtonState(String settlementButtonState) {
        this.settlementButtonState = settlementButtonState;
    }

    @Override
    public String toString() {
        return "GoodDetailBean{" +
                "skuInfos=" + skuInfos +
                ", storeInfo=" + storeInfo +
                ", settlementButtonState='" + settlementButtonState + '\'' +
                '}';
    }
}
