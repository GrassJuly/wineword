package com.runjing.bean.response.home;

import com.runjing.base.BaseResponse;

import java.util.List;

/**
 * @Created: qianxs  on 2020.07.14 10:29.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.14 10:29.
 * @Remark:
 */
public class HomeBean {

    private List<BannerBean> images;

    private List<GoodBean> goods;

    public List<BannerBean> getImages() {
        return images;
    }

    public void setImages(List<BannerBean> images) {
        this.images = images;
    }

    public List<GoodBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodBean> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "HomeBean{" +
                "images=" + images +
                ", goods=" + goods +
                '}';
    }
}

