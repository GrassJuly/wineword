package com.runjing.bean.test;

import com.alibaba.fastjson.JSON;
import com.runjing.bean.response.good.GoodDetailBean;
import com.runjing.bean.response.home.BannerBean;
import com.runjing.bean.response.home.CityBean;
import com.runjing.bean.response.home.GoodBean;
import com.runjing.bean.response.home.GoodTagBean;
import com.runjing.bean.response.home.HomeBean;
import com.runjing.bean.response.home.ProvinceBean;
import com.runjing.bean.response.home.StoreBean;
import com.runjing.ui.home.HomeFragment;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created: qianxs  on 2020.07.17 10:44.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.17 10:44.
 * @Remark:
 */
public class HomeData {

    public static HomeBean getHomeData() {

        HomeBean homeBean = new HomeBean();
        List<BannerBean> images = new ArrayList<>();
        BannerBean bean1 = new BannerBean();
        bean1.setImage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1349551846,2489686808&fm=26&gp=0.jpg");
        images.add(bean1);
        BannerBean bean2 = new BannerBean();
        bean2.setImage("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=344437805,3086543967&fm=15&gp=0.jpg");
        images.add(bean2);
        BannerBean bean3 = new BannerBean();
        bean3.setImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594862788656&di=4b1a3077ebfbc261d01a27600e43eafd&imgtype=0&src=http%3A%2F%2Fpic1.zhimg.com%2F50%2Fv2-42bd7dfcfe0ab4962c6e2b815d92757e_hd.jpg");
        images.add(bean3);
        homeBean.setImages(images);

        List<GoodBean> goods = new ArrayList<>();
        GoodBean good = new GoodBean();
        good.setImage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1349551846,2489686808&fm=26&gp=0.jpg");
        good.setName("五粮液酿身佳品52度浓香型");
        good.setDesc("白酒500ml/瓶");
        good.setPrice("¥259.00");
        good.setFavorablePrice("¥259.00");
        good.setState(1);
        List<GoodTagBean> tags = new ArrayList<>();
        GoodTagBean tag = new GoodTagBean();
        tag.setTag("买一赠一");
        GoodTagBean tag1 = new GoodTagBean();
        tag.setTag("直降");
        tags.add(tag);
        tags.add(tag1);
        tags.add(tag1);
        good.setTags(tags);

        GoodBean good1 = new GoodBean();
        good1.setImage("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1553171949,3079781356&fm=26&gp=0.jpg");
        good1.setName("五粮液酿身佳品52度浓香型");
        good1.setDesc("白酒500ml/瓶");
        good1.setPrice("¥259.00");
        good1.setFavorablePrice("¥259.00");
        good1.setState(1);
        List<GoodTagBean> tags2 = new ArrayList<>();
        GoodTagBean tag2 = new GoodTagBean();
        tag2.setTag("买一赠一");
        GoodTagBean tag22 = new GoodTagBean();
        tag22.setTag("直降");
        tags2.add(tag2);
        tags2.add(tag22);
        good1.setTags(tags2);

        GoodBean good2 = new GoodBean();
        good2.setImage("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1902839288,3768379962&fm=26&gp=0.jpg");
        good2.setName("五粮液酿身佳品52度浓香型");
        good2.setDesc("白酒500ml/瓶");
        good2.setPrice("¥259.00");
        good2.setFavorablePrice("¥259.00");
        good2.setState(1);
        List<GoodTagBean> tags3 = new ArrayList<>();
        GoodTagBean tag3 = new GoodTagBean();
        tag3.setTag("买一赠一");
        GoodTagBean tag33 = new GoodTagBean();
        tag.setTag("直降");
        tags3.add(tag3);
        tags3.add(tag33);
        good2.setTags(tags3);

        GoodBean good3 = new GoodBean();
        good3.setState(1);
        good3.setImage("http://a3.att.hudong.com/55/22/20300000929429130630222900050.jpg");
        good3.setName("五粮液酿身佳品52度浓香型");
        good3.setDesc("白酒500ml/瓶");
        good3.setPrice("¥259.00");
        good3.setFavorablePrice("¥259.00");
        List<GoodTagBean> tags4 = new ArrayList<>();
        GoodTagBean tag4 = new GoodTagBean();
        tag4.setTag("买一赠一");
        GoodTagBean tag44 = new GoodTagBean();
        tag44.setTag("直降");
        tags4.add(tag4);
        tags4.add(tag44);
        good3.setTags(tags4);

        GoodBean good4 = new GoodBean();
        good4.setImage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3714408895,354969059&fm=26&gp=0.jpg");
        good4.setName("五粮液酿身佳品52度浓香型");
        good4.setDesc("白酒500ml/瓶");
        good4.setPrice("¥259.00");
        good4.setFavorablePrice("¥259.00");
        List<GoodTagBean> tags5 = new ArrayList<>();
        GoodTagBean tag5 = new GoodTagBean();
        tag5.setState(0);
        tag5.setTag("买一赠一");
        GoodTagBean tag55 = new GoodTagBean();
        tag55.setTag("直降");
        tags5.add(tag);
        tags5.add(tag5);
        tags5.add(tag55);
        good4.setTags(tags);
        good4.setState(0);
        goods.add(good);
        goods.add(good1);
        goods.add(good2);
        goods.add(good3);
        goods.add(good4);
//        homeBean.setGoods(goods);

        List<ProvinceBean> provinceBeans = new ArrayList<>();
        ProvinceBean provinceBean = new ProvinceBean();
        provinceBean.setProvince("北京");
        List<CityBean> cityBeans = new ArrayList<>();
        CityBean cityBean = new CityBean();
        cityBean.setCity("大兴");
        cityBeans.add(cityBean);
        cityBeans.add(cityBean);
        cityBeans.add(cityBean);
        cityBeans.add(cityBean);
        provinceBean.setCitys(cityBeans);
        ProvinceBean provinceBean1 = new ProvinceBean();
        provinceBean1.setProvince("天津");
        List<CityBean> cityBeans1 = new ArrayList<>();
        CityBean cityBean1 = new CityBean();
        cityBean1.setCity("河东");
        cityBeans1.add(cityBean1);
        cityBeans1.add(cityBean1);
        cityBeans1.add(cityBean1);
        cityBeans1.add(cityBean1);
        cityBeans1.add(cityBean1);
        cityBeans1.add(cityBean1);
        provinceBean1.setCitys(cityBeans);
        provinceBeans.add(provinceBean);
        provinceBeans.add(provinceBean1);
        provinceBeans.add(provinceBean1);
        provinceBeans.add(provinceBean1);
        provinceBeans.add(provinceBean1);
        homeBean.setProvinces(provinceBeans);

        List<StoreBean> stores = new ArrayList<>();
        StoreBean storeBean = new StoreBean();
        storeBean.setStoreImage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3714408895,354969059&fm=26&gp=0.jpg");
        storeBean.setName("润京旗舰店");
        storeBean.setAddress("中航国际广场");
        storeBean.setDistance("3.5km");
        storeBean.setStatus(0);
        stores.add(storeBean);
        stores.add(storeBean);
        stores.add(storeBean);
        stores.add(storeBean);
        stores.add(storeBean);
//        homeBean.setStores(stores);


        return homeBean;
    }


    /**
     * 导航页面数据
     * @return
     */
    public static List<BannerBean> getBanner() {
        List<BannerBean> banners = new ArrayList<>();
        BannerBean bannerBean = new BannerBean();
        bannerBean.setImage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3714408895,354969059&fm=26&gp=0.jpg");
        banners.add(bannerBean);
        banners.add(bannerBean);
        banners.add(bannerBean);
        return banners;
    }



}
