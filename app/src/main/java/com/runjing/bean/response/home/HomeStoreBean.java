package com.runjing.bean.response.home;

import java.util.List;

/**
 * @Created: qianxs  on 2020.07.30 16:33.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.30 16:33.
 * @Remark:
 */
public class HomeStoreBean {

    /**
     * code : 200
     * data : {"freshStoreVOList":[{"addressDetail":"北京市大兴区兴政街15号","distance":"230.00","freshShopType":6,"id":132916,"image":"https://img.jd9sj.com/WechatIMG418.jpeg","latitude":"39.724926","longitude":"116.340474","name":"倩倩","openTimeEnd":"23:00","openTimeStart":"09:00","shopMap":"","status":1,"storeType":7},{"addressDetail":"河南省洛阳市长安小区12号","distance":"17930.00","freshShopType":6,"id":132833,"image":"https://img.jd9sj.com/WechatIMG418.jpeg","latitude":"39.822036","longitude":"116.510971","name":"普通门店1","openTimeEnd":"22:00","openTimeStart":"10:00","shopMap":"","status":1,"storeType":0},{"addressDetail":"马驹桥镇","distance":"21060.00","freshShopType":1,"id":132850,"image":"https://img.jd9sj.com/WechatIMG418.jpeg","latitude":"39.876557","longitude":"116.492686","name":"旗舰店2","openTimeEnd":"22:00","openTimeStart":"10:00","shopMap":"","status":1,"storeType":7},{"addressDetail":"郑州站","distance":"602510.00","freshShopType":6,"id":131667,"image":"https://img.jd9sj.com/WechatIMG418.jpeg","latitude":"34.745681","longitude":"113.658581","name":"得辉商行","openTimeEnd":"22:00","openTimeStart":"08:00","shopMap":"","status":1,"storeType":6},{"addressDetail":"郑州站","distance":"602510.00","freshShopType":6,"id":131666,"image":"https://img.jd9sj.com/WechatIMG418.jpeg","latitude":"34.745681","longitude":"113.658581","name":"郑州门店1122","openTimeEnd":"22:00","openTimeStart":"10:00","shopMap":"","status":1,"storeType":6},{"addressDetail":"宝安国际机场","distance":"1916410.00","freshShopType":6,"id":131684,"image":"https://img.jd9sj.com/WechatIMG418.jpeg","latitude":"22.630293","longitude":"113.812265","name":"苏苏深圳测试门店1","openTimeEnd":"23:00","openTimeStart":"09:00","shopMap":"","status":1,"storeType":0},{"addressDetail":"北京市大兴区荣华南路","distance":"1921560.00","freshShopType":6,"id":131335,"image":"https://img.jd9sj.com/WechatIMG418.jpeg","latitude":"22.551682","longitude":"114.135625","name":"测试密码","openTimeEnd":"23:00","openTimeStart":"09:00","shopMap":"","status":1,"storeType":0}],"type":2}
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
         * freshStoreVOList : [{"addressDetail":"北京市大兴区兴政街15号","distance":"230.00","freshShopType":6,"id":132916,"image":"https://img.jd9sj.com/WechatIMG418.jpeg","latitude":"39.724926","longitude":"116.340474","name":"倩倩","openTimeEnd":"23:00","openTimeStart":"09:00","shopMap":"","status":1,"storeType":7},{"addressDetail":"河南省洛阳市长安小区12号","distance":"17930.00","freshShopType":6,"id":132833,"image":"https://img.jd9sj.com/WechatIMG418.jpeg","latitude":"39.822036","longitude":"116.510971","name":"普通门店1","openTimeEnd":"22:00","openTimeStart":"10:00","shopMap":"","status":1,"storeType":0},{"addressDetail":"马驹桥镇","distance":"21060.00","freshShopType":1,"id":132850,"image":"https://img.jd9sj.com/WechatIMG418.jpeg","latitude":"39.876557","longitude":"116.492686","name":"旗舰店2","openTimeEnd":"22:00","openTimeStart":"10:00","shopMap":"","status":1,"storeType":7},{"addressDetail":"郑州站","distance":"602510.00","freshShopType":6,"id":131667,"image":"https://img.jd9sj.com/WechatIMG418.jpeg","latitude":"34.745681","longitude":"113.658581","name":"得辉商行","openTimeEnd":"22:00","openTimeStart":"08:00","shopMap":"","status":1,"storeType":6},{"addressDetail":"郑州站","distance":"602510.00","freshShopType":6,"id":131666,"image":"https://img.jd9sj.com/WechatIMG418.jpeg","latitude":"34.745681","longitude":"113.658581","name":"郑州门店1122","openTimeEnd":"22:00","openTimeStart":"10:00","shopMap":"","status":1,"storeType":6},{"addressDetail":"宝安国际机场","distance":"1916410.00","freshShopType":6,"id":131684,"image":"https://img.jd9sj.com/WechatIMG418.jpeg","latitude":"22.630293","longitude":"113.812265","name":"苏苏深圳测试门店1","openTimeEnd":"23:00","openTimeStart":"09:00","shopMap":"","status":1,"storeType":0},{"addressDetail":"北京市大兴区荣华南路","distance":"1921560.00","freshShopType":6,"id":131335,"image":"https://img.jd9sj.com/WechatIMG418.jpeg","latitude":"22.551682","longitude":"114.135625","name":"测试密码","openTimeEnd":"23:00","openTimeStart":"09:00","shopMap":"","status":1,"storeType":0}]
         * type : 2
         */

        private int type;
        private List<FreshStoreVOListBean> freshStoreVOList;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<FreshStoreVOListBean> getFreshStoreVOList() {
            return freshStoreVOList;
        }

        public void setFreshStoreVOList(List<FreshStoreVOListBean> freshStoreVOList) {
            this.freshStoreVOList = freshStoreVOList;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "type=" + type +
                    ", freshStoreVOList=" + freshStoreVOList +
                    '}';
        }

        public static class FreshStoreVOListBean {
            /**
             * addressDetail : 北京市大兴区兴政街15号
             * distance : 230.00
             * freshShopType : 6
             * id : 132916
             * image : https://img.jd9sj.com/WechatIMG418.jpeg
             * latitude : 39.724926
             * longitude : 116.340474
             * name : 倩倩
             * openTimeEnd : 23:00
             * openTimeStart : 09:00
             * shopMap :
             * status : 1
             * storeType : 7
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

            @Override
            public String toString() {
                return "FreshStoreVOListBean{" +
                        "addressDetail='" + addressDetail + '\'' +
                        ", distance='" + distance + '\'' +
                        ", freshShopType=" + freshShopType +
                        ", id=" + id +
                        ", image='" + image + '\'' +
                        ", latitude='" + latitude + '\'' +
                        ", longitude='" + longitude + '\'' +
                        ", name='" + name + '\'' +
                        ", openTimeEnd='" + openTimeEnd + '\'' +
                        ", openTimeStart='" + openTimeStart + '\'' +
                        ", shopMap='" + shopMap + '\'' +
                        ", status=" + status +
                        ", storeType=" + storeType +
                        '}';
            }
        }
    }

    @Override
    public String toString() {
        return "HomeStoreBean{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
