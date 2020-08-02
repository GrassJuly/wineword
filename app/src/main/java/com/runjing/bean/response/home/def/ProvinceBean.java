package com.runjing.bean.response.home.def;

import java.util.List;

/**
 * @Created: qianxs  on 2020.07.19 04:15.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.19 04:15.
 * @Remark:
 */
public class ProvinceBean {
    private String province;
    private List<CityBean> citys;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<CityBean> getCitys() {
        return citys;
    }

    public void setCitys(List<CityBean> citys) {
        this.citys = citys;
    }

    @Override
    public String toString() {
        return "ProvinceBean{" +
                "province='" + province + '\'' +
                ", citys=" + citys +
                '}';
    }
}
