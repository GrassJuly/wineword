package com.runjing.base;

import com.runjing.ui.address.SelectAddressFragment;
import com.runjing.ui.good.GoodDetailFragment;
import com.runjing.ui.home.TestHomeFragment;
import com.runjing.ui.ordre.OrderDetaiFragment;
import com.runjing.ui.search.SearchFragment;
import com.runjing.ui.home.HomeFragment;
import com.runjing.ui.mine.MineFragment;
import com.runjing.ui.ordre.OrderFragment;
import com.runjing.ui.shop.ShopCarlFragment;
import com.runjing.ui.sort.SortFragment;
import com.runjing.ui.store.StorListFragment;

/**
 * @Created by xiaoyu on 2017/1/6.
 * @Describe：跳转Fragment的枚举类
 * @Review by：
 * @Modify by：
 * @Version : $ v_1.0 on 2017/1/6.
 * @Remark:
 */
public enum SimpleBackPage {

    /*每个人的key 按自己的顺序排列 以下只是一个简单的例子，请各位做相应的替换*/
    Home(1, HomeFragment.class),
    Mine(2, MineFragment.class),
    Order(3, OrderFragment.class),
    Sort(4, SortFragment.class),
    Search(5, SearchFragment.class),
    OrderDetail(6, OrderDetaiFragment.class),
    GoodDetail(7, GoodDetailFragment.class),
    StoreList(8, StorListFragment.class),
    SelectAddress(9, SelectAddressFragment.class),
    ShopCat(10, ShopCarlFragment.class),
    TestHome(11, TestHomeFragment.class);


    private Class<?> clazz;
    private int value;

    private SimpleBackPage(int value, Class<?> cls) {

        this.clazz = cls;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public static Class<?> getPageByValue(int value) {
        for (SimpleBackPage p : values()) {
            if (p.getValue() == value)
                return p.getClazz();
        }
        return null;
    }
}
