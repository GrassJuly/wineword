package com.runjing.bean.response.home;

/**
 * @Created: qianxs  on 2020.07.19 04:16.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.19 04:16.
 * @Remark:
 */
public class CityBean {
    private String city;
    private String cityId;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "CityBean{" +
                "city='" + city + '\'' +
                ", cityId='" + cityId + '\'' +
                '}';
    }
}
