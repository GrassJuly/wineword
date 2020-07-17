package com.runjing.bean.response.home;

/**
 * @Created: qianxs  on 2020.07.17 00:11.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.17 00:11.
 * @Remark:
 */
public class BannerBean {
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "BannerBean{" +
                "image='" + image + '\'' +
                '}';
    }
}
