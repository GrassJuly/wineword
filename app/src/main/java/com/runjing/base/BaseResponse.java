package com.runjing.base;

import java.io.Serializable;

/**
 * @Created by xiaoyu on 2017/1/6.
 * @Describe：通用响应码
 * @Review by：
 * @Modify by：
 * @Version : $ v_1.0 on 2017/1/6.
 * @Remark:
 */
public class BaseResponse implements Serializable {
    public String resultCode;//响应码
    public String resultInfo;//响应说明
    private String weightUnit;//单位名称 1代表斤 0代表公斤

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "resultCode='" + resultCode + '\'' +
                ", resultInfo='" + resultInfo + '\'' +
                ", weightUnit='" + weightUnit + '\'' +
                '}';
    }
}
