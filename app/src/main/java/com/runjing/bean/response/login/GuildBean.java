package com.runjing.bean.response.login;

import com.runjing.base.BaseResponse;
import com.runjing.bean.response.home.BannerBean;

import java.util.List;

/**
 * @Created: qianxs  on 2020.07.20 13:48.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.20 13:48.
 * @Remark:
 */
public class GuildBean extends BaseResponse {
    private List<BannerBean> data;

    public List<BannerBean> getData() {
        return data;
    }

    public void setData(List<BannerBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GuildBean{" +
                "data=" + data +
                '}';
    }
}
