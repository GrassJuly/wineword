package com.runjing.bean.response.home;

/**
 * @Created: qianxs  on 2020.07.30 18:38.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.30 18:38.
 * @Remark:
 */
public class HomeData {
    public static final int TYPE_ITEM_GOOD = 0X120;
    public static final int TYPE_ITEM_STORE = 0X121;
    public static final int TYPE_ITEM_CITY = 0X122;

    private DistrictBean districtBean;
    private HomeStoreBean homeStoreBean;
    private BannerBean bannerBean;
    private GoodBean homeGoodBean;
    private int itemTpye;

    public HomeData(DistrictBean districtBean, HomeStoreBean homeStoreBean, BannerBean bannerBean, GoodBean homeGoodBean) {
        this.districtBean = districtBean;
        this.homeStoreBean = homeStoreBean;
        this.bannerBean = bannerBean;
        this.homeGoodBean = homeGoodBean;
    }

    static class Helper {
        private static HomeData INSTANCE = new HomeData();
    }

    public static HomeData getInstance() {
        return Helper.INSTANCE;
    }

    private HomeData() {
    }

    public DistrictBean getDistrictBean() {
        return districtBean;
    }

    public void setDistrictBean(DistrictBean districtBean) {
        this.districtBean = districtBean;
    }

    public HomeStoreBean getHomeStoreBean() {
        return homeStoreBean;
    }

    public void setHomeStoreBean(HomeStoreBean homeStoreBean) {
        this.homeStoreBean = homeStoreBean;
    }

    public BannerBean getBannerBean() {
        return bannerBean;
    }

    public void setBannerBean(BannerBean bannerBean) {
        this.bannerBean = bannerBean;
    }

    public GoodBean getHomeGoodBean() {
        return homeGoodBean;
    }

    public void setHomeGoodBean(GoodBean homeGoodBean) {
        this.homeGoodBean = homeGoodBean;
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
                "districtBean=" + districtBean +
                ", homeStoreBean=" + homeStoreBean +
                ", bannerBean=" + bannerBean +
                ", homeGoodBean=" + homeGoodBean +
                '}';
    }

    public boolean loadFinish() {
        while (districtBean != null && homeStoreBean != null && homeGoodBean != null) {
            return true;
        }
        return false;
    }
}
