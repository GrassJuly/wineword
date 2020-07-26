package com.runjing.bean.response.good;

/**
 * @Created: qianxs  on 2020.07.23 14:34.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.23 14:34.
 * @Remark:
 *
 * @desc：根据状态码 自主拼接前面文字
 *
 * 100 直降
 * 101 单品折扣
 * 102 秒杀
 * 200 买赠
 * 300 满减
 * 301 满折
 * 302 满赠
 * 307 m元n件
 * 1001 酒币抵现
 * 1002 买送酒币
 * 1003 PLUS专享 （现在不确定，小程序和后端正在开发）
 * 免运费
 *
 * @desc: 名称要拼接
 *
 * 100 name
 * 101 name
 * 102 距结束剩余00:00:00
 * 200 买一个，赠count个skuName
 * 300 满￥condition元，减￥result元
 * 301 满condition件result折
 * 302 满￥condition元，赠skuName
 * 307 仅需result元，可任选condition件
 * 1001 name
 * 1002 name
 * 1003 name （现在不确定，小程序和后端正在开发）
 * 免运费 全场免运费
 *
 * @desc:箭头显示 TODO有其一便显示
 *
 * 102
 * 200
 * 300
 * 301
 * 302
 * 307
 * 1003 (是否要显示箭头，做到的时候需要问马阳)
 */
public class DiscountBean {
    private int promoSubType;
    private String tagName;
    private String name;
    private boolean isArrow;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getPromoSubType() {
        return promoSubType;
    }

    public void setPromoSubType(int promoSubType) {
        this.promoSubType = promoSubType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isArrow() {
        return isArrow;
    }

    public void setArrow(boolean arrow) {
        isArrow = arrow;
    }

    @Override
    public String toString() {
        return "DiscountBean{" +
                "promoSubType=" + promoSubType +
                ", tagName='" + tagName + '\'' +
                ", name='" + name + '\'' +
                ", isArrow=" + isArrow +
                '}';
    }
}
