package com.runjing.bean.response.store;

/**
 * @Created: qianxs  on 2020.08.03 19:50.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.08.03 19:50.
 * @Remark:
 */
public class DetailStroeBean {

    /**
     * code : 200
     * data : {"addressDetail":"北京市东城区国瑞城中区9号楼1层107","distance":"1650.00","freshShopType":6,"id":131423,"image":"https://img.jd9sj.com/WechatIMG418.jpeg","latitude":"39.898772","longitude":"116.424899","name":"京东酒世界顺雅华盛（国瑞城店）","openTimeEnd":"23:30","openTimeStart":"09:00","shopMap":"","status":1,"storeType":0}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * addressDetail : 北京市东城区国瑞城中区9号楼1层107
         * distance : 1650.00
         * freshShopType : 6
         * id : 131423
         * image : https://img.jd9sj.com/WechatIMG418.jpeg
         * latitude : 39.898772
         * longitude : 116.424899
         * name : 京东酒世界顺雅华盛（国瑞城店）
         * openTimeEnd : 23:30
         * openTimeStart : 09:00
         * shopMap :
         * status : 1
         * storeType : 0
         */

        private String addressDetail;
        private String distance;
        private int freshShopType;
        private int id;
        private String image;
        private String latitude;
        private String longitude;
        private String name;
        private String openTimeEnd;
        private String openTimeStart;
        private String shopMap;
        private int status;
        private int storeType;

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public int getFreshShopType() {
            return freshShopType;
        }

        public void setFreshShopType(int freshShopType) {
            this.freshShopType = freshShopType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOpenTimeEnd() {
            return openTimeEnd;
        }

        public void setOpenTimeEnd(String openTimeEnd) {
            this.openTimeEnd = openTimeEnd;
        }

        public String getOpenTimeStart() {
            return openTimeStart;
        }

        public void setOpenTimeStart(String openTimeStart) {
            this.openTimeStart = openTimeStart;
        }

        public String getShopMap() {
            return shopMap;
        }

        public void setShopMap(String shopMap) {
            this.shopMap = shopMap;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStoreType() {
            return storeType;
        }

        public void setStoreType(int storeType) {
            this.storeType = storeType;
        }
    }
}
