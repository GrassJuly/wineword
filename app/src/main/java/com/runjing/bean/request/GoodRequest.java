package com.runjing.bean.request;

import com.runjing.base.BaseRequest;

/**
 * @Created: qianxs  on 2020.07.17 11:14.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.17 11:14.
 * @Remark:
 */
public class GoodRequest extends BaseRequest {

    private String f7StoreId, goodsName;

    public String getF7StoreId() {
        return f7StoreId;
    }

    public void setF7StoreId(String f7StoreId) {
        this.f7StoreId = f7StoreId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Override
    public String toString() {
        return "GoodRequest{" +
                "f7StoreId='" + f7StoreId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                '}';
    }
}
