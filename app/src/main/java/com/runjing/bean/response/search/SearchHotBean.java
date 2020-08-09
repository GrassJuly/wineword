package com.runjing.bean.response.search;

import java.util.List;

/**
 * @Created: qianxs  on 2020.08.04 11:28.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.08.04 11:28.
 * @Remark:
 */
public class SearchHotBean {
    /**
     * code : 200
     * data : ["北京红酒"]
     */

    private int code;
    private List<String> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
