package com.runjing.bean.response.home;

/**
 * @Created: qianxs  on 2020.07.30 16:34.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.30 16:34.
 * @Remark:
 */
public class BannerBean {
    /**
     * code : 200
     * data : {"extGoodsVO":{"backgroundImg":"https://img.jd9sj.com/YWR2ZXJ0aXNlbWVudF8yMDIwLTA2LTE5XzMxNzJiNWMxMTZhYjQ1NzZhMzBlZDFiYmQ4OGQzMGVhX2ZpbGU=","goodsSn":"","goodsVoImg":"https://img.jd9sj.com/YWR2ZXJ0aXNlbWVudF8yMDIwLTA2LTE5XzMxNzJiNWMxMTZhYjQ1NzZhMzBlZDFiYmQ4OGQzMGVhX2ZpbGU=","type":1,"url":"/pages/webview/invite/invite?acid=10433"},"scedGoodsVO":{},"show":true,"showExt":true,"showSced":false}
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
         * extGoodsVO : {"backgroundImg":"https://img.jd9sj.com/YWR2ZXJ0aXNlbWVudF8yMDIwLTA2LTE5XzMxNzJiNWMxMTZhYjQ1NzZhMzBlZDFiYmQ4OGQzMGVhX2ZpbGU=","goodsSn":"","goodsVoImg":"https://img.jd9sj.com/YWR2ZXJ0aXNlbWVudF8yMDIwLTA2LTE5XzMxNzJiNWMxMTZhYjQ1NzZhMzBlZDFiYmQ4OGQzMGVhX2ZpbGU=","type":1,"url":"/pages/webview/invite/invite?acid=10433"}
         * scedGoodsVO : {}
         * show : true
         * showExt : true
         * showSced : false
         */

        private ExtGoodsVOBean extGoodsVO;
        private ScedGoodsVOBean scedGoodsVO;
        private boolean show;
        private boolean showExt;
        private boolean showSced;

        public ExtGoodsVOBean getExtGoodsVO() {
            return extGoodsVO;
        }

        public void setExtGoodsVO(ExtGoodsVOBean extGoodsVO) {
            this.extGoodsVO = extGoodsVO;
        }

        public ScedGoodsVOBean getScedGoodsVO() {
            return scedGoodsVO;
        }

        public void setScedGoodsVO(ScedGoodsVOBean scedGoodsVO) {
            this.scedGoodsVO = scedGoodsVO;
        }

        public boolean isShow() {
            return show;
        }

        public void setShow(boolean show) {
            this.show = show;
        }

        public boolean isShowExt() {
            return showExt;
        }

        public void setShowExt(boolean showExt) {
            this.showExt = showExt;
        }

        public boolean isShowSced() {
            return showSced;
        }

        public void setShowSced(boolean showSced) {
            this.showSced = showSced;
        }

        public static class ExtGoodsVOBean {
            /**
             * backgroundImg : https://img.jd9sj.com/YWR2ZXJ0aXNlbWVudF8yMDIwLTA2LTE5XzMxNzJiNWMxMTZhYjQ1NzZhMzBlZDFiYmQ4OGQzMGVhX2ZpbGU=
             * goodsSn :
             * goodsVoImg : https://img.jd9sj.com/YWR2ZXJ0aXNlbWVudF8yMDIwLTA2LTE5XzMxNzJiNWMxMTZhYjQ1NzZhMzBlZDFiYmQ4OGQzMGVhX2ZpbGU=
             * type : 1
             * url : /pages/webview/invite/invite?acid=10433
             */

            private String backgroundImg;
            private String goodsSn;
            private String goodsVoImg;
            private int type;

            public String getBackgroundImg() {
                return backgroundImg;
            }

            public void setBackgroundImg(String backgroundImg) {
                this.backgroundImg = backgroundImg;
            }

            public String getGoodsSn() {
                return goodsSn;
            }

            public void setGoodsSn(String goodsSn) {
                this.goodsSn = goodsSn;
            }

            public String getGoodsVoImg() {
                return goodsVoImg;
            }

            public void setGoodsVoImg(String goodsVoImg) {
                this.goodsVoImg = goodsVoImg;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class ScedGoodsVOBean {
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "extGoodsVO=" + extGoodsVO +
                    ", scedGoodsVO=" + scedGoodsVO +
                    ", show=" + show +
                    ", showExt=" + showExt +
                    ", showSced=" + showSced +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BannerBean{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
