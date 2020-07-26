package com.runjing.bean.response.update;


import com.runjing.base.BaseResponse;

/**
 * 升级接口响应
 * Created by zm on 2016/6/28.
 */
public class UpgradeResponse extends BaseResponse {

    private UpgradeBean data;

    public UpgradeBean getData() {
        return data;
    }

    public void setData(UpgradeBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UpgradeResponse{" +
                "data=" + data +
                '}';
    }
}
