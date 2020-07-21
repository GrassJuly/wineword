package com.runjing.bean.response;

import java.io.Serializable;

/**
 * @Created: qianxs  on 2020.07.21 14:41.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.21 14:41.
 * @Remark:
 */
public class WebBean implements Serializable {
    private int id;
    private String url;
    private String methed;
    private String parames;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethed() {
        return methed;
    }

    public void setMethed(String methed) {
        this.methed = methed;
    }

    public String getParames() {
        return parames;
    }

    public void setParames(String parames) {
        this.parames = parames;
    }

    @Override
    public String toString() {
        return "WebBean{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", methed='" + methed + '\'' +
                ", parames='" + parames + '\'' +
                '}';
    }
}
