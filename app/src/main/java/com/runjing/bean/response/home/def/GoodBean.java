package com.runjing.bean.response.home.def;

import java.util.List;

/**
 * @Created: qianxs  on 2020.07.14 10:30.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.14 10:30.
 * @Remark:
 */
public class GoodBean {
    private String goodId;
    private String image;
    private String name;
    private String desc;
    private String price;
    private String favorablePrice;
    private List<GoodTagBean> tags;
    private int state;

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFavorablePrice() {
        return favorablePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFavorablePrice(String favorablePrice) {
        this.favorablePrice = favorablePrice;
    }

    public List<GoodTagBean> getTags() {
        return tags;
    }

    public void setTags(List<GoodTagBean> tags) {
        this.tags = tags;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "GoodBean{" +
                "goodId='" + goodId + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price='" + price + '\'' +
                ", favorablePrice='" + favorablePrice + '\'' +
                ", tags=" + tags +
                ", state=" + state +
                '}';
    }
}
