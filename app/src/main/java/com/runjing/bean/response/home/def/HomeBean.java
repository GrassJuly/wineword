package com.runjing.bean.response.home.def;


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
    public static final int TYPE_ITEM_GOOD = 0X120;
    public static final int TYPE_ITEM_STORE = 0X121;
    public static final int TYPE_ITEM_CITY = 0X122;
    private List<BannerBean> images;
    private List<GoodBean> goods;
    private List<StoreBean> stores;
    private List<ProvinceBean> provinces;
    private int itemTpye;

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
        setItemTpye(TYPE_ITEM_GOOD);
    }

    public List<StoreBean> getStores() {
        return stores;
    }

    public void setStores(List<StoreBean> stores) {
        this.stores = stores;
        setItemTpye(TYPE_ITEM_STORE);
    }

    public List<ProvinceBean> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<ProvinceBean> provinces) {
        this.provinces = provinces;
        setItemTpye(TYPE_ITEM_CITY);
    }

    public int getItemTpye() {
        return itemTpye;
    }

    public void setItemTpye(int itemTpye) {
        this.itemTpye = itemTpye;
    }

    @Override
    public String toString() {
        return "HomeBean{" +
                "images=" + images +
                ", goods=" + goods +
                ", stores=" + stores +
                ", provinces=" + provinces +
                ", itemTpye=" + itemTpye +
                '}';
    }
}

