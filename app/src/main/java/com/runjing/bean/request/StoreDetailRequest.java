package com.runjing.bean.request;

import com.runjing.base.BaseRequest;

/**
 * @Created: qianxs  on 2020.08.03 19:44.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.08.03 19:44.
 * @Remark:
 */
public class StoreDetailRequest extends BaseRequest {
    private String storeId;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "StoreDetailRequest{" +
                "storeId='" + storeId + '\'' +
                '}';
    }
}
