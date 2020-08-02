package com.runjing.bean.response.home.def;

/**
 * @Created: qianxs  on 2020.07.14 10:33.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.14 10:33.
 * @Remark:
 */
public class GoodTagBean {
    private int state;
    private String tag;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "GoodTagBean{" +
                "state=" + state +
                ", tag='" + tag + '\'' +
                '}';
    }
}
