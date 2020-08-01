package com.runjing.bean.response.home.def;

import com.runjing.base.BaseResponse;

/**
 * @Created: qianxs  on 2020.07.17 11:18.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.17 11:18.
 * @Remark:
 */
public class HomeResponse extends BaseResponse {

    private HomeBean data;

    public HomeBean getData() {
        return data;
    }

    public void setData(HomeBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HomeResponse{" +
                "data=" + data +
                '}';
    }
}
