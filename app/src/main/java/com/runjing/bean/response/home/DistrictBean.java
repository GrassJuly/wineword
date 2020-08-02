package com.runjing.bean.response.home;

import java.util.List;

/**
 * @Created: qianxs  on 2020.07.30 14:58.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.30 14:58.
 * @Remark:
 */
public class DistrictBean {


    /**
     * code : 200
     * data : [{"childrenList":[{"createTime":"2020-07-16 15:41:05","delFlag":0,"id":7,"level":2,"name":"深圳市","operator":"mayang","parentId":6,"sort":1,"status":1,"updateTime":"2020-07-16 15:41:05"},{"createTime":"2020-07-16 15:41:22","delFlag":0,"id":8,"level":2,"name":"东莞市","operator":"mayang","parentId":6,"sort":2,"status":1,"updateTime":"2020-07-16 15:41:22"},{"createTime":"2020-07-16 15:41:38","delFlag":0,"id":9,"level":2,"name":"广州市","operator":"mayang","parentId":6,"sort":3,"status":1,"updateTime":"2020-07-16 15:41:38"},{"createTime":"2020-07-16 15:41:51","delFlag":0,"id":10,"level":2,"name":"佛山市","operator":"mayang","parentId":6,"sort":4,"status":1,"updateTime":"2020-07-16 15:41:51"},{"createTime":"2020-07-16 15:42:01","delFlag":0,"id":11,"level":2,"name":"阳江市","operator":"mayang","parentId":6,"sort":5,"status":1,"updateTime":"2020-07-16 15:42:01"}],"createTime":"2020-07-16 15:40:53","delFlag":0,"id":6,"level":1,"name":"广东省","operator":"mayang","parentId":0,"sort":1,"status":1,"updateTime":"2020-07-16 15:40:53"},{"childrenList":[{"createTime":"2020-07-16 15:42:34","delFlag":0,"id":13,"level":2,"name":"郑州市","operator":"mayang","parentId":12,"sort":1,"status":1,"updateTime":"2020-07-16 15:42:34"},{"createTime":"2020-07-16 15:42:44","delFlag":0,"id":14,"level":2,"name":"周口市","operator":"mayang","parentId":12,"sort":2,"status":1,"updateTime":"2020-07-16 15:42:44"},{"createTime":"2020-07-16 15:43:01","delFlag":0,"id":15,"level":2,"name":"驻马店市","operator":"mayang","parentId":12,"sort":3,"status":1,"updateTime":"2020-07-16 15:43:01"},{"createTime":"2020-07-16 15:43:15","delFlag":0,"id":16,"level":2,"name":"平顶山市","operator":"mayang","parentId":12,"sort":4,"status":1,"updateTime":"2020-07-16 15:43:15"},{"createTime":"2020-07-16 15:43:27","delFlag":0,"id":17,"level":2,"name":"信阳市","operator":"mayang","parentId":12,"sort":5,"status":1,"updateTime":"2020-07-16 15:43:27"},{"createTime":"2020-07-16 15:43:39","delFlag":0,"id":18,"level":2,"name":"南阳市","operator":"mayang","parentId":12,"sort":6,"status":1,"updateTime":"2020-07-16 15:43:39"}],"createTime":"2020-07-16 15:42:11","delFlag":0,"id":12,"level":1,"name":"河南省","operator":"mayang","parentId":0,"sort":2,"status":1,"updateTime":"2020-07-16 15:42:21"},{"childrenList":[{"createTime":"2020-07-16 15:44:51","delFlag":0,"id":20,"level":2,"name":"武汉市","operator":"mayang","parentId":19,"sort":1,"status":1,"updateTime":"2020-07-16 15:44:51"},{"createTime":"2020-07-16 15:45:01","delFlag":0,"id":21,"level":2,"name":"咸宁市","operator":"mayang","parentId":19,"sort":2,"status":1,"updateTime":"2020-07-16 15:45:01"}],"createTime":"2020-07-16 15:44:32","delFlag":0,"id":19,"level":1,"name":"湖北省","operator":"mayang","parentId":0,"sort":3,"status":1,"updateTime":"2020-07-16 15:44:39"},{"childrenList":[{"createTime":"2020-07-16 15:45:29","delFlag":0,"id":23,"level":2,"name":"泉州市","operator":"mayang","parentId":22,"sort":1,"status":1,"updateTime":"2020-07-16 15:45:29"}],"createTime":"2020-07-16 15:45:19","delFlag":0,"id":22,"level":1,"name":"福建省","operator":"mayang","parentId":0,"sort":4,"status":1,"updateTime":"2020-07-16 15:45:19"},{"childrenList":[{"createTime":"2020-07-16 15:46:16","delFlag":0,"id":25,"level":2,"name":"北京市","operator":"mayang","parentId":24,"sort":1,"status":1,"updateTime":"2020-07-16 15:46:16"}],"createTime":"2020-07-16 15:45:58","delFlag":0,"id":24,"level":1,"name":"北京市","operator":"mayang","parentId":0,"sort":5,"status":1,"updateTime":"2020-07-16 15:46:09"},{"childrenList":[{"createTime":"2020-07-16 15:46:42","delFlag":0,"id":27,"level":2,"name":"长沙市","operator":"mayang","parentId":26,"sort":1,"status":1,"updateTime":"2020-07-16 15:46:42"}],"createTime":"2020-07-16 15:46:31","delFlag":0,"id":26,"level":1,"name":"湖南省","operator":"mayang","parentId":0,"sort":6,"status":1,"updateTime":"2020-07-16 15:46:31"}]
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * childrenList : [{"createTime":"2020-07-16 15:41:05","delFlag":0,"id":7,"level":2,"name":"深圳市","operator":"mayang","parentId":6,"sort":1,"status":1,"updateTime":"2020-07-16 15:41:05"},{"createTime":"2020-07-16 15:41:22","delFlag":0,"id":8,"level":2,"name":"东莞市","operator":"mayang","parentId":6,"sort":2,"status":1,"updateTime":"2020-07-16 15:41:22"},{"createTime":"2020-07-16 15:41:38","delFlag":0,"id":9,"level":2,"name":"广州市","operator":"mayang","parentId":6,"sort":3,"status":1,"updateTime":"2020-07-16 15:41:38"},{"createTime":"2020-07-16 15:41:51","delFlag":0,"id":10,"level":2,"name":"佛山市","operator":"mayang","parentId":6,"sort":4,"status":1,"updateTime":"2020-07-16 15:41:51"},{"createTime":"2020-07-16 15:42:01","delFlag":0,"id":11,"level":2,"name":"阳江市","operator":"mayang","parentId":6,"sort":5,"status":1,"updateTime":"2020-07-16 15:42:01"}]
         * createTime : 2020-07-16 15:40:53
         * delFlag : 0
         * id : 6
         * level : 1
         * name : 广东省
         * operator : mayang
         * parentId : 0
         * sort : 1
         * status : 1
         * updateTime : 2020-07-16 15:40:53
         */

        private String createTime;
        private int delFlag;
        private int id;
        private int level;
        private String name;
        private String operator;
        private int parentId;
        private int sort;
        private int status;
        private String updateTime;
        private List<ChildrenListBean> childrenList;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public List<ChildrenListBean> getChildrenList() {
            return childrenList;
        }

        public void setChildrenList(List<ChildrenListBean> childrenList) {
            this.childrenList = childrenList;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "createTime='" + createTime + '\'' +
                    ", delFlag=" + delFlag +
                    ", id=" + id +
                    ", level=" + level +
                    ", name='" + name + '\'' +
                    ", operator='" + operator + '\'' +
                    ", parentId=" + parentId +
                    ", sort=" + sort +
                    ", status=" + status +
                    ", updateTime='" + updateTime + '\'' +
                    ", childrenList=" + childrenList +
                    '}';
        }

        public static class ChildrenListBean {
            /**
             * createTime : 2020-07-16 15:41:05
             * delFlag : 0
             * id : 7
             * level : 2
             * name : 深圳市
             * operator : mayang
             * parentId : 6
             * sort : 1
             * status : 1
             * updateTime : 2020-07-16 15:41:05
             */

            private String createTime;
            private int delFlag;
            private int id;
            private int level;
            private String name;
            private String operator;
            private int parentId;
            private int sort;
            private int status;
            private String updateTime;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOperator() {
                return operator;
            }

            public void setOperator(String operator) {
                this.operator = operator;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            @Override
            public String toString() {
                return "ChildrenListBean{" +
                        "createTime='" + createTime + '\'' +
                        ", delFlag=" + delFlag +
                        ", id=" + id +
                        ", level=" + level +
                        ", name='" + name + '\'' +
                        ", operator='" + operator + '\'' +
                        ", parentId=" + parentId +
                        ", sort=" + sort +
                        ", status=" + status +
                        ", updateTime='" + updateTime + '\'' +
                        '}';
            }
        }
    }

    @Override
    public String toString() {
        return "DistrictBean{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
