package com.runjing.bean.response.good;

import com.runjing.base.BaseResponse;
import com.runjing.bean.response.home.GoodBean;

/**
 * @Created: qianxs  on 2020.07.23 13:21.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.23 13:21.
 * @Remark:
 */
public class GoodDetailBaseBean extends BaseResponse {
    private GoodDetailBean data;

    public GoodDetailBean getData() {
        return data;
    }

    public void setData(GoodDetailBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoodDetailBaseBean{" +
                "data=" + data +
                '}';
    }
}
