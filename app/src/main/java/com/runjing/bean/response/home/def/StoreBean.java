package com.runjing.bean.response.home.def;

/**
 * @Created: qianxs  on 2020.07.19 03:18.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.19 03:18.
 * @Remark:
 */
public class StoreBean {
    private String storeId;
    private String storeImage;
    private String name;
    private String address;
    private String distance;
    private int status;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StoreBean{" +
                "storeId='" + storeId + '\'' +
                ", storeImage='" + storeImage + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", distance='" + distance + '\'' +
                ", status=" + status +
                '}';
    }
}
